package dam.jcpf.pikmin

data class CharacterPikmin(
    val name: String,
    val description: String,
    val type: String,
    val skills: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CharacterPikmin

        if (name != other.name) return false
        if (description != other.description) return false
        if (type != other.type) return false
        if (!skills.contentEquals(other.skills)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + skills.contentHashCode()
        return result
    }

    fun getImage(): Int {
        return when (name) {
            "Pikmin rojo" -> R.drawable.redPikmin
            "Pikmin amarillo" -> R.drawable.yellowPikmin
            "Pikmin azul" -> R.drawable.bluePikmin
            "Pikmin blanco" -> R.drawable.whitePikmin
            "Pikmin morado" -> R.drawable.purplePikmin
            "Pikmin pétreo" -> R.drawable.rockPikmin
            "Pikmin alado" -> R.drawable.wingedPikmin
            "Pikmin gélido" -> R.drawable.icePikmin
            else -> R.drawable.glowPikmin

        }
    }
}