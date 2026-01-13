package converter // Do not delete this line

fun convert(number: Int, targetBase: Int): String {
    if (number == 0) return "0"

     return generateSequence (number){ it / targetBase }
         .takeWhile { it > 0 }
         .map { it % targetBase }
        .toList()
        .asReversed()
        .joinToString("") {
            when (it){
                10 -> "A"
                11 -> "B"
                12 -> "C"
                13 -> "D"
                14 -> "E"
                15 -> "F"
                else -> it.toString()
            }
        }
}

fun main() {
    println("Enter number in decimal system: ")
    val number = readln().toInt()

    println("Enter target base: ")
    val targetBase = readln().toInt()

    println("Conversion result: ${convert(number, targetBase)}")

}