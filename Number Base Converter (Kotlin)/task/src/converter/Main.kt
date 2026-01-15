package converter // Do not delete this line

import java.math.BigInteger

fun baseToDecimal(numberStr: String, sourceBase: Int): BigInteger  {
    return numberStr.reversed()
        .mapIndexed { index, char ->
            val digitValue = when (char.uppercaseChar()) {
                in '0'..'9' -> char - '0'
                in 'A'..'Z' -> 10 + (char.uppercaseChar() - 'A')
                else -> throw IllegalArgumentException("Invalid digit: $char")
            }
            BigInteger.valueOf(digitValue.toLong())
                .multiply(BigInteger.valueOf(sourceBase.toLong()).pow(index))
        }
        .fold(BigInteger.ZERO) { acc, value -> acc + value }
}

fun decimalToBase(decimal: BigInteger, targetBase: Int): String {
    if (decimal == BigInteger.ZERO) return "0"

    return generateSequence (decimal ) {
        it.divide(BigInteger.valueOf(targetBase.toLong()))
    }
        .takeWhile { it > BigInteger.ZERO }
        .map { it.mod(BigInteger.valueOf(targetBase.toLong())) }
        .toList()
        .asReversed()
        .joinToString("") { digit ->
            when (digit.toInt()) {
                in 0..9 -> digit.toString()
                10 -> "A"
                11 -> "B"
                12 -> "C"
                13 -> "D"
                14 -> "E"
                15 -> "F"
                16 -> "G"
                17 -> "H"
                18 -> "I"
                19 -> "J"
                20 -> "K"
                21 -> "L"
                22 -> "M"
                23 -> "N"
                24 -> "O"
                25 -> "P"
                26 -> "Q"
                27 -> "R"
                28 -> "S"
                29 -> "T"
                30 -> "U"
                31 -> "V"
                32 -> "W"
                33 -> "X"
                34 -> "Y"
                35 -> "Z"
                else -> throw IllegalArgumentException("Digit too large: $digit")
            }
        }
}

fun main() {
    do {
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        val input = readln().trim()
        if (input == "/exit") return

        val (sourceBaseStr, targetBaseStr) = input.split(" ")
        val sourceBase = sourceBaseStr.toInt()
        val targetBase = targetBaseStr.toInt()

        do {
            println("Enter number in base $sourceBase to convert to base $targetBase (To go back type /back)")
            val numberStr = readln().trim()
            if (numberStr.lowercase() == "/back") break

            val decimal = baseToDecimal(numberStr, sourceBase)
            val result = decimalToBase(decimal, targetBase)
            println("Conversion result: $result")
        } while (true)
    } while (true)
}
