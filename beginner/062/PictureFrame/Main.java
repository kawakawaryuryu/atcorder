import java.util.Scanner;

public class Main {

    static int H;
    static int W;
    static String[][] a;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        H = sc.nextInt();
        W = sc.nextInt();

        a = new String[H+2][W+2];

        for (int i = 1; i <= H; i++) {
            String[] str = sc.next().split("");
            for (int j = 1; j <= W; j++) {
                a[i][j] = str[j - 1];
            }
        }

        showPixel();
    }

    private static void showPixel() {
        for (int i = 0; i < H+2; i++) {
            for (int j = 0; j < W+2; j++) {
                if (i == 0 || i == H+1 || j == 0 || j == W+1) {
                    System.out.print("#");
                } else {
                    System.out.print(a[i][j]);
                }
            }
            System.out.println();
        }
    }
}
