package week08.boj_1261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//1261. 알고스팟 
public class Somyeong {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int M, N; // 가로크기, 세로크기
	static int map[][];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int cnt[][]; // 현재 좌표로 오기까지 벽을 부순 갯수 저장 

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cnt= new int[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
				cnt[i][j]=Integer.MAX_VALUE; //cnt배열은 최대인트로 넣어두기 
			}
		}

		Queue<Point> queue = new ArrayDeque<Point>();
		queue.add(new Point(0, 0));
		cnt[0][0]=0;
		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr>=0 && nc>=0 && nr<N && nc<M) {
					// 현재 좌표가 벽이고, 이전 좌표를 거쳐오는 경우가 벽을 더 적게 사용한 경우 : 갯수 업데이트하고, 큐에 넣기 
					if(map[nr][nc]==1 && cnt[nr][nc]>cnt[cur.r][cur.c]+1) {
						cnt[nr][nc]=cnt[cur.r][cur.c]+1;
						queue.add(new Point(nr,nc));
					}
					
					// 현재 좌표가 벽이 아니고, 이전 좌표를 거쳐오는 경우가 벽을 더 적게 사용한 경우 : 갯수 업데이트하고, 큐에 넣기 
					else if(map[nr][nc]==0 && cnt[nr][nc]>cnt[cur.r][cur.c]) {
						cnt[nr][nc]=cnt[cur.r][cur.c];
						queue.add((new Point(nr,nc)));
					}
				}
			}
		}
		System.out.println(cnt[N-1][M-1]);

	}
}
