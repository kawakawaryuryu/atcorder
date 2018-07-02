import java.util.Scanner

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val input = sc.nextInt()
    var sum = 0

    var n = input
    sum += n % 10
    while (n / 10 != 0) {
        n /= 10
        sum += n % 10
    }

    if (input % sum == 0) {
        println("Yes")
    } else {
        println("No")
    }
}