import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String ...args) {
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        List<String> dictionary = new ArrayList<>();

        int upperCount = 0;
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            charList.add(S.charAt(i));
            if (Character.isUpperCase(S.charAt(i))) {
                upperCount++;
                if (upperCount == 2) {
                    String word = charList.stream().map(String::valueOf).collect(Collectors.joining());
                    int result = Collections.binarySearch(dictionary, word, String::compareToIgnoreCase);
                    int index = result >= 0 ? result : ~result;
                    dictionary.add(index, word);
                    upperCount = 0;
                    charList.clear();
                }
            }
        }

        System.out.println(String.join("", dictionary));
    }
}
