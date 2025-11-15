package dam.jcpf.pikmin
/**
 * Representa un tipo de Pikmin del juego.
 *
 * @property name El nombre del Pikmin (Se usa como idientificador úinico también)
 * @property description La descripción del Pikmin
 * @property type El tipo de Pikmin
 * @property skills Las habilidades del Pikmin
 */
data class CharacterPikmin(
    val name: String,
    val description: String,
    val type: String,
    val skills: String
) {

    /**
     * Devuelve la imagen del Pikmin correspondiente según su ID (name)
     *
     * @return drawable correspondiente al name del Pikmin
     */
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