package week07.boj_2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//2206. 벽 부수고 이동하기 
public class Somyeong_FAIL {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int R, C;
	static int arr[][];
	static boolean visited[][];
	static int useBreak[][];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int dist[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		visited = new boolean[R][C];
		useBreak = new int[R][C];
		dist = new int[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = s.charAt(j) - '0'; // character를 int로 변환
			}
		}

		Queue<Point> q = new ArrayDeque<Point>();
		q.add(new Point(0, 0));
		visited[0][0] = true;
		dist[0][0] = 1;
		
		loop:
		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nr >= 0 && nc >= 0 && nr < R && nc < C && visited[nr][nc] == false) {
					if (arr[nr][nc] == 1 && useBreak[cur.r][cur.c] == 1)
						continue;
					if (arr[nr][nc] == 1 && useBreak[cur.r][cur.c] == 0) {
						useBreak[nr][nc] = 1;
					}

					if (arr[nr][nc] == 0)
						useBreak[nr][nc] = useBreak[cur.r][cur.c];

					visited[nr][nc] = true;
					q.add(new Point(nr, nc));
					dist[nr][nc] = dist[cur.r][cur.c] + 1;
					

				}
			}
		}
		System.out.println("===dist===");
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("===useBreak===");
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(useBreak[i][j] + " ");
			}
			System.out.println();
		}
		if (dist[R - 1][C - 1] == 0)
			System.out.println(-1);
		else
			System.out.println(dist[R - 1][C - 1]);

	}

}
