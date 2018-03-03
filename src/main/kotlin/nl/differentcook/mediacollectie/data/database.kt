package nl.differentcook.mediacollectie.data

import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.joda.time.DateTime

fun getDatabase() {
    val dotenv = dotenv {
        directory = "E:\\dev\\projects\\kotlin\\mediacollectie"
    }

    Database.connect(dotenv["DB_URL"] ?: "", driver = dotenv["DB_DRIVER"] ?: "",
            user = dotenv["DB_USER"] ?: "", password = dotenv["DB_PASSWORD"] ?: "")
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

object Ondertitels : Table(name = "ondertitels") {
    val id: Column<Int> = integer("id").primaryKey()
    val bestand: Column<Int> = integer("bestand")
    val taal: Column<String> = varchar("taal", 25)
    val soort: Column<String> = varchar("soort", 25)
    val created_at: Column<DateTime> = date("created_at")
    val updated_at: Column<DateTime> = date("updated_at")
}

