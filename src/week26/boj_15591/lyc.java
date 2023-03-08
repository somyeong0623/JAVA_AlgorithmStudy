import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Element {
        int num, usado;

        Element(int num, int usado) {
            this.num = num;
            this.usado = usado;
        }
    }

    private static final int SIZE = 5001;
    private static final List<Element>[] adj = new ArrayList[SIZE];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            adj[p].add(new Element(q, r));
            adj[q].add(new Element(p, r));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            sb.append(bfs(v, k)).append('\n');
        }
        System.out.println(sb);
    }

    private static int bfs(int start, int k) {
        Queue<Element> queue = new ArrayDeque<>();
        boolean[] visit = new boolean[SIZE];
        visit[start] = true;
        int ret = 0;
        queue.add(new Element(start, 0));

        while (!queue.isEmpty()) {
            Element cur = queue.poll();
            for (Element next: adj[cur.num]) {
                if (next.usado < k || visit[next.num]) continue;
                ret++;
                visit[next.num] = true;
                queue.add(next);
            }
        }
        return ret;
    }
}