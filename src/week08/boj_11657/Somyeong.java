package week08.boj_11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//11657. 타임머신 (벨만포드 - 뭔가 이해가 되는듯 안되는듯 ... )
//dist배열을 int로 하면 출력초과인데 왜그런지 ?
public class Somyeong {

	static class Edge {
		int from;
		int to;
		int time;

		public Edge(int from, int to, int time) {
			this.from = from;
			this.to = to;
			this.time = time;
		}

	}

	static int N, M;
	static long dist[];
	static ArrayList<Edge> edges;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new long[N + 1];

		edges = new ArrayList<Edge>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			edges.add(new Edge(from, to, time));
		}
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[1] = 0;
		if(belman(1)) {
			System.out.println(-1);
		}else {
			for(int i=2; i<=N; i++) {
				if(dist[i]!=Long.MAX_VALUE)
					System.out.println(dist[i]);
				else
					System.out.println(-1);
			}
		}
	}

	public static boolean belman(int v) {

		for (int i = 1; i <= N; i++) { // N-1번 반복하는 부분이 잘 이해 안됨. 
			for (int j = 0; j < M; j++) { // 모든 간선 다 살펴보기 
				Edge cur = edges.get(j);

				if (dist[cur.from] != Long.MAX_VALUE && dist[cur.to] > dist[cur.from] + cur.time) {
					dist[cur.to] = dist[cur.from] + cur.time;
					
					if(i==N) // 음수 순환이 없다면 N-1번 반복했을 때 결정됨. N번째 뭔가 갱신이 된다면 음수 순환 존재 
						return true;
				}
			}
		}
		return false;

	}

}
