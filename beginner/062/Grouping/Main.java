import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Main {

    static List<Integer> A;
    static List<Integer> B;
    static List<Integer> C;

    public static void main(String args[]) {

        A = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
        B = Arrays.asList(4, 6, 9, 11);
        C = Arrays.asList(2);
 
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        String answer = isSameGroup(x, y) ? "Yes" : "No";
        System.out.println(answer);
    }

    private static boolean isSameGroup(int x, int y) {
        if ((A.contains(x) && A.contains(y))
           || (B.contains(x) && B.contains(y))
           || (C.contains(x) && C.contains(y))) {
            return true;
        } else {
            return false;
        }

    }
}
