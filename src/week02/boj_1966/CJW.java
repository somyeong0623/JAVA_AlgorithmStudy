package week02.boj_1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CJW {
    static class Pair {
        public int score, idx;

        public Pair(int score, int idx) {
            this.score = score;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 0; tc < T; tc++) {
            Queue<Pair> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine(), " ");
            int size, print;
            size = Integer.parseInt(st.nextToken());
            print = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < size; i++) {
                queue.offer(new Pair(Integer.parseInt(st.nextToken()), i));
            }
            int cnt = 0;
            while (!queue.isEmpty()) {
                boolean result = true;
                Pair pop = queue.poll();
                for (Pair p : queue) {
                    if (p.score > pop.score) {
                        result = false;
                        queue.offer(pop);
                        break;
                    }
                }

                if (result) {
                    cnt++;
                    if (pop.idx == print) {
                        System.out.println(cnt);
                        break;
                    }
                }
            }
        }
    }
}
