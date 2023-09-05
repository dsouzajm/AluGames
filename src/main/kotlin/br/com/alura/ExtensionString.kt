package br.com.alura

import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

fun String.retornaIdade(): Int {
    val dateFormatter: DateTimeFormatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val dataNascimento = LocalDate.parse(this, dateFormatter)
    val to = LocalDate.now()

    val period = Period.between(dataNascimento, to)
    return period.years
}