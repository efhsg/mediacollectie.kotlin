package nl.differentcook.mediacollectie.query

import nl.differentcook.mediacollectie.data.*
import org.junit.BeforeClass
import org.junit.Test

class QueryTests {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            destroyDatabase()
            makeDatabase()
            seedDatabase()
        }
    }

    @Test
    fun `query on Prospero's Books movie`() {
        val movieName = "Prospero's Books"
        val found = queryBestanden(movieName,
                {
                    assert(it[Bestanden.schijf].equals("2016"))
                    assert(it[Mappen.naam].equals("\\Film\\Klassieker\\"))
                    assert(it[Bestanden.naam].equals(movieName))
                    assert(it[Bestanden.bestandstype].equals("avi"))
                }
        )
        assert(found.size == 1)
        assert(found.contains(1))
    }


    @Test
    fun `query on n'existe pas`() {
        val movieName = "n'existe pas"
        val found = queryBestanden(movieName,
                {
                    assert(false)
                }
        )
        assert(found.isEmpty())
    }


}