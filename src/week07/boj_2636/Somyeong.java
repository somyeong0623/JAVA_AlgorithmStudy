package week07.boj_2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

//2636. 치즈 
public class Somyeong {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;

		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}

	}

	static int R, C, cheeseCnt, answer;
	static int arr[][];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			meetAir(0, 0); //공기와 접촉된 칸 -1로 채우기  (0,0)은 가장자리라 항상 공기로 취급되니까 (0,0)으로 bfs돌리기 
			
			if (existCheese() == 0) // 남은 치즈가 없으면  while문 끝내기 
				break;

			simulate(); // 공기와 접촉한 좌표 -1로 채우는 함수 
			answer++; // 시간 1 증가 

		}

		System.out.println(answer);
		System.out.println(cheeseCnt);

	}

	public static void meetAir(int r, int c) {
		boolean visited[][] = new boolean[R + 1][C + 1];
		Queue<Point> queue = new ArrayDeque<Point>();
		visited[r][c] = true;
		queue.add(new Point(r, c));

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nr >= 0 && nc >= 0 && nr < R && nc < C) {
					if (visited[nr][nc] == false && arr[nr][nc] != 1) {
						arr[nr][nc] = -1;
						visited[nr][nc] = true;
						queue.add(new Point(nr, nc));
					}
				}
			}

		}
	}

	public static int existCheese() { // 남아있는 치즈 갯수 리턴
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == 1)
					cnt++;
			}
		}

		if (cnt == 0)
			return 0;
		return cheeseCnt = cnt;
	}

	public static void simulate() {
		ArrayList<Point> list = new ArrayList<Point>();
		// 치즈가 녹아 없어질 예정일 좌표들 list에 담아놓기
		for (int i = 1; i < R - 1; i++) {
			for (int j = 1; j < C - 1; j++) {
				if (arr[i][j] == 1) {
					if ((arr[i - 1][j] == -1) || (arr[i][j - 1] == -1) || (arr[i + 1][j] == -1)
							|| (arr[i][j + 1] == -1))
						list.add(new Point(i, j));
				}
			}
		}
		//리스트에 담긴 좌표들 -1로 갱신 
		for (Point cur : list) {
			arr[cur.r][cur.c] = -1;
		}
	}

}
