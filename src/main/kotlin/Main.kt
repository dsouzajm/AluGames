import com.google.gson.Gson
import java.lang.Exception
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.*


fun main() {

    var scanner = Scanner(System.`in`)
    println("Digite o codigo: ")
    var codigoDigitado = scanner.nextLine()
    var urlApi = "https://www.cheapshark.com/api/1.0/games?id=$codigoDigitado"
    println(urlApi)

    val client: HttpClient = HttpClient.newHttpClient()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(urlApi))
        .build()
    val response = client
        .send(request, BodyHandlers.ofString())
    val json = response.body()
    println(json)

    val meuJogo = Jogo( "Batman: Arkham Asylum Game of the Year Edition",
                        "https:\\/\\/cdn.cloudflare.steamstatic.com\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1681938587")
    println(meuJogo)

    var gson = Gson()

    try {
        var meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)
        var meuJogo2 = Jogo(meuInfoJogo.info.title,
                            meuInfoJogo.info.thumb)
        println(meuJogo2)
    } catch(ex:Exception){
        println("Erro, tente outro id")
    }
}