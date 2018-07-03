import java.util.Scanner

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    val k = sc.nextInt()

    val a = IntArray(n)
    var minIndex: Int = -1
    for (i in 0 until n) {
        a[i] = sc.nextInt()
        if (a[i] == 1) {
            minIndex = i
        }
    }

    println(replaceLeft(minIndex, minIndex, n, k))
}

fun replaceLeft(leftIndex: Int, rightIndex: Int, n: Int, k: Int): Int {
    if (leftIndex - k + 1 <= 0) {
        val right = rightIndex - (leftIndex - k + 1)
        return replaceRight(right, n, k) + 1
    }
    return replaceLeft(leftIndex - k + 1, rightIndex, n, k) + 1
}

fun replaceRight(rightIndex: Int, n: Int, k: Int): Int {
    if (rightIndex >= n - 1) {
        return 0
    }
    return replaceRight(rightIndex + k - 1, n, k) + 1
}
