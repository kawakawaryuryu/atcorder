import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val input = scanner.nextLine()
    val value: CharArray = input.toCharArray()

    var number = 0
    for (v in value) {
        if (v == '+') number++
        if (v == '-') number--
    }

    println(number)
}