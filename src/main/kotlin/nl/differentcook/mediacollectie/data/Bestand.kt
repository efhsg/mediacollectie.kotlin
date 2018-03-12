package nl.differentcook.mediacollectie.data

import org.joda.time.DateTime

data class Bestand(
        val id: Int,
        val schijf: String,
        val map: String,
        val naam: String,
        val bestandstype: String,
        val grootte: Int,
        val created_at: DateTime,
        val updated_at: DateTime
)