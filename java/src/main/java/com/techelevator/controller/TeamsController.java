package com.techelevator.controller;

import com.techelevator.dao.PlayersDao;
import com.techelevator.dao.TeamsDao;
import com.techelevator.exception.TeamNotFoundException;
import com.techelevator.model.Players;
import com.techelevator.model.Teams;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
public class TeamsController {

    private final TeamsDao teamsDao;
    private final PlayersDao playersDao;


    public TeamsController(TeamsDao teamsDao, PlayersDao playersDao) { this.teamsDao = teamsDao; this.playersDao = playersDao; }


    @GetMapping("/teams")
    public List<Teams> listAllTeams() throws TeamNotFoundException {
        return teamsDao.listAllTeams();
    }

    @GetMapping("/teams/{id}")
    public Teams getTeamById(@PathVariable int teamId) throws InterruptedException {

        Teams result = teamsDao.getTeamById(teamId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Tournament with that id. ");
        } else {
            return result;
        }
    }



    @GetMapping("/teams/{id}/teamSize")
    public Teams getTeamSize(@PathVariable ("id")  int teamSize) throws InterruptedException {

        Teams result = teamsDao.getTeamSize(teamSize);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Tournament with that id. ");
        } else {
            return result;
        }
    }
    @GetMapping("/teams/{id}/players")
    public List<Players> getPlayersByTeam(@PathVariable ("id") int teamId){
        return playersDao.getPlayerByTeam(teamId);
    }




    @RequestMapping(path = "/teams", method = RequestMethod.POST)
    public Teams postTeam(@RequestBody Teams newTeam) {
        return teamsDao.createTeam(newTeam);
    }


    @RequestMapping(path = "/teams/{id}", method = RequestMethod.PUT)
    public Teams putTeam(@PathVariable ("id") int teamId, @RequestBody Teams updatedTeam) {

       if (teamsDao.updateTeam(teamId) != null) {
            return updatedTeam;
        } else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tournament not found to update.");
        }
    }

    @DeleteMapping("/teams/{id}")
    public void deleteTeam(@PathVariable int teamId) {
      if (!teamsDao.deleteTeam(teamId)) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found to delete.");
         }

     }

}

