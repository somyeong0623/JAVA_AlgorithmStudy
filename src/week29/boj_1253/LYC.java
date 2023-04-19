import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, List<Pair>> map = makeSet(numbers);

        int res = 0;
        for (int idx = 0; idx < numbers.length; idx++) {
            if (map.containsKey(numbers[idx])) {
                for (Pair pair: map.get(numbers[idx])) {
                    if (pair.first == idx || pair.second == idx) continue;
                    res++;
                    break;
                }
            }
        }

        System.out.println(res);
    }

    private static Map<Integer, List<Pair>> makeSet(int[] numbers) {
        Map<Integer, List<Pair>> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                int sum = numbers[i] + numbers[j];
                if (map.containsKey(sum)) {
                    map.get(sum).add(new Pair(i, j));
                } else {
                    List<Pair> tmp = new ArrayList<>();
                    tmp.add(new Pair(i, j));
                    map.put(sum, tmp);
                }
            }
        }
        return map;
    }

    private static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }
}