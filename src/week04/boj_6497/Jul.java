package week04.boj_6497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 전력난
public class Jul {
	static class Edge {
		int a, b, cost;

		public Edge(int a, int b, int cost) {
			super();
			this.a = a;
			this.b = b;
			this.cost = cost;
		}

	}

	static int v, e, result, rich;
	static int[] parents;
	static PriorityQueue<Edge> graph = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			result = 0;
			rich = 0 ;
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			if (v == 0 && e == 0) {
				break;
			}
			parents = new int[v];
			// makeset
			for (int i = 0; i < v; i++) {
				parents[i] = i;
			}
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				rich += c;
				graph.add(new Edge(a, b, c));
				graph.add(new Edge(b, a, c));
			}
			while (!graph.isEmpty()) {
				Edge edge = graph.poll();
				if (findParent(parents, edge.a) != findParent(parents, edge.b)) {
					union(edge.a, edge.b);
					result += edge.cost;
				}
			}
			System.out.println(rich - result);
		}
	}

	private static void union(int a, int b) {
		a = findParent(parents, a);
		b = findParent(parents, b);
		if (a < b) {
			parents[b] = a;
		} else {
			parents[a] = b;
		}

	}

	private static int findParent(int[] parents, int x) {
		if (parents[x] == x)
			return x;
		return parents[x] = findParent(parents, parents[x]);
	}

}
