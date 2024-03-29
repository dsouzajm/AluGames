package br.com.alura.modelo

import java.lang.IllegalArgumentException
import java.security.InvalidParameterException
import java.util.*
import kotlin.random.Random

data class Gamer(var nome:String,
                 var email:String) {
    var dataNascimento:String? = null
    var usuario:String? = null
        set(value) {
            field = value
            criaIdInterno(value)
        }
    private var idInterno:String? = null
        private set
    var jogosPesquisados = mutableListOf<Jogo?>();
    init {
        if(nome.isNullOrBlank()){
            throw IllegalArgumentException("Nome esta em branco!")
        }
        val emailValido = isEmailValido(email)
        if(!emailValido){
            throw InvalidParameterException("Email invalido!");
        }
    }
    constructor(nome:String, email: String, dataNascimento:String?, usuario:String?) : this(nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
    }
    fun isEmailValido(email:String):Boolean{
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        return regex.matches(email)
    }
    fun criaIdInterno(usuario:String?) {
        if(!usuario.isNullOrBlank()) {
            val idAleatorio = Random.nextInt(10000)
            val tag = String.format("%04d", idAleatorio)
            idInterno = "$usuario#$tag"
        } else {
            throw InvalidParameterException("Usuario invalido!")
        }
    }
    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }

    companion object {
        fun criaGamer(leitura: Scanner): Gamer {
            println("Bem-vindos ao AluGames! Vamos fazer seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Deseja completar seu cadastro com usuário e data de nascimento? (S/N)")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Digite sua data de nascimento(DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, nascimento, usuario)
            } else {
                return Gamer (nome, email)
            }
        }
    }
}