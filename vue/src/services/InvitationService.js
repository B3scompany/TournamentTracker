import axios from "axios";

export default {

    createInvite(invitation){
        return axios.post('/invitations', invitation);
    },

    getInviteByTournamentId(tournamentID){
        return axios.get(`/invitations/tournament/${tournamentID}`)
    },

   sentInviteByOrganizerId(organizerId){
        return axios.get(`/invitations/organizer/${organizerId}`);
    },

    getInviteByStatus(invitationStatus) {
        return axios.get(`/invitations/${invitationStatus}`);
    },

    getInviteByTeamId(teamId){
        return axios.get(`/invitations/team/${teamId}`);
    },

    updateInvitationStatus(invitation) {
        return axios.put(`/invitations/status/`, invitation);
    }
    
}