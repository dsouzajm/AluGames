package br.com.alura.servico

import br.com.alura.modelo.Gamer
import br.com.alura.modelo.InfoJogadorJson
import br.com.alura.modelo.InfoJogo
import br.com.alura.modelo.Jogo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ConsumoApi {
    fun buscaJogo(id:String):InfoJogo{

        var urlApi = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder().uri(URI.create(urlApi)).build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val json = response.body()
        var gson = Gson()
        var meuJogo: Jogo? = null
        return gson.fromJson(json, InfoJogo::class.java)
    }
    fun buscaJogador(): List<Gamer> {

        var urlApi = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder().uri(URI.create(urlApi)).build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val json = response.body()
        val gson = Gson()
        val meuGamerTipo = object : TypeToken<List<InfoJogadorJson>>() {}.type

        val listaInfoJogadorJson: List<InfoJogadorJson> = gson.fromJson(json, meuGamerTipo)
        val listaConvertidaGamer: List<Gamer> = listaInfoJogadorJson.map { listaInfoJogadorJson -> Gamer(listaInfoJogadorJson.nome, listaInfoJogadorJson.email, listaInfoJogadorJson.dataNascimento, listaInfoJogadorJson.usuario) }
        return listaConvertidaGamer
    }
}