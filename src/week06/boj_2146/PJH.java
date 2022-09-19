package week06.boj_2146;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PJH {
	public static int[][] table;
	public static int[][] origin;

	public static int answer;
	public static int tmp_answer;

	public static int[] rotate_r = { -1, 0, 1, 0 };
	public static int[] rotate_c = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(bf.readLine());
		answer = Integer.MAX_VALUE;
		tmp_answer = 0;
		table = new int[N][N];
		origin = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int id = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (table[i][j] == 1) {
					id++;
					Queue<Point> q = new LinkedList<>();
					q.add(new Point(i, j));
					table[i][j] = id;
					while (!q.isEmpty()) {
						Point tmp = q.poll();
						for (int k = 0; k < 4; k++) {
							int cur_r = tmp.r + rotate_r[k];
							int cur_c = tmp.c + rotate_c[k];

							if (check(cur_r, cur_c, 1, true)) {
								q.add(new Point(cur_r, cur_c));
								table[cur_r][cur_c] = id;
							}

						}
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				origin[i][j] = table[i][j];
			}
		}

//		for(int i = 0 ; i < N ;i++) { // 지도확인
//			System.out.println(Arrays.toString(table[i]));
//		}
//		System.out.println();
		//////////////////////////////////////////////////////////////////////////

//		start();
//		for (int i = 0; i < N; i++) { // 지도확인
//			System.out.println(Arrays.toString(table[i]));
//		}

		////////////////////////////////////////////////////////////////////////////
		boolean stop = true;
		while (id > 1) {
			tmp_answer++;
			start(id);
			stop = one_replace(id);

			if (!stop) {
				answer = Math.min(answer, tmp_answer);
				tmp_answer = 0;
				id--;
				
//				for (int i = 0; i < N; i++) {
//					System.out.println(Arrays.toString(table[i]));
//				}
//				System.out.println(answer);
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						table[i][j] = origin[i][j];
					}
				}
			}

		}

		System.out.println(answer);
	}

	public static void start(int target) {
		int N = table.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (table[i][j] == 0) {
					for (int k = 0; k < 4; k++) {
						int cur_r = i + rotate_r[k];
						int cur_c = j + rotate_c[k];

						if (check(cur_r, cur_c, target, true)) {
							table[i][j] = 1;
						}

					}

				}
			}
		}
	}

	public static boolean one_replace(int target) {
		int N = table.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (table[i][j] == 1) {
					for (int k = 0; k < 4; k++) {
						int cur_r = i + rotate_r[k];
						int cur_c = j + rotate_c[k];

						if (check(cur_r, cur_c, target, true)) {
							table[i][j] = table[cur_r][cur_c];
						}

						if (check(cur_r, cur_c, target, false)) {
							return false;
						}

					}

				}

			}
		}
		return true;
	}

	public static boolean check(int r, int c, int val, boolean mode) {
		if (r >= 0 && r < table.length && c >= 0 && c < table.length) {
			if (mode) {// true
				if (table[r][c] == val) {
					return true;
				} else {
					return false;
				}
			} else {// mode ==false val이 아닐경우 true;
				if (table[r][c] != val && table[r][c] != 1 && table[r][c] != 0) { // 테두리 1은 무시해야함.
					return true;
				} else {
					return false;
				}
			}
		}

		return false;
	}

}

class Point {
	int r;
	int c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}