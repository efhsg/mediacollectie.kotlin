package nl.differentcook.mediacollectie.query

import nl.differentcook.mediacollectie.data.queryBestanden

fun main(args: Array<String>) {
    printStreep()
    printBestanden(if (args.size == 1) args[0] else null)
    printStreep()
}

private fun printBestanden(zoekTerm: String?) {
    val found = mutableListOf<Int>()
    queryBestanden(zoekTerm,
            {
                print("[${it.schijf}]:${it.map}\\${it.naam}.${it.bestandstype}")
                if (!it.ondertitels.isEmpty()) println(", subs: ${it.ondertitels}") else println("")
                found.add(it.id)            }
    )
    println("Records found: ${found.size}")
}

private fun printStreep() {
    println("=".streep())
}

private fun String.streep() = repeat(80)