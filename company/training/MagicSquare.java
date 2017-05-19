import java.util.Scanner;

public class MagicSquare {

    static int result[][];
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("input odd number:");

        N = sc.nextInt();
        sc.close();

        result = new int[N][N];

        fill(N/2 + 1, N/2, 1);

        for (int[] res : result) {
            for (int r : res) {
                System.out.printf("%2d ", r);
            }
            System.out.println();
        }
    }

    private static void fill(int vertical, int horizontal, int number) {
        int nextVertical = vertical;
        int nextHorizontal = horizontal;

        if (number <= N * N ) {
            if (0 <= vertical && vertical < N && 0 <= horizontal && horizontal < N) {
                if (result[vertical][horizontal] != 0) {
                    nextVertical = vertical + 1;
                    nextHorizontal = horizontal - 1;
                }
                else {
                    result[vertical][horizontal] = number;
                    nextVertical += 1;
                    nextHorizontal += 1;
                    number += 1;
                }
            }
            else {
                if (vertical >= N) {
                    nextVertical = 0;   
                }
                if (horizontal < 0) {
                    nextHorizontal = N - 1;
                }
                if (horizontal >= N) {
                    nextHorizontal = 0;
                }
            }
            fill(nextVertical, nextHorizontal, number);
        }
    }
}
