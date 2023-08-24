class Jogo(val titulo: String, val capa: String) {
    val descricao: String? = null
    override fun toString(): String {
        return "Jogo(titulo='$titulo', capa='$capa', descricao='$descricao')"
    }
}