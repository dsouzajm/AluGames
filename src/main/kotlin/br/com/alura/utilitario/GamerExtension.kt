package br.com.alura.utilitario

import br.com.alura.modelo.Gamer
import br.com.alura.modelo.InfoGamerJson

fun InfoGamerJson.criaGamer(): Gamer {
    return Gamer(this.nome, this.email, this.dataNascimento, this.usuario)
}