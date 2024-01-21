import br.com.alura.modelo.Gamer
import br.com.alura.modelo.Jogo
import br.com.alura.retornaIdade
import br.com.alura.servico.ConsumoApi
import java.util.*

fun main() {

    var scanner = Scanner(System.`in`)
    var gamer = Gamer.criaGamer(scanner)
    println(gamer)
    println("\nA idade do gamer e:" + gamer.dataNascimento?.retornaIdade())

    do {
        println("Digite o codigo do jogo que deseja pesquisar: ")
        var codigoDigitado = scanner.nextLine()
        var serviceApiJogo = ConsumoApi()
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

    println("\nEsses sao os jogos pesquisados:")
    println(gamer.jogosPesquisados)

    gamer.jogosPesquisados.sortBy {
        it?.titulo
    }
    println("\nEsses sao os jogos pesquisados ordenados:")
    println(gamer.jogosPesquisados)

    val jogosFiltrados = gamer.jogosPesquisados.filter {
        it?.titulo?.contains("Batman", true)?: false
    }
    println("\nEsses sao os jogos pesquisados filtrados:")
    println(jogosFiltrados)

    println("\nExibindo somente o titulo dos filmes:")
    gamer.jogosPesquisados.forEach {
        println(it?.titulo)
    }

    println("\nDeseja excluir algum elmento da lista de filmes pesquisados? Digite S/N.")
    val respostaExclusao = scanner.nextLine()
    if(respostaExclusao.equals("S", true)) {
        println("Digite o indice a ser excluido da lista:")
        val indiceExclusao = scanner.nextInt()
        gamer.jogosPesquisados.removeAt(indiceExclusao)
        println("Esses sao os jogos pesquisados apos a exclusao:")
        println(gamer.jogosPesquisados)
    }

    println("\nChamada da API realizada com sucesso.")
}