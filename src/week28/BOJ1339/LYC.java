import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static char[][] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        words = new char[n][];
        boolean[] duple = new boolean[26];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine().toCharArray();
            for (int j = 0; j < words[i].length; j++) {
                int idx = words[i][j] - 'A';
                if (duple[idx]) continue;
                duple[idx] = true;
                sb.append(words[i][j]);
            }
        }

        char[] chars = sb.toString().toCharArray();
        Arrays.sort(chars);
        maxNum = (calculate(chars));
        while(nextPermutation(chars));
        System.out.println(maxNum);
    }

    private static int maxNum = 0;

    private static boolean nextPermutation(char[] input) {
        int idx = input.length - 1;
        while (idx > 0 && (input[idx - 1] > input[idx])) idx--;

        if (idx == 0) return false;

        int bigIdx = input.length - 1;
        while (bigIdx > idx && input[idx - 1] > input[bigIdx]) bigIdx--;

        char tmp = input[idx - 1];
        input[idx - 1] = input[bigIdx];
        input[bigIdx] = tmp;

        Arrays.sort(input, idx, input.length);
//        System.out.println(Arrays.toString(input));
        maxNum = Math.max(maxNum, calculate(input));
        return true;
    }

    private static int calculate(char[] input) {
        Map<Character, Integer> map = new HashMap<>();
        for (int idx = 0; idx < input.length; idx++) {
            map.put(input[idx], 9 - idx);
        }
        int sum = 0;
        for (char[] word : words) {
            int num = 0;
            for (char c : word) {
                num = (num * 10) + map.get(c);
            }
            sum += num;
        }
        return sum;
    }
}