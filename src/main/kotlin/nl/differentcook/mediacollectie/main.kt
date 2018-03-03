package nl.differentcook.mediacollectie

import nl.differentcook.mediacollectie.data.*
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {
    getDatabase()
    printData()
}

private fun printData() {
    transaction {
        logger.addLogger(StdOutSqlLogger)
        printSchijven()
        printMappen()
        printBestandstypes()
        printBestanden()
        printOndertitels()
    }
}

private fun printBestandstypes() {
    printStreep();
    bestandstypes.selectAll().forEach {
        println(it[bestandstypes.naam] + ","
                + "," + it[bestandstypes.created_at].toLocalDate()
                + "," + it[bestandstypes.updated_at].toLocalDate()
        )
    }
}

private fun printMappen() {
    printStreep();
    Mappen.selectAll().forEach {
        println(it[Mappen.naam] + ","
                + "," + it[Mappen.created_at].toLocalDate()
                + "," + it[Mappen.updated_at].toLocalDate()
        )
    }
}

private fun printSchijven() {
    printStreep();
    Schijven.selectAll().forEach {
        println(it[Schijven.naam] + "," + it[Schijven.capaciteit] + "," + it[Schijven.beschikbaar]
                + "," + it[Schijven.scandatum].toLocalDate()
                + "," + it[Schijven.created_at].toLocalDate()
                + "," + it[Schijven.updated_at].toLocalDate()
        )
    }
}

private fun printBestanden() {
    printStreep();
    Bestanden.selectAll().forEach {
        println(it[Bestanden.naam] + "," + it[Bestanden.bestandstype] + "," + it[Bestanden.map]
                + "," + it[Bestanden.schijf]
                + "," + it[Bestanden.grootte]
                + "," + it[Bestanden.created_at].toLocalDate()
                + "," + it[Bestanden.updated_at].toLocalDate()
        )
    }
}

private fun printOndertitels() {
    printStreep();
    Ondertitels.selectAll().forEach {
        println(it[Ondertitels.bestand].toString()
                + "," + it[Ondertitels.taal] + "," + it[Ondertitels.soort]
                + "," + it[Ondertitels.created_at]?.toLocalDate()
                + "," + it[Ondertitels.updated_at]?.toLocalDate()
        )
    }
}

private fun printStreep() {
    println("=".streep())
}

fun String.streep() = repeat(80)