import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String ...args) {
        Scanner sc = new Scanner(System.in);
        final int size = 6;
        int[] number = new int[size];
        Arrays.fill(number, -1);
        int val = sc.nextInt();
        number[0] = val;
        for (int i = 1; i < size; i++) {
            val = sc.nextInt();
            for (int j = 0; j <= i; j++) {
                if (number[j] < val) {
                    int stash = number[j];
                    number[j] = val;
                    val = stash;
                }
            }
        }

        System.out.println(number[2]);
    }
}
