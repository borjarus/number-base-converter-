package converter // Do not delete this line

import kotlin.math.pow

fun decimalToBase(number: Int, targetBase: Int): String {
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

fun baseToDecimal(number: String, sourceBase: Int): Int {
    return number.reversed()
        .mapIndexed { index, char ->
            val digitValue = when (char.uppercaseChar()){
                in '0'..'9' -> char - '0'
                'A' -> 10
                'B' -> 11
                'C' -> 12
                'D' -> 13
                'E' -> 14
                'F' -> 15
                else -> throw IllegalArgumentException("Invalid digit: $char")
            }
            digitValue * sourceBase.toDouble().pow(index).toInt()
        }
        .sum()
}

fun fromDecimal(){
    println("Enter number in decimal system: ")
    val number = readln().toInt()
    println("Enter target base: ")
    val targetBase = readln().toInt()
    println("Conversion result: ${decimalToBase(number, targetBase)}")
}

fun toDecimal(){
    println("Enter source number: ")
    val sourceNumber = readln().trim()
    println("Enter source base: ")
    val sourceBase = readln().toInt()
    println("Conversion to decimal result: ${baseToDecimal(sourceNumber, sourceBase)}")
}

fun main() {
    do {
        println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)")
        when (val command = readln().trim().lowercase()) {
            "/exit" -> return
            "/from" -> fromDecimal()
            "/to" -> toDecimal()
            else -> println("Invalid command")
        }
    } while (true)
}
