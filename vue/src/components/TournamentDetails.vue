<template>
  <div>
      <h2>{{tournament.tournamentName}}</h2>
      <section id="tournament-info">
          <p>{{tournament.sportName}}</p>
          <p>From {{tournament.startDate}} to {{tournament.endDate}}</p>
          <p>{{tournament.description}}</p>
      </section>
  </div>
</template>

<script>
import TournamentService from "@/services/TournamentService.js"

export default {
    name: "tournament-details",
    props: {
        tournamentID: Number
    },
    data(){
        return {
            tournament: {}
        }
    },
    created() {
        TournamentService.getTournamentDetails(this.tournamentID).then((response) => {
            if (response.status == 200) {
                let newTournament = {};
                newTournament.tournamentId = response.data.tournamentId;
                newTournament.tournamentName = response.data.tournamentName;
                newTournament.startDate = response.data.startDate;
                newTournament.endDate = response.data.endDate;
                newTournament.sportId = response.data.sportId;
                newTournament.numOfTeams = response.data.numOfTeams;
                newTournament.description = response.data.description;

                TournamentService.getSportById(response.data.sportId).then((response) => {
                if (response.status == 200) {
                    newTournament.sportName = response.data.sportName;
                    this.$store.commit("SET_ACTIVE_TOURNAMENT", newTournament);
                    this.tournament = newTournament;
                }
                });
            }
        })
    }
}
</script>

<style scoped>
    h2 {
        text-align: center;
        color:#DC8400;
    }
    #tournament-info {
        text-align: center;
    }
    div {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
</style>