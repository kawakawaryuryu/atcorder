import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class Infix {

    public static void main(String[] args) {

        Infix infix = new Infix();
        infix.run();
    }

    private void run() {

        // 入力
        System.out.print("中間記法で入力してください:");
        String[] formula = input();

        List<String> reversedPolish = new ArrayList<String>();
        Deque<String> operators = new ArrayDeque<String>();
        Deque<Integer> numbers = new ArrayDeque<Integer>();

        try {
            // 入力値チェック
            checkInput(formula);

            // 計算
            convertToReversedPolish(formula, 0, reversedPolish, operators);

            //reversedPolish.stream().forEach(x -> System.out.print(x + " "));

            // 中間記法から書き換えた式を計算
            calculate(reversedPolish, 0, numbers);

            if (numbers.size() != 1) {
                throw new Exception("値の数と演算子の数が適切ではありません");
            }
        } catch(NoSuchElementException e) {
            e.printStackTrace();
        } catch(IllegalStateException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // 結果の表示
        System.out.println("答え:" + numbers.pop());

    }

    private String[] input() throws NoSuchElementException, IllegalStateException {
        Scanner sc = new Scanner(System.in);
        String[] words = null;

        try {
            words = sc.nextLine().split(" ");
        } catch(NoSuchElementException e) {
            throw new NoSuchElementException("行が見つかりません");
        } catch(IllegalStateException e) {
            throw new IllegalStateException("クローズされていません");
        } finally {
            sc.close();
        }

        return words;
    }

    private void checkInput(String[] words) throws Exception {
        List<String> correctWords = new ArrayList<String>(Arrays.asList("+", "-", "*", "/", "(", ")"));
        for (int i = 0; i <= 9; i++) {
            correctWords.add(Integer.toString(i));
        }

        for (String word : words) {
            if (!correctWords.contains(word)) {
                throw new Exception("入力値が不正です");
            }
        }
    }

    private void convertToReversedPolish(String[] formula, int index, List<String> reversedPolish, Deque<String> operators) throws Exception  {
        // インデックスが式の文字数以下だったら
        if (index < formula.length) {
            String word = formula[index];

            if (word.equals("+") || word.equals("-")) {
                // 前に保持した演算子があればそれを取り出す
                String operator = Optional.ofNullable(operators.peek()).orElse("(");
                if (!operator.equals("(")) {
                    reversedPolish.add(operators.pop());
                }
                operators.push(word);
            }
            else if (word.equals("*") || word.equals("/")) {
                String operator = Optional.ofNullable(operators.peek()).orElse("");
                if (operator.equals("*") || operator.equals("/")) {
                    // 前の演算子を優先する
                    Optional.ofNullable(operators).ifPresent(op -> reversedPolish.add(op.pop()));
                }
                operators.push(word);
            }
            else if (word.equals("(")) {
                operators.push(word);
            }
            else if (word.equals(")")) {
                // 括弧が始まってからの全ての演算子を取り出す
                String operator = null;
                while (!(operator = operators.pop()).equals("(")) {
                    reversedPolish.add(operator);
                }
            }
            else {
                // 数字であれば逆ポーランドリストに入れる
                reversedPolish.add(word);
            }
            convertToReversedPolish(formula, index + 1, reversedPolish, operators);
        }
        else {
            // 残っている演算子を入れる
            while (!operators.isEmpty()) {
                reversedPolish.add(operators.pop());
            }
        }
    }

    private void calculate(List<String> formula, int index, Deque<Integer> numbers) throws Exception  {
        // インデックスが式の文字数以下だったら
        if (index < formula.size()) {
            String word = formula.get(index);

            if (word.equals("+") || word.equals("-") || word.equals("*") || word.equals("/")) {
                int n1 = 0;
                int n2 = 0;

                try {
                    n1 = numbers.pop();
                    n2 = numbers.pop();
                } catch(NoSuchElementException e) {
                    System.out.println("値の数と演算子の数が適切ではありません");
                    e.printStackTrace();
                    System.exit(-1);
                }


                numbers.push(getAnswer(n1, n2, word));
            }
            else {
                numbers.push(Integer.parseInt(word));
            }
            calculate(formula, index + 1, numbers);
        }
    }

    private int getAnswer(int n1, int n2, String operator) throws Exception {

        if (operator.equals("+")) {
            return n1 + n2;
        }
        else if (operator.equals("-")) {
            return n2 - n1;
        }
        else if (operator.equals("*")) {
            return n1 * n2;
        }
        else if (operator.equals("/")) {
            return n2 / n1;
        }
        else {
            throw new Exception("ありえない演算子です");
        }
    }
}
