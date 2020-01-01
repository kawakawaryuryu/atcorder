import java.util.Scanner;

public class Main {
    public static void main(String ...args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int yesterday = scanner.nextInt();
        for (int i = 2; i <= N; i++) {
            int sales = scanner.nextInt();
            if (sales == yesterday) {
                System.out.println("stay");
            } else if (yesterday > sales) {
                System.out.println("down " + (yesterday - sales));
            } else {
                System.out.println("up " + (sales - yesterday));
            }
            yesterday = sales;
        }
    }
}
