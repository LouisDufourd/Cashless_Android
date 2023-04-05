package fr.plaglefleau.cashless.models.response.stand

import fr.plaglefleau.cashless.models.Historique

data class StandHistoriqueResponse(var responseString: String, val historiques : ArrayList<Historique>?)
