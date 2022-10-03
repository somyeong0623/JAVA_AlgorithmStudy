package week08.boj_11657;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class PJH {
	static ArrayList<Edge> edge;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(bf.readLine());

		int N = Integer.parseInt(st.nextToken());// 도시수
		int M = Integer.parseInt(st.nextToken());// 버스노선
		edge = new ArrayList<Edge>();
		long[] dist = new long[N];
		for (int i = 1; i < N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int a, b, c;
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken());

			edge.add(new Edge(a, b, c));
		}
		
		// 시작 0번째
		boolean stop = false;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Edge target = edge.get(j);
				
				if(dist[target.src]!=Integer.MAX_VALUE && dist[target.dist] > dist[target.src]+target.time) {//방문한적 있고 더 짧은 경로를 발견한 경우이다.
					dist[target.dist] = dist[target.src]+target.time;
					if(i == N-1) {
						stop = true;
					}
				}
				
			}
		}
		
		if(stop) {
			System.out.println(-1);
		}else {
			for(int i = 1; i < N ;i++) {
				if(dist[i]!=Integer.MAX_VALUE) {
					System.out.println(dist[i]);
				}else {
					System.out.println(-1);
				}
			}
		}
		

	}

}

class Edge {
	int src;
	int dist;
	int time;

	public Edge(int src, int dist, int time) {
		this.src = src;
		this.dist = dist;
		this.time = time;
	}
}
