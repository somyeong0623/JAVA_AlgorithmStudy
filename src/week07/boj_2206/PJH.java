package week07.boj_2206;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PJH {

	static int[][] table;
	static boolean[][][] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		table = new int[N][M];
		visit = new boolean[N][M][2];
		int[] rotate_r = { -1, 0, 1, 0 };
		int[] rotate_c = { 0, 1, 0, -1 };

		// 입력값 받아오기
		for (int i = 0; i < N; i++) {
			char[] tmp = bf.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				table[i][j] = tmp[j] - '0';
			}
		}

		// 기저조건
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> {
			return e1.cnt - e2.cnt;
		});
		pq.add(new Edge(0, 0, 1, 0));
		visit[0][0][0] = true;
		int answer = -1;
		while (!pq.isEmpty()) {
			Edge tmp = pq.poll();
			//System.out.println(tmp);
			if (tmp.r == N - 1 && tmp.c == M - 1) {
				answer = tmp.cnt;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int cr = rotate_r[i] + tmp.r;
				int cc = rotate_c[i] + tmp.c;

				if (isIn(cr, cc, N, M)) { // 0일 때 벽만남 안만남 1일때 벽만남 안만남
					if(tmp.chance==0) { // 벽 부신적 없을 때
						if(table[cr][cc]==1) {//벽일때
							pq.add(new Edge(cr,cc,tmp.cnt+1,1));
							visit[cr][cc][1]= true;
						}else if(!visit[cr][cc][0]){ //벽이 아닐 때 + 방문한적 없을 때
							pq.add(new Edge(cr,cc,tmp.cnt+1,0));
							visit[cr][cc][0]= true;
						}
					}else { //벽부신적 있을 때
						if(!visit[cr][cc][0]&&!visit[cr][cc][1] && table[cr][cc]!=1) {//여태 방문x + 벽이 아닐 때
							pq.add(new Edge(cr,cc,tmp.cnt+1,tmp.chance));
							visit[cr][cc][1]=true;
						}
					}
				}

			}

		}
		System.out.println(answer);
		
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(table[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(visit[i][j][0] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(visit[i][j][1] + " ");
//			}
//			System.out.println();
//		}
		
	}

	static boolean isIn(int r, int c, int N, int M) {
		if (r >= 0 && r < N && c >= 0 && c < M) {
			return true;
		}
		return false;
	}

	static class Edge {
		int r;
		int c;
		int cnt;
		int chance;

		public Edge(int r, int c, int cnt,int chance) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.chance = chance;
		}

		@Override
		public String toString() {
			return "Edge [r=" + r + ", c=" + c + ", cnt=" + cnt + ", chance=" + chance + "]";
		}
		
	}

}
