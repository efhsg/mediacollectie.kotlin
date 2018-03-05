package nl.differentcook.mediacollectie.data

import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table

fun getDatabase() {
    val dotenv = dotenv {
        directory = "."
    }

    Database.connect(dotenv["DB_URL"] ?: "", driver = dotenv["DB_DRIVER"] ?: "",
            user = dotenv["DB_USER"] ?: "", password = dotenv["DB_PASSWORD"] ?: "")
}

object Schijven : Table(name = "schijven") {
    val id = integer("id").primaryKey()
    val naam = varchar("naam", 25).uniqueIndex()
    val capaciteit = integer("capaciteit")
    val beschikbaar = integer("beschikbaar")
    val scandatum = date("scandatum")
    val created_at = date("created_at")
    val updated_at = date("updated_at")
}

object Mappen : Table(name = "mappen") {
    val id = integer("id").primaryKey()
    val naam = varchar("naam", 200)
    val created_at = date("created_at")
    val updated_at = date("updated_at")
}

object Bestanden : Table(name = "bestanden") {
    val id = integer("id").primaryKey()
    val naam = varchar("naam", 150)
    val bestandstype = varchar("bestandstype", 25) references Bestandstypes.naam
    val map = integer("map") references Mappen.id
    val schijf = varchar("schijf", 25) references Schijven.naam
    val grootte = integer("grootte")
    val created_at = date("created_at")
    val updated_at = date("updated_at")
}

object Bestandstypes : Table(name = "bestandstypes") {
    val id = integer("id").primaryKey()
    val naam = varchar("naam", 25).uniqueIndex()
    val created_at = date("created_at")
    val updated_at = date("updated_at")
}

object Ondertitels : Table(name = "ondertitels") {
    val id = integer("id").primaryKey()
    val bestand = integer("bestand") references Bestanden.id
    val taal = varchar("taal", 25)
    val soort = varchar("soort", 25)
    val created_at = date("created_at")
    val updated_at = date("updated_at")
}

