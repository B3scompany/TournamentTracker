package com.techelevator.controller;

import com.techelevator.dao.PlayersDao;
import com.techelevator.model.Players;
import org.springframework.data.repository.query.Param;
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
public class PlayersController {

    private final PlayersDao playersDao;

    public PlayersController(PlayersDao playersDao) {
        this.playersDao = playersDao;
    }


    @GetMapping("/players")
    public List<Players> listAllPlayers() throws InterruptedException {
        return playersDao.listAllPlayers();
    }

    @GetMapping("/players/{id}")
    public Players getPlayerById(@PathVariable int playerId) throws InterruptedException {

        Players result = playersDao.getPlayerById(playerId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Player found with that id. ");
        } else {
            return result;
        }

    }
        @GetMapping("/players/{userId}")
        public Players getPlayerByUserId(@PathVariable ("userId") int userId) throws InterruptedException {

            Players result = playersDao.getPlayerByUserId(userId);
            if (result == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Player found with that user id. ");
            } else {
                return result;
            }

        }

            @GetMapping("/players/{playerId}")
            public Players getPlayerName(@PathVariable int playerId) throws InterruptedException {

                Players result = playersDao.getPlayerName(playerId);
                if (result == null) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Player with that name. ");
                } else {
                    return result;
                }
            }



    @RequestMapping(path = "/players", method = RequestMethod.POST)
    public Players postPlayer(@RequestBody Players newPlayer) {
        return playersDao.createPlayer(newPlayer);
    }


    @RequestMapping(path = "/players/{id}", method = RequestMethod.PUT)
    public Players putPlayer(@PathVariable ("id") int playerId, @RequestBody Players updatedPlayer) {

        if (playersDao.updatePlayer(playerId) != null) {
            return updatedPlayer;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found to update.");
        }

    }


    @DeleteMapping("/players/{id}")
    public void deletePlayer(@PathVariable int playerId) {
        if (!playersDao.deletePlayer(playerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found to delete.");
        }

    }
}
