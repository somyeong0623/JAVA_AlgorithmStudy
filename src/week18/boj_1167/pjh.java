//56분 12초
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int target;
	static int len;
	static ArrayList[] node_list;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		node_list = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			node_list[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int edge = Integer.parseInt(st.nextToken());
			target = edge;
			while (st.hasMoreTokens()) {
				int tmp_dist = Integer.parseInt(st.nextToken());
				if (tmp_dist == -1) {
					break;
				}
				int tmp_weight = Integer.parseInt(st.nextToken());
				node_list[edge].add(new Node(tmp_dist, tmp_weight));
				
			}
		}
		len = 0;
		visit = new boolean[N + 1];
		visit[target] = true;
		DFS(target, 0);
		len = 0;
		visit = new boolean[N + 1];
		visit[target] = true;
		DFS(target, 0);
		System.out.println(len);
	}

	static void DFS(int edge, int depth) {
		if (depth > len) {
			target = edge;
			len = depth;
		}
		for (int i = 0; i < node_list[edge].size(); i++) {
			Node next_edge = (Node) node_list[edge].get(i);
			if (!visit[next_edge.dist]) {
				visit[next_edge.dist] = true;
				DFS(next_edge.dist, depth + next_edge.weight);
				visit[next_edge.dist] = false;
			}
		}
	}

	static class Node {
		int dist;
		int weight;

		public Node(int dist, int weight) {
			this.dist = dist;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [dist=" + dist + ", weight=" + weight + "]";
		}

	}

}