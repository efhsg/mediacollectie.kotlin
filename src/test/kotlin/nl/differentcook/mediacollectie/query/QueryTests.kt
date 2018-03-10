package nl.differentcook.mediacollectie.query

import nl.differentcook.mediacollectie.data.*
import org.junit.Before
import org.junit.Test

class QueryTests {

    @Before
    fun prepareTest() {
        makeDatabase()
        seedDatabase()
    }

    @Test
    fun `query on Prospero's Books movie`() {
        val movieName = "Prospero's Books"
        queryBestanden(movieName,
                {
                    println("${it[Bestanden.schijf]}:${it[Mappen.naam]}\\${it[Bestanden.naam]}.${it[Bestanden.bestandstype]}")
                    assert(it[Bestanden.schijf].equals("2014"))
                    assert(it[Mappen.naam].equals("\\Film\\Klassieker"))
                    assert(it[Bestanden.naam].equals(movieName))
                    assert(it[Bestanden.bestandstype].equals("avi"))
                }
        )
    }
}