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