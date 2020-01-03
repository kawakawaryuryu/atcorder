import java.util.*;

/**
 * 基本方針としては
 * 1. cardをオブジェクトとして管理
 * 2. クエリタイプによって見るリストや配列を変える
 *
 * 2.のクエリタイプごとに見るリストについて
 * Card[] C: cardオブジェクトをカード番号順に管理。カード番号をindexとして引ける。クエリタイプ1用
 * List<Card> oddCards: 奇数番号のcardオブジェクトをstockの枚数の少ないごとにソートされたリスト。クエリタイプ2用
 * List<Card> allCard: cardオブジェクトをstockの枚数の少ないごとにソートされたリスト。クエリタイプ3用
 */
public class Main2 {
    public static void main(String ...args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Card[] C = new Card[N+1];
        List<Card> allCards = new ArrayList<>(); //全カードを在庫の少ない順に管理するリスト
        List<Card> oddCards = new ArrayList<>(); //奇数カードを在庫の少ない順に管理するリスト
        C[0] = new Card(1000000001, -1);
        // カード
        for (int i = 1; i <= N; i++) {
            int stock = sc.nextInt();
            Card card = new Card(stock, i);
            C[i] = card;

            int index = insertedIndex(allCards, card);
            allCards.add(index, card);
            if (i % 2 != 0) {
                index = insertedIndex(oddCards, card);
                oddCards.add(index, card);
            }
        }
        int Q = sc.nextInt();

        int sellCards = 0;

        sc.nextLine();
        // クエリ処理
        for (int i = 0; i < Q; i++) {
            String S = sc.nextLine();
            String[] query = S.split(" ");

            if (query[0].equals("1")) {
                int x = Integer.parseInt(query[1]);
                int a = Integer.parseInt(query[2]);
                if (C[x].stock - a < 0) {
                    continue;
                }
                sellCards = decrease(C, x, a, allCards, oddCards, sellCards);
                sort(C[x], allCards);
                if (C[x].number % 2 != 0) {
                    sort(C[x], oddCards);
                }
            } else if (query[0].equals("2")) {
                int a = Integer.parseInt(query[1]);
                if (oddCards.get(0).stock - a < 0) {
                    continue;
                }
                for (int j = 1; j <= N; j++) {
                    if (j % 2 != 0) {
                        sellCards = decrease(C, j, a, allCards, oddCards, sellCards);
                    }
                }
            } else {
                int a = Integer.parseInt(query[1]);
                if (allCards.get(0).stock - a < 0) {
                    continue;
                }
                for (int j = 1; j <= N; j++) {
                    sellCards = decrease(C, j, a, allCards, oddCards, sellCards);
                }
            }
        }

        System.out.println(sellCards);
    }

    private static int insertedIndex(List<Card> cards, Card card) {
        int result = Collections.binarySearch(cards, card, Comparator.comparing(card1 -> card1.stock));
        return result >= 0 ? result : ~result;
    }

    private static int decrease(Card[] C, int x, int a, List<Card> cards, List<Card> oddCards, int sellCards) {
        sellCards = sellCards + a;
        C[x].stock = C[x].stock - a;
        return sellCards;
    }

    private static void sort(Card sortedCard, List<Card> cards) {
        cards.remove(sortedCard);
        int index = insertedIndex(cards, sortedCard);
        cards.add(index, sortedCard);
    }

    private static class Card {
        int stock;
        final int number;

        public Card(int stock, int number) {
            this.stock = stock;
            this.number = number;
        }
    }
}
