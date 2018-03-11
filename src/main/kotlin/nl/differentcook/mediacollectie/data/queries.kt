package nl.differentcook.mediacollectie.data

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun queryBestanden(zoekNaam: String?, function: (ResultRow) -> Unit) {
    getDatabase()
    val join = Bestanden innerJoin Mappen
    transaction {
        val query = if (zoekNaam != null)
            (join).select { Bestanden.naam like "%" + zoekNaam + "%" } else
            (join).selectAll()
        query.forEach { resultRow -> function(resultRow) }
    }
}