package nl.differentcook.mediacollectie.query

import nl.differentcook.mediacollectie.data.*

fun main(args: Array<String>) {
    destroyDatabase()
    makeDatabase()
    seedDatabase()
//    printStreep()
//    printBestanden(if (args.size == 1) args[0] else null)
//    printStreep()
}

private fun printBestanden(zoekTerm: String?) {
    queryBestanden(zoekTerm,
            {
                println("${it[Bestanden.schijf]}:${it[Mappen.naam]}\\${it[Bestanden.naam]}.${it[Bestanden.bestandstype]}")
            }
    )
}

private fun printStreep() {
    println("=".streep())
}

private fun String.streep() = repeat(80)