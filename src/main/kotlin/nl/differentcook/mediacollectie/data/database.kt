package nl.differentcook.mediacollectie.data

import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.io.File
import java.text.SimpleDateFormat

const val dateFormat = "MM/dd/yyyy"

fun getDatabase() {
    val dotenv = dotenv {
        directory = "."
    }

    Database.connect(dotenv["DB_URL"] ?: "", driver = dotenv["DB_DRIVER"] ?: "",
            user = dotenv["DB_USER"] ?: "", password = dotenv["DB_PASSWORD"] ?: "")
}

fun createSeededDatabase() {
    destroyDatabase()
    makeDatabase()
    seedDatabase()
}


fun makeDatabase() {
    getDatabase()
    transaction {
        create(Schijven, Mappen, Bestandstypes, Bestanden, Ondertitels)
    }
}

fun destroyDatabase() {
    getDatabase()
    transaction {
        drop(Ondertitels, Bestanden, Bestandstypes, Mappen, Schijven)
    }
}

fun seedDatabase() {
    getDatabase()
    transaction {
        seedSchijven()
        seedBestandtypes()
        seedMappen()
        seedBestanden()
        seedOndertitels()
    }
}

private fun seedSchijven() {
    val file = File(getDumpdir() + File.separator + "schijven.csv")
    file.reader().forEachLine {
        val schijf = it.splitWithComma()
        Schijven.insert {
            it[created_at] = DateTime.now()
            it[updated_at] = DateTime.now()
            it[naam] = schijf[3].stripQuotes()
            it[capaciteit] = schijf[4].stripQuotes().toInt()
            it[beschikbaar] = schijf[5].stripQuotes().toInt()
            it[scandatum] = DateTime((SimpleDateFormat(dateFormat)).parse(schijf[6].stripQuotes()))
        }
    }
}

private fun seedBestandtypes() {
    val file = File(getDumpdir() + File.separator + "bestandstypes.csv")
    file.reader().forEachLine {
        val bestandstype = it.splitWithComma()
        Bestandstypes.insert {
            it[naam] = bestandstype[3].stripQuotes()
            it[created_at] = DateTime.now()
            it[updated_at] = DateTime.now()
        }
    }
}

private fun seedMappen() {
    val file = File(getDumpdir() + File.separator + "mappen.csv")
    file.reader().forEachLine {
        val map = it.splitWithComma()
        Mappen.insert {
            it[naam] = map[3].stripQuotes()
            it[created_at] = DateTime.now()
            it[updated_at] = DateTime.now()
        }
    }
}

private fun seedBestanden() {
    val file = File(getDumpdir() + File.separator + "bestanden.csv")
    file.reader().forEachLine {
        val bestand = it.splitWithComma()
        Bestanden.insert {
            it[naam] = bestand[3].stripQuotes()
            it[bestandstype] = bestand[4].stripQuotes()
            it[map] = bestand[5].stripQuotes().toInt()
            it[schijf] = bestand[6].stripQuotes()
            it[grootte] = bestand[7].stripQuotes().toInt()
            it[created_at] = DateTime.now()
            it[updated_at] = DateTime.now()
        }
    }
}

private fun seedOndertitels() {
    val file = File(getDumpdir() + File.separator + "ondertitels.csv")
    file.reader().forEachLine {
        val ondertitel = it.splitWithComma()
        Ondertitels.insert {
            it[bestand] = ondertitel[3].stripQuotes().toInt()
            it[taal] = ondertitel[4].stripQuotes()
            it[soort] = ondertitel[5].stripQuotes()
            it[created_at] = DateTime.now()
            it[updated_at] = DateTime.now()
        }
    }
}

private fun getDumpdir(): String {
    val dotenv = dotenv {
        directory = "."
    }
    return dotenv["DUMP_DIR"] ?: "e:\\tmp"
}

private fun String.stripQuotes() = replace("\"", "")
private fun String.splitWithComma() = split("\",\"")
