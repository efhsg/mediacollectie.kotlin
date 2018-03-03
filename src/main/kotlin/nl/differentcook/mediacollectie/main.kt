package nl.differentcook.mediacollectie

import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

fun main(args: Array<String>) {

    val dotenv = dotenv {
        directory = "E:\\dev\\projects\\kotlin\\mediacollectie"
    }

    Database.connect(dotenv["DB_URL"] ?: "", driver = dotenv["DB_DRIVER"] ?: "",
            user = dotenv["DB_USER"] ?: "", password = dotenv["DB_PASSWORD"] ?: "")

    transaction {
        logger.addLogger(StdOutSqlLogger)
        printSchijven()
        printMappen()
        printBestandstypes()
        printBestanden()
    }
}

private fun printBestandstypes() {
    printStreep();
    bestandstypes.select { bestandstypes.id greater 0 }.forEach {
        println(it[bestandstypes.naam] + ","
                + "," + it[bestandstypes.created_at].toLocalDate()
                + "," + it[bestandstypes.updated_at].toLocalDate()
        )
    }
    printStreep();
}

private fun printMappen() {
    printStreep();
    Mappen.select { Mappen.id greater 0 }.forEach {
        println(it[Mappen.naam] + ","
                + "," + it[Mappen.created_at].toLocalDate()
                + "," + it[Mappen.updated_at].toLocalDate()
        )
    }
    printStreep();
}

private fun printSchijven() {
    printStreep();
    Schijven.select { Schijven.id greater 0 }.forEach {
        println(it[Schijven.naam] + "," + it[Schijven.capaciteit] + "," + it[Schijven.beschikbaar]
                + "," + it[Schijven.scandatum].toLocalDate()
                + "," + it[Schijven.created_at].toLocalDate()
                + "," + it[Schijven.updated_at].toLocalDate()
        )
    }
    printStreep();
}

private fun printBestanden() {
    printStreep();
    Bestanden.select { Bestanden.id greater 0 }.forEach {
        println(it[Bestanden.naam] + "," + it[Bestanden.bestandstype] + "," + it[Bestanden.map]
                + "," + it[Bestanden.schijf]
                + "," + it[Bestanden.grootte]
                + "," + it[Bestanden.created_at].toLocalDate()
                + "," + it[Bestanden.updated_at].toLocalDate()
        )
    }
    printStreep();
}

object Schijven : Table(name = "schijven") {
    val id: Column<Int> = integer("id").primaryKey()
    val naam: Column<String> = varchar("naam", 25)
    val capaciteit: Column<Int> = integer("capaciteit")
    val beschikbaar: Column<Int> = integer("beschikbaar")
    val scandatum: Column<DateTime> = date("scandatum")
    val created_at: Column<DateTime> = date("created_at")
    val updated_at: Column<DateTime> = date("updated_at")
}

object Mappen : Table(name = "mappen") {
    val id: Column<Int> = integer("id").primaryKey()
    val naam: Column<String> = varchar("naam", 200)
    val created_at: Column<DateTime> = date("created_at")
    val updated_at: Column<DateTime> = date("updated_at")
}

object bestandstypes : Table(name = "bestandstypes") {
    val id: Column<Int> = integer("id").primaryKey()
    val naam: Column<String> = varchar("naam", 25)
    val created_at: Column<DateTime> = date("created_at")
    val updated_at: Column<DateTime> = date("updated_at")
}

object Bestanden : Table(name = "bestanden") {
    val id: Column<Int> = integer("id").primaryKey()
    val naam: Column<String> = varchar("naam", 150)
    val bestandstype: Column<String> = varchar("bestandstype", 25)
    val map: Column<Int> = integer("map")
    val schijf: Column<String> = varchar("schijf", 25)
    val grootte: Column<Int> = integer("grootte")
    val created_at: Column<DateTime> = date("created_at")
    val updated_at: Column<DateTime> = date("updated_at")
}

private fun printStreep() {
    println("=".streep())
}

fun String.streep() = repeat(80)