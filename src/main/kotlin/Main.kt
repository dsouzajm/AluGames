import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*


fun main() {

    var scanner = Scanner(System.`in`)
    println("Digite o codigo do jogo: ")
    var codigoDigitado = scanner.nextLine()
    var urlApi = "https://www.cheapshark.com/api/1.0/games?id=$codigoDigitado"
    println(urlApi)

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
                                .uri(URI.create(urlApi))
                                .build()
    val response = client.send(request, BodyHandlers.ofString())
    val json = response.body()
    var gson = Gson()
    var meuJogo:Jogo? = null
       
    var resultado = runCatching {
        var meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
        meuJogo = Jogo(meuInfoJogo.info.title,
                       meuInfoJogo.info.thumb)
    }

    resultado.onFailure {
        println("Erro, tente outro id.")
    }

    resultado.onSuccess {
        println("Digite a descricao:")
        val descricaoDigitada = scanner.nextLine()
        meuJogo?.descricao = descricaoDigitada
        println(meuJogo)
        println("Chamada da API realizada com sucesso.")
    }
}