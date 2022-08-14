package week02.boj_4358;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CJW {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> data = new HashMap<>();
        Set<String> kind = new HashSet<>();
        int total = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            total++;
            String input = br.readLine();
            if (input == null || input.isEmpty())
                break;
            if (!data.containsKey(input)) {
                data.put(input, 0);
                kind.add(input);
            }
            data.put(input, data.get(input) + 1);
        }
        List<String> order = new ArrayList<>(kind);
        Collections.sort(order);
        for (String item : order) {
            System.out.printf("%s %.4f%n", item, data.get(item) * 1.0 / total * 100);

        }
    }
}
