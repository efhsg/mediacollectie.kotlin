package nl.differentcook.mediacollectie.data

import io.github.cdimascio.dotenv.dotenv
import org.jetbrains.exposed.sql.Database

fun getDatabase() {
    val dotenv = dotenv {
        directory = "."
    }

    Database.connect(dotenv["DB_URL"] ?: "", driver = dotenv["DB_DRIVER"] ?: "",
            user = dotenv["DB_USER"] ?: "", password = dotenv["DB_PASSWORD"] ?: "")
}