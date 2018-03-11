package nl.differentcook.mediacollectie.query

import nl.differentcook.mediacollectie.data.Bestanden
import nl.differentcook.mediacollectie.data.Mappen
import nl.differentcook.mediacollectie.data.createSeededDatabase
import nl.differentcook.mediacollectie.data.queryBestanden
import org.junit.BeforeClass
import org.junit.Test

class QueryTests {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setup() {
            createSeededDatabase()
        }
    }

    @Test
    fun `query on Prospero's Books movie`() {
        val movieName = "Prospero's Books"
        val found: MutableList<Int> = mutableListOf()
        queryBestanden(movieName,
                {
                    assert(it[Bestanden.schijf].equals("2016"))
                    assert(it[Mappen.naam].equals("\\Film\\Klassieker"))
                    assert(it[Bestanden.naam].equals(movieName))
                    assert(it[Bestanden.bestandstype].equals("avi"))
                    found.add(it[Bestanden.id])
                }
        )
        assert(found.size == 1)
        assert(found.contains(1))
    }


    @Test
    fun `query on n'existe pas`() {
        val movieName = "n'existe pas"
        queryBestanden(movieName,
                {
                    assert(false)
                }
        )
    }

    @Test
    fun `query on Godfather movies`() {
        val movieName = "Godfather"
        val found: MutableList<Int> = mutableListOf()
        queryBestanden(movieName,
                {
                    found.add(it[Bestanden.id])
                }
        )
        assert(found.size == 3)
        assert(found.contains(2))
        assert(found.contains(3))
        assert(found.contains(4))
    }

}