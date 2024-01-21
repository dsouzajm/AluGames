package br.com.alura.servico

import br.com.alura.modelo.Gamer
import br.com.alura.modelo.InfoGamerJson
import br.com.alura.modelo.InfoJogo
import br.com.alura.modelo.Jogo
import br.com.alura.utilitario.criaGamer
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
        val meuGamerTipo = object : TypeToken<List<InfoGamerJson>>() {}.type

        val listaInfoJogadorJson: List<InfoGamerJson> = gson.fromJson(json, meuGamerTipo)
        val listaConvertidaGamer: List<Gamer> = listaInfoJogadorJson.map { listaInfoJogadorJson ->  listaInfoJogadorJson.criaGamer() }
        return listaConvertidaGamer
    }
}