import java.util.Scanner;

public class Main {
    public static void main(String ...args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        try {
            int number = Integer.parseInt(input);
            System.out.println(number * 2);
        } catch (NumberFormatException e) {
            System.out.println("error");
            System.exit(0);
        }
    }
}
