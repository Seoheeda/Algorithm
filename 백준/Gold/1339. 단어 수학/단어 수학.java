import java.io.*;
import java.util.*;

public class Main {

    public static void solution(String[] words, int N) throws IOException {
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> valueMap = new HashMap<>();
        
        // 1. Compute the importance of each character
        for (String word : words) {
            int length = word.length();
            int multiplier = (int)Math.pow(10, length - 1);
            for (char c : word.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + multiplier);
                multiplier /= 10;
            }
        }
        
        // 2. Sort characters by their importance (descending order)
        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(map.entrySet());
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        // 3. Assign digits to characters with the highest importance first
        int value = 9;
        for (Map.Entry<Character, Integer> entry : entries) {
            valueMap.put(entry.getKey(), value--);
        }
        
        // 4. Compute the final result using the assigned values
        int sum = 0;
        for (String word : words) {
            int currentNumber = 0;
            for (char c : word.toCharArray()) {
                currentNumber = currentNumber * 10 + valueMap.get(c);
            }
            sum += currentNumber;
        }
        
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];
        for (int w = 0; w < N; w++) {
            words[w] = br.readLine().trim();
        }
        
        solution(words, N);
    }
}