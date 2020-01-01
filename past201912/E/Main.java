import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String ...args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();
        char[][] f = new char[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(f[i], 'N');
        }

        sc.nextLine();
        for (int i = 0; i < Q; i++) {
            String S = sc.nextLine();
            String[] parsedLog = S.split(" ");

            if (parsedLog[0].equals("1")) {
                int a = Integer.parseInt(parsedLog[1]) - 1;
                int b = Integer.parseInt(parsedLog[2]) - 1;
                f[a][b] = 'Y';
            } else if (parsedLog[0].equals("2")) {
                int a = Integer.parseInt(parsedLog[1]) - 1;
                for (int j = 0; j < N; j++) {
                    if (j == a) {
                        continue;
                    }
                    if (f[j][a] == 'Y') {
                        f[a][j] = 'Y';
                    }
                }
            } else {
                int a = Integer.parseInt(parsedLog[1]) - 1;
                List<Integer> followee = new ArrayList<>();
                for (int x = 0; x < N; x++) {
                   if (f[a][x] == 'Y') {
                        followee.add(x);
                   }
                }
                followee.forEach(x -> {
                    if (f[a][x] == 'Y') {
                        for (int j = 0; j < N; j++) {
                            if (j == a) {
                                continue;
                            }
                            if (f[x][j] == 'Y') {
                                f[a][j] = 'Y';
                            }
                        }
                    }
                });
            }
        }

        Arrays.stream(f).forEach(System.out::println);
    }
}
