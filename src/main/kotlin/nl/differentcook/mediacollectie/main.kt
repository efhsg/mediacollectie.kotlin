package nl.differentcook.mediacollectie

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {

    Database.connect("jdbc:mysql://192.168.10.10:3306/mediacollectie?autoReconnect=true&useSSL=false", driver = "com.mysql.jdbc.Driver", user = "homestead", password = "secret")

    transaction {
        logger.addLogger(StdOutSqlLogger)
        Schijven.select { Schijven.capaciteit greater 0 }.forEach {
            println(it[Schijven.naam] + "," + it[Schijven.capaciteit] + "," + it[Schijven.beschikbaar])
        }
    }
}

object Schijven : IntIdTable(name = "schijven") {
    val naam: Column<String> = varchar("naam", 50)
    val capaciteit: Column<Int> = integer("capaciteit")
    val beschikbaar: Column<Int> = integer("beschikbaar")
}