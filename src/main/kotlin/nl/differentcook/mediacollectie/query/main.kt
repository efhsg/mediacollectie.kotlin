package nl.differentcook.mediacollectie.query

import nl.differentcook.mediacollectie.data.queryBestanden

fun main(args: Array<String>) {
    printStreep()
    printBestanden(if (args.size == 1) args[0] else null)
    printStreep()
}

private fun printBestanden(zoekTerm: String?) {
    var found = 0
    queryBestanden(zoekTerm,
            {
                println("[${it.schijf}]:${it.map}\\${it.naam}.${it.bestandstype}")
                found++
            }
    )
    println("Records found: ${found}")
}

private fun printStreep() {
    println("=".streep())
}

private fun String.streep() = repeat(80)