package dam.jcpf.pikmin

data class CharacterPikmin(
    val name: String,
    val description: String,
    val type: String,
    val skills: String
) {

    fun getImage(): Int {
        return when (name) {
            "Pikmin rojo" -> R.drawable.red
            "Pikmin amarillo" -> R.drawable.yellow
            "Pikmin azul" -> R.drawable.blue
            "Pikmin blanco" -> R.drawable.white
            "Pikmin morado" -> R.drawable.purple
            "Pikmin pétreo" -> R.drawable.rock
            "Pikmin alado" -> R.drawable.winged
            "Pikmin gélido" -> R.drawable.ice
            else -> R.drawable.glow

        }
    }
}