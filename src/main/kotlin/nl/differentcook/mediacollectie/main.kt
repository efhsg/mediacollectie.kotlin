package nl.differentcook.mediacollectie

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>) {

    Database.connect("jdbc:mysql://192.168.10.10:3306/mediacollectie", driver = "com.mysql.jdbc.Driver", user = "homestead", password = "secret")

    transaction {
        logger.addLogger(StdOutSqlLogger)
        println("Schijven: ${Schijven.selectAll()}")
    }
}

object Schijven : IntIdTable() {
    val naam = varchar("naam", 50)
    val capaciteit = varchar("capaciteit", 50)
}