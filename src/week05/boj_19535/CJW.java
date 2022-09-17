package week05.boj_19535;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CJW {
    static void dfs(int depth) {

    }

    static Map<Integer, List<Integer>> adjList;
    static long d;
    static long g;

    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        adjList = new HashMap<>();
        StringTokenizer st;
        d = 0;
        g = 0;
        visited = new boolean[N + 1];
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a, b;
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (!adjList.containsKey(a))
                adjList.put(a, new ArrayList<>());

            if (!adjList.containsKey(b))
                adjList.put(b, new ArrayList<>());

            adjList.get(a).add(b);
            adjList.get(b).add(a);
        }

        // Find G
        for (int i = 1; i <= N; i++) {
            if (adjList.get(i).size() >= 3) {
                int n = adjList.get(i).size();
                // nCr 공식
                g += ((long) n * (n - 1) * (n - 2)) / 6;
            }
        }


        // Find D
        for (int i = 1; i <= N; i++) {
            long child = adjList.get(i).size();
            for (int j = 0; j < child; j++) {
                if (visited[adjList.get(i).get(j)])
                    continue;

                // (현재노드 간선 수 - 1) * (연결된 노드 간선 수 - 1)
                d += (child - 1) * (adjList.get(adjList.get(i).get(j)).size() - 1);
            }

            visited[i] = true;
        }

        if (g * 3 < d) System.out.println("D");
        else if (g * 3 > d) System.out.println("G");
        else System.out.println("DUDUDUNGA");

    }
}
