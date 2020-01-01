import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String ...args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        boolean[] A = new boolean[N+1];
        Arrays.fill(A, false);
        int y = 0;
        for (int i = 1; i <= N; i++) {
            int input = sc.nextInt();
            if (!A[input]) {
                A[input] = true;
            } else {
                y = input;
            }
        }

        int x = 0;
        boolean isCollect = true;
        for (int i = 1; i <= N; i++) {
            if (!A[i]) {
                x = i;
                isCollect = false;
                break;
            }
        }
        if (!isCollect) {
            System.out.println(y + " " + x);
        } else {
            System.out.println("Correct");
        }
    }
}
