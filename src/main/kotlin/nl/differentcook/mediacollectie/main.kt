package nl.differentcook.mediacollectie

import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {

    val dotenv = dotenv {
        directory = "E:\\dev\\projects\\kotlin\\mediacollectie"
    }

    Database.connect(dotenv["DB_URL"] ?: "", driver = "com.mysql.jdbc.Driver",
            user = dotenv["DB_USER"] ?: "", password = dotenv["DB_PASSWORD"] ?: "")

    transaction {
        logger.addLogger(StdOutSqlLogger)
        Schijven.select { Schijven.id greater 0 }.forEach {
            println(it[Schijven.naam] + "," + it[Schijven.capaciteit] + "," + it[Schijven.beschikbaar])
        }
    }
}

object Schijven : Table(name = "schijven") {
    val id: Column<Int> = integer("id")
    val naam: Column<String> = varchar("naam", 50)
    val capaciteit: Column<Int> = integer("capaciteit")
    val beschikbaar: Column<Int> = integer("beschikbaar")
}