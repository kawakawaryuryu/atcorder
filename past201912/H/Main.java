import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String ...args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int minStockCard = 1; //在庫が最小のカード番号
        int minStockOddCard = 1; //奇数カードの中で在庫が最小のカード番号
        Map<Integer, Integer> cards = new HashMap<>(); //<カード番号, 在庫数>のマップ
        int evenSum = 0, oddSum = 0;

        // カード
        for (int i = 1; i <= N; i++) {
            int stock = sc.nextInt();
            cards.put(i, stock);
            minStockCard = getMinStockCard(cards, i, minStockCard);

            if (i % 2 != 0) {
                minStockOddCard = getMinStockCard(cards, i, minStockOddCard);
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
                    minStockOddCard = cards.get(x) - oddSum < cards.get(minStockOddCard) - oddSum ? x : minStockOddCard;
                    int sum = minStockCard % 2 == 0 ? evenSum : oddSum;
                    minStockCard = cards.get(x) - oddSum < cards.get(minStockCard) - sum ? x : minStockCard;
                } else {
                    if (cards.get(x) - evenSum - a < 0) {
                        continue;
                    }
                    decrease(cards, x, a);
                    int sum = minStockCard % 2 == 0 ? evenSum : oddSum;
                    minStockCard = cards.get(x) - evenSum < cards.get(minStockCard) - sum ? x : minStockCard;
                }
                sellCards += a;
            } else if (query == 2) {
                int a = sc.nextInt();
                if (cards.get(minStockOddCard) - oddSum - a < 0) {
                    continue;
                }
                sellCards += a * ((1 + N) / 2);
                oddSum += a;
                int sum = minStockCard % 2 == 0 ? evenSum : oddSum;
                minStockCard = cards.get(minStockOddCard) - oddSum < cards.get(minStockCard) - sum ? minStockOddCard : minStockCard;
            } else {
                int a = sc.nextInt();
                int sum = minStockCard % 2 == 0 ? evenSum : oddSum;
                if (cards.get(minStockCard) - sum - a < 0) {
                    continue;
                }
                sellCards += a * N;
                oddSum += a;
                evenSum += a;
            }
//            System.out.println("sellCards = " + sellCards);
//            System.out.println("minStockCard = " + minStockCard);
//            System.out.println("minStockOddCard = " + minStockOddCard);
//            System.out.println(cards.entrySet()
//                    .stream()
//                    .map(e -> e.getKey() + "," + e.getValue())
//                    .collect(Collectors.joining(" ")));
        }

        System.out.println(sellCards);
    }

    private static void decrease(Map<Integer, Integer> cards, int card, int decreasedCount) {
        cards.put(card, cards.get(card) - decreasedCount);
    }

    private static int getMinStockCard(Map<Integer, Integer> cards, int card, int minStockCard) {
        if (cards.get(minStockCard) > cards.get(card)) {
            return card;
        }
        return minStockCard;
    }
}
