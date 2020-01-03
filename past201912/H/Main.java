import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String ...args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int minEvenStock = 1000000001; //偶数カードの最少の在庫数
        int minOddStock = 1000000001; //奇数カードの最少の在庫数
        Map<Integer, Integer> cards = new HashMap<>(); //<カード番号, 在庫数>のマップ
        int evenSum = 0, oddSum = 0;

        // カード
        for (int i = 1; i <= N; i++) {
            int stock = sc.nextInt();
            cards.put(i, stock);

            if (i % 2 != 0) {
                minOddStock = Math.min(stock, minOddStock);
            } else {
                minEvenStock = Math.min(stock, minEvenStock);
            }
        }
        int Q = sc.nextInt();

        long sellCards = 0;

        sc.nextLine();
        // クエリ処理
        for (int i = 0; i < Q; i++) {
            int query = sc.nextInt();

            if (query == 1) {
                int x = sc.nextInt();
                int a = sc.nextInt();
                if (x % 2 != 0) {
                    if (cards.get(x) - oddSum - a < 0) {
                        continue;
                    }
                    decrease(cards, x, a);
                    minOddStock = Math.min(cards.get(x) - oddSum, minOddStock);
                } else {
                    if (cards.get(x) - evenSum - a < 0) {
                        continue;
                    }
                    decrease(cards, x, a);
                    minEvenStock = Math.min(cards.get(x) - evenSum, minEvenStock);
                }
                sellCards += a;
            } else if (query == 2) {
                int a = sc.nextInt();
                if (minOddStock - a < 0) {
                    continue;
                }
                sellCards += a * ((1 + N) / 2);
                oddSum += a;
                minOddStock -= a;
            } else {
                int a = sc.nextInt();
                int stock = Math.min(minOddStock, minEvenStock);
                if (stock - a < 0) {
                    continue;
                }
                sellCards += a * N;
                oddSum += a;
                evenSum += a;
                minOddStock -= a;
                minEvenStock -= a;
            }
        }

        System.out.println(sellCards);
    }

    private static void decrease(Map<Integer, Integer> cards, int card, int decreasedCount) {
        cards.put(card, cards.get(card) - decreasedCount);
    }
}
