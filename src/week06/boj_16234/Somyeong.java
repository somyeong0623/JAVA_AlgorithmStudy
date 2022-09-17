package week06.boj_16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

//16234.인구 이동 
public class Somyeong {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int answer;
	static int n, L, R;
	static int people[][];
	static boolean move = false;
	static boolean visited[][];
	static int dr[] = { -1,1,0,0 };
	static int dc[] = { 0,0,-1,1};

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		people = new int[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				people[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		while (true) {
			visited = new boolean[n][n]; //이동 시작할때마다 방문배열 초기
			move = false; // 인구이동 여부 
			
			//2차원 배열 전체를 확인하면서 연합을 이루고있는 나라 찾기 
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (visited[i][j] == false) {
						bfs(i, j);
					}
				}
			}
			if (move == false) //인구이동이 발생하지 않으면 
				break;

			answer++;
		}
		System.out.println(answer);

	}

	public static void bfs(int r, int c) {
		visited[r][c] = true;
		Queue<Point> queue = new ArrayDeque<Point>(); //bfs를 위한 
		ArrayList<Point> list = new ArrayList<Point>(); // 서로 이동할수 있는 나라 리스트 
		queue.offer(new Point(r, c));
		list.add(new Point(r, c));
		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr >= 0 && nc >= 0 && nr < n && nc < n && visited[nr][nc] == false
						&& Math.abs(people[cur.r][cur.c] - people[nr][nc]) >= L
						&& Math.abs(people[cur.r][cur.c] - people[nr][nc]) <= R) {

					queue.offer(new Point(nr, nc));
					visited[nr][nc] = true;
					list.add(new Point(nr, nc));
				}
			}
		}
		if (list.size() > 1) // 리스트에 2이상이면 이동 가능 
			move = true;
		
		//연합을 이루고 있는 칸에 새로 갱신될 인구 수 구하기 
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			Point cur = list.get(i);
			sum += people[cur.r][cur.c];
		}
		int p = sum / list.size();
		for (int i = 0; i < list.size(); i++) { // 나라 리스트를 돌면서 이동하고 난 후의 인구로 갱신해줌 
			Point cur = list.get(i);
			people[cur.r][cur.c] = p;
		}

	}
}
