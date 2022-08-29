package week04.boj_6497;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PJH {
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(bf.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			if(M == 0 && N == 0) {
				break;
			}
			PriorityQueue<Point> pq = new PriorityQueue<>((e1, e2) -> {
				return e1.weight - e2.weight;
			});

			parent = new int[M];
			for (int i = 0; i < M; i++) {
				parent[i] = i;
			}

			int answer = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				int s = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				answer += weight;
				pq.add(new Point(s, d, weight));
			}

			while (!pq.isEmpty()) {
				Point tmp = pq.poll();
				if (find(tmp.s) != find(tmp.d)) {
					union(tmp.s, tmp.d);
					answer -= tmp.weight;
				}

			}

			System.out.println(answer);
		}

	}

	static int find(int a) {
		if (parent[a] == a)
			return a;
		else
			return parent[a] = find(parent[a]);

	}

	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a > b)
			parent[a] = b;
		else
			parent[b] = a;
	}

}

class Point {
	int s;
	int d;
	int weight;

	public Point(int s, int d, int weight) {
		this.s = s;
		this.d = d;
		this.weight = weight;
	}

}
