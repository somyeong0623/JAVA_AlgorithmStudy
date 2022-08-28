package week04.boj_6497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//6497. 전력난 
public class Somyeong {

	static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

	}

	static int m, n;
	static int vertexCnt;
	static int total, sum;
	static int parent[];
	static ArrayList<Edge> edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			sum=0;
			total=0;

			if (m == 0 && n == 0)
				break;

			edges = new ArrayList<Edge>();
			parent = new int[m];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int f = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				edges.add(new Edge(f, t, w));
				total += w;
			}
			Collections.sort(edges);

			vertexCnt = 1;

			makeSet();
			for (Edge e : edges) {
				Edge cur = e;
				if (find(cur.from) == find(cur.to))
					continue;
				union(cur.from, cur.to);
				sum += cur.weight;
				vertexCnt++;
				if (vertexCnt == n)
					break;
			}

			System.out.println(total - sum);
		}
	}

	static void makeSet() {
		for (int i = 0; i < m; i++) {
			parent[i] = i;
		}
	}

	static int find(int x) {
		if (x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		if (rootX < rootY)
			parent[rootY] = rootX;
		else
			parent[rootX] = rootY;
	}
}
