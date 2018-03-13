package nl.differentcook.mediacollectie.query

import nl.differentcook.mediacollectie.data.createSeededDatabase
import nl.differentcook.mediacollectie.data.queryBestanden
import org.hamcrest.MatcherAssert.assertThat
import org.junit.BeforeClass
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

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
                    assert(it.schijf.equals("2016"))
                    assert(it.map.equals("\\Film\\Klassieker"))
                    assert(it.naam.equals(movieName))
                    assert(it.bestandstype.equals("avi"))
                    found.add(it.id)
                }
        )
        assertThat(found, Is(mutableListOf(1)))
    }


    @Test
    fun `query on n'existe pas`() {
        val movieName = "n'existe pas"
        queryBestanden(movieName,
                {
                    assertThat(false, Is(true))
                }
        )
    }

    @Test
    fun `query on Godfather movies`() {
        val movieName = "Godfather"
        val found: MutableList<Int> = mutableListOf()
        queryBestanden(movieName,
                {
                    found.add(it.id)
                }
        )
        assertThat(found, Is(mutableListOf(3, 2, 4)))
    }

    @Test
    fun `query with subtitles`() {
        val movieName = "Godfather"
        val found: MutableList<List<String>> = mutableListOf()
        queryBestanden(movieName,
                {
                    found.add(it.ondertitels)
                }
        )
        assertThat(found, Is(mutableListOf(listOf("nl", "en"), listOf(), listOf("nl"))))
    }

}