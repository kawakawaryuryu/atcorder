import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String ...args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] a = new int[N][N];
        for (int i = 1; i < N; i++) {
            Arrays.fill(a[i], 0);
            for (int j = i + 1; j <= N; j++) {
                int score = sc.nextInt();
                a[i][j] = score;
                a[j][i] = score;
            }
        }
    }
}
