import br.com.alura.model.Gamer
import br.com.alura.model.Jogo
import br.com.alura.service.ApiJogo
import java.util.*

fun main() {

    var scanner = Scanner(System.`in`)
    var gamer = Gamer.criarGamer(scanner)
    println(gamer)

    do {
        println("Digite o codigo do jogo que deseja pesquisar: ")
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
            println(meuJogo)
            println("Deseja preencher a descricao para o filme retornado? Digite S/N.")
            var preenchimentoPreenchido = scanner.nextLine()
            if(preenchimentoPreenchido.equals("S", true)){
                println("Digite a descricao para preenchimento do filme retornado:")
                val descricaoDigitada = scanner.nextLine()
                meuJogo?.descricao = descricaoDigitada
            }
            gamer.jogosPesquisados.add(meuJogo)
        }

        println("Deseja prosseguir com a busca? Digite S/N.")
        val respostaContinuacao = scanner.nextLine()
    } while (respostaContinuacao.equals("S", true))

    println("Esses foram os jogos pesquisados:")
    println(gamer.jogosPesquisados)
    println("Chamada da API realizada com sucesso.")
}