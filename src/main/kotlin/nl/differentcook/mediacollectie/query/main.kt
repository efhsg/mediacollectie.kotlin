package nl.differentcook.mediacollectie.query

import nl.differentcook.mediacollectie.data.Bestanden
import nl.differentcook.mediacollectie.data.Bestandstypes
import nl.differentcook.mediacollectie.data.Mappen
import nl.differentcook.mediacollectie.data.getDatabase
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {
    getDatabase()
    printBestanden(if (args.size == 1) args[0] else null)
}

private fun printBestanden(zoekNaam: String? = null) {
    printStreep();
    transaction {
        logger.addLogger(StdOutSqlLogger)
        printBestanden(queryBestanden(zoekNaam))
    }
}

private fun queryBestanden(zoekNaam: String?): Query {
    val join = Bestanden innerJoin Mappen
    return if (zoekNaam != null)
        (join).select { Bestanden.naam like "%" + zoekNaam + "%" } else
        (join).selectAll()
}

private fun printBestanden(query: Query) {
    query.forEach {
        println("${it[Bestanden.schijf]}:${it[Mappen.naam]}\\${it[Bestanden.naam]}.${it[Bestanden.bestandstype]}"
        )
    }
}

private fun printStreep() {
    println("=".streep())
}

fun String.streep() = repeat(80)