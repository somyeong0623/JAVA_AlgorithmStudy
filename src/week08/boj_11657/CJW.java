package week08.boj_11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class CJW {
    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

    }

    static long[] d;
    static int N;
    static int M;
    static List<Edge> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        d = new long[N + 1];

        list = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.add(new Edge(from, to, weight));
        }
        Arrays.fill(d, Long.MAX_VALUE);
        d[1] = 0;
        boolean res = true;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Edge cur = list.get(j);

                if (d[cur.from] != Long.MAX_VALUE && d[cur.to] > d[cur.from] + cur.weight) {
                    d[cur.to] = d[cur.from] + cur.weight;

                    // 순환 발견
                    if (i == N - 1)
                        res = false;
                }
            }
        }

        if (res) {
            for (int i = 2; i <= N; i++) {
                if (d[i] != Long.MAX_VALUE)
                    System.out.println(d[i]);
                else
                    System.out.println(-1);
            }
        } else {
            System.out.println(-1);
        }
    }
}