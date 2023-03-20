package fr.plaglefleau.cashless.models

data class Stand(
    val idStand: Int,
    val standName: String
){
    override fun toString(): String {
        return "    idStand:$idStand\n" +
               "    standName:$standName"
    }
}