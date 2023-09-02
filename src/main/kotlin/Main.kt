import br.com.alura.model.InfoJogo
import br.com.alura.model.Jogo
import br.com.alura.service.ApiJogo
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
    var serviceApiJogo = ApiJogo()
    val meuInfoJogo = serviceApiJogo.buscaJogo(codigoDigitado)
    var meuJogo:Jogo? = null

    var resultado = runCatching {
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