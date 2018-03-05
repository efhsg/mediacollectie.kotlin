package nl.differentcook.mediacollectie.query

import nl.differentcook.mediacollectie.data.Bestanden
import nl.differentcook.mediacollectie.data.Mappen
import nl.differentcook.mediacollectie.data.queryBestanden

fun main(args: Array<String>) {
    printStreep()
    printBestanden(if (args.size == 1) args[0] else null)
    printStreep()
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