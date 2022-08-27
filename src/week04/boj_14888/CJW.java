package week04.boj_14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CJW {
    static int N;
    static int[] input;
    static List<Integer> calcs;

    static boolean[] visited;

    static int[] answer;

    private static int calc(int a, int calc, int b) {
        switch (calc) {
            case 0: {
                return a + b;
            }
            case 1: {
                return a - b;
            }
            case 2: {
                return a * b;
            }
            case 3: {
                return a / b;
            }
        }
        return -1;
    }

    private static void perm(int depth, int a) {
        if (depth == calcs.size()) {
            answer[0] = Math.max(answer[0], a);
            answer[1] = Math.min(answer[1], a);
            return;
        }
        for (int i = 0; i < calcs.size(); i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            perm(depth + 1, calc(a, calcs.get(i), input[depth + 1]));
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        input = new int[N];
        calcs = new ArrayList<>();
        answer = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE};

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }


        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                calcs.add(i);
            }
        }

        visited = new boolean[calcs.size()];
        perm(0, input[0]);
        for (int i = 0; i < 2; i++)
            System.out.println(answer[i]);
    }
}
