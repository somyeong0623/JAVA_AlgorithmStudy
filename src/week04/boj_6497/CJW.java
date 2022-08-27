package week04.boj_6497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CJW {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int[] parents;
    // 정점의 개수, 간선의 개수
    static int V, E;

    // 크기가 1인 서로소 집합 생성
    static void make() {
        parents = new int[V];
        // 모든 노드가 대표자인 집합을 만든다.
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    // A의 대표자 찾기
    static int find(int a) {
        if (parents[a] == a)
            return a;

        // path compression
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int parent_a = find(a);
        int parent_b = find(b);
        if (parent_a == parent_b)
            return false;

        parents[parent_b] = parent_a;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            if(V == 0 && E == 0)
                break;
            make();
            int sum = 0;
            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from, to, weight;
                from = Integer.parseInt(st.nextToken());
                to = Integer.parseInt(st.nextToken());
                weight = Integer.parseInt(st.nextToken());
                edges.add(new Edge(from, to, weight));
                sum += weight;
            }

            edges.sort(Comparator.comparingInt(o -> o.weight));

            int result = 0;
            int count = 0;
            for (Edge edge : edges) {
                if (union(edge.from, edge.to)) {
                    result += edge.weight;
                    if (count++ == V - 1) {
                        break;
                    }
                }
            }
            System.out.println(sum - result);
        }
    }
}
