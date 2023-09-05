package br.com.alura.model

class Jogo(val titulo:String, val capa:String) {
    var descricao:String? = null
    override fun toString():String {
        return "\nJogo {titulo='$titulo', capa='$capa', descricao='$descricao'}"
    }
}