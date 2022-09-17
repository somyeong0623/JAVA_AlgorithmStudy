package week05.boj_2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CJW {
    static class Node {
        int vertex;
        Node next;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }

    static int V, E;
    static Node[] adjList;
    static int[] inDegree;

    private static List<Integer> topologySort() {
        List<Integer> list = new ArrayList<>();
        Queue<Integer> que = new ArrayDeque<>();

        // 진입 차수가 0인 정점을 큐에 넣기
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0)
                que.offer(i);
        }

        // BFS
        while (!que.isEmpty()) {
            int cur = que.poll();
            list.add(cur);

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (--inDegree[temp.vertex] == 0) {
                    que.offer(temp.vertex);
                }

            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new Node[V];
        inDegree = new int[V];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            adjList[from] = new Node(to, adjList[from]);
            inDegree[to]++;
        }
        List<Integer> list = topologySort();
        if (list.size() == V) {
            // 위상정렬 완성
            for (int val : list) {
                System.out.print((val + 1) + " ");
            }
        } else {
            // 사이클 발생
            System.out.println("cycle ...");
        }
    }
}
