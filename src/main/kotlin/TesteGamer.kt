import br.com.alura.modelo.Gamer

fun main() {
    var gamer1 = Gamer("Fulano",
                       "fulano@alura.com",
                       "01/12/2000",
                       "fulanoUser")
    println("Gamer1='$gamer1")

    val gamer2 = Gamer(
        "Daniel Souza",
        "daniel2@alura.com",
        "01/01/2000",
        "danielsouzajm2"
    )
    println("Gamer2='$gamer2")

    gamer1.let {
        it.dataNascimento = "31/12/1999"
        it.usuario = "danielsouzajm1"
    }
    println("Gamer1='$gamer1")
}

class TesteInfoGamer {
    fun main(){

    }
}