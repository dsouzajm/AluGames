package br.com.alura

import br.com.alura.servico.ConsumoApi
fun main() {
    val consumo = ConsumoApi()
    val listaGamers = consumo.buscaJogador()

    println(listaGamers)
}
