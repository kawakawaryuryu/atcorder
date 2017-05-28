import java.util.Scanner;

public class Main {

    static long H;
    static long W;

    public static void main(String[] args) {
    
        Scanner sc = new Scanner(System.in);

        H = sc.nextLong();
        W = sc.nextLong();

        // 縦方向か横方向に一つ目を分割し、
        // 二つ目三つ目を縦か横に分割する
        System.out.println(divide());
    }

    private static long divide() {

        if (H % 3 == 0 || W % 3 == 0) {

            return 0;

        } else {

            long hDividedDiff = calcDiff(H, W);
            long wDividedDiff = calcDiff(W, H);

            return Math.min(hDividedDiff, wDividedDiff);
        }

    }

    private static long calcDiff(long divlen1, long divlen2) {

        long diff = divlen1 * divlen2;

        for (int i = 1; i < divlen1; i++) {
            long s1 = i * divlen2;

            // 一つ目
            long s2 = (int)((divlen1 - i) / 2) * divlen2;
            long s3 = divlen1 * divlen2 - s1 - s2;

            diff = minDiff(s1, s2, s3, diff);

            // 二つ目
            s2 = (divlen1 - i) * (int)(divlen2 / 2);
            s3 = divlen1 * divlen2 - s1 - s2;

            diff = minDiff(s1, s2, s3, diff);
        }

        return diff;
    }

    private static long minNum(long a, long b, long c) {
        return Math.min(Math.min(a, b), c);
    }

    private static long maxNum(long a, long b, long c) {
        return Math.max(Math.max(a, b), c);
    }

    private static long minDiff(long s1, long s2, long s3, long minDiff) {
    
        long diff = maxNum(s1, s2, s3) - minNum(s1, s2, s3); 

        if (minDiff > diff) {
            minDiff = diff;
        }

        return minDiff;
    }
}
