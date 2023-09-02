package br.com.alura.service

import br.com.alura.model.InfoJogo
import br.com.alura.model.Jogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class ApiJogo {
    fun buscaJogo(id:String):InfoJogo{
        var urlApi = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
                                .uri(URI.create(urlApi))
                                .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val json = response.body()
        var gson = Gson()
        var meuJogo: Jogo? = null
        return gson.fromJson(json, InfoJogo::class.java)
    }
}