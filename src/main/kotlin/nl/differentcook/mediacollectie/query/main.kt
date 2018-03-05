package nl.differentcook.mediacollectie.query

import nl.differentcook.mediacollectie.data.Bestanden
import nl.differentcook.mediacollectie.data.Mappen
import nl.differentcook.mediacollectie.data.getDatabase
import nl.differentcook.mediacollectie.data.queryBestanden
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {
    printBestanden(if (args.size == 1) args[0] else null)
}

private fun printBestanden(zoekNaam: String? = null) {
    getDatabase()
    transaction {
        logger.addLogger(StdOutSqlLogger)
        printBestanden(queryBestanden(zoekNaam))
    }
}

private fun printBestanden(query: Query) {
    printStreep()
    query.forEach {
        println("${it[Bestanden.schijf]}:${it[Mappen.naam]}\\${it[Bestanden.naam]}.${it[Bestanden.bestandstype]}"
        )
    }
    printStreep()
}

private fun printStreep() {
    println("=".streep())
}

fun String.streep() = repeat(80)