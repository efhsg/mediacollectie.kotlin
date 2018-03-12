package nl.differentcook.mediacollectie.data

import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.lowerCase
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun queryBestanden(zoekNaam: String?, function: (Bestand) -> Unit) {
    getDatabase()
    val join = Bestanden innerJoin Mappen
    transaction {
        val query = if (zoekNaam != null)
            (join).select { Bestanden.naam.lowerCase() like "%" + zoekNaam.toLowerCase() + "%" } else
            (join).selectAll()
        query.forEach {
            function(Bestand(it[Bestanden.id], it[Bestanden.schijf], it[Mappen.naam],
                    it[Bestanden.naam], it[Bestanden.bestandstype], it[Bestanden.grootte], getOndertitels(it),
                    it[Bestanden.created_at], it[Bestanden.updated_at]))
        }
    }
}

private fun getOndertitels(it: ResultRow): MutableList<String> {
    val ondertitels: MutableList<String> = mutableListOf();
    Ondertitels.select { Ondertitels.bestand.eq(it[Bestanden.id]) }.forEach {
        ondertitels.add(it[Ondertitels.taal])
    }
    return ondertitels
}