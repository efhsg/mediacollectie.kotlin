package nl.differentcook.mediacollectie.query

import nl.differentcook.mediacollectie.data.*
import org.junit.BeforeClass
import org.junit.Test

class QueryTests {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            makeDatabase()
            seedDatabase()
        }
    }

    @Test
    fun `query on Prospero's Books movie`() {
        val movieName = "Prospero's Books"
        val found = queryBestanden(movieName,
                {
                    assert(it[Bestanden.schijf].equals("2014"))
                    assert(it[Mappen.naam].equals("\\Film\\Klassieker"))
                    assert(it[Bestanden.naam].equals(movieName))
                    assert(it[Bestanden.bestandstype].equals("avi"))
                }
        )
        assert(found.contains(1877))
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