import br.com.alura.model.Jogo
import br.com.alura.service.ApiJogo
import java.util.*

fun main() {

    var scanner = Scanner(System.`in`)

    do {
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
            println("Digite a descricao para preenchimento do filme retornado:")
            val descricaoDigitada = scanner.nextLine()
            meuJogo?.descricao = descricaoDigitada
            println(meuJogo)
        }
        println("Deseja prosseguir com a busca? Digite S/N.")
        val respostaContinuacao = scanner.nextLine()
    } while (respostaContinuacao.equals("S", true))

    println("Chamada da API realizada com sucesso.")
}