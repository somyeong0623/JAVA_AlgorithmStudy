import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int SIZE;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// [1,2,3] 1,2좌표에 도달하는데 파괴한 블록 수는 3개라는 경우의 수
		PriorityQueue<Integer[]> pq = new PriorityQueue<>((e1, e2) -> {
			return e1[2] - e2[2];
		});

		SIZE = Integer.parseInt(bf.readLine());
		map = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			char[] tmp = bf.readLine().toCharArray();
			for (int j = 0; j < SIZE; j++) {
				map[i][j] = tmp[j] - '0';
			}
		}

		bfs();

	}

	static void bfs() {
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		boolean[][] visit = new boolean[SIZE][SIZE];
		PriorityQueue<Point> pq = new PriorityQueue<>((e1, e2) -> {
			return e1.cnt - e2.cnt;
		});

		pq.add(new Point(0, 0, 0));
		visit[0][0] = true;
		while (!pq.isEmpty()) {
			Point tmp = pq.poll();
			if (tmp.r == (SIZE - 1) && tmp.c == (SIZE - 1)) {
				System.out.println(tmp.cnt);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int cur_r = tmp.r + dr[i];
				int cur_c = tmp.c + dc[i];
				if (outCheck(cur_r, cur_c) && !visit[cur_r][cur_c]) {
					Point new_point = new Point(cur_r, cur_c, tmp.cnt);
					visit[cur_r][cur_c] = true;

					if (map[cur_r][cur_c] == 0) {
						new_point.cnt += 1;
					}
					pq.add(new_point);

				}
			}
		}
	}

	static boolean outCheck(int r, int c) {
		if (r < 0 || c < 0 || r >= SIZE || c >= SIZE) {
			return false;
		}

		return true;
	}

	static class Point {
		int r;
		int c;
		int cnt;

		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
}
