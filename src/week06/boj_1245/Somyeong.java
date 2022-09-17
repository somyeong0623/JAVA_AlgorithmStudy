package week06.boj_1245;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

//1245. 농장 관리 
public class Somyeong {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M;
	static int arr[][];
	static int dr[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int dc[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int answer;
	static boolean visited[][];
	static boolean mountain[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		mountain = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != 0 && mountain[i][j]==false) // 0이 아니고 산봉우리가 아닌 경우에 bfs돌기 
					bfs(i, j);
			}
		}
		System.out.println(answer);

	}

	public static void bfs(int r, int c) {
		visited = new boolean[N][M];
		Queue<Point> queue = new ArrayDeque<Point>();
		ArrayList<Point> mountains = new ArrayList<Point>();
		
		queue.add(new Point(r, c));
		mountains.add(new Point(r,c)); //같은 산봉우리로 취급되는 지점
		

		while (!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int d = 0; d < 8; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] == true)
					continue;

				if (arr[cur.r][cur.c] < arr[nr][nc]) { // 현재지점의 높이보다 인접 지점의 높이가 더 산봉우리가 아니므로 리턴해버리
					return;
				}
				if (arr[cur.r][cur.c] == arr[nr][nc]) {// 높이가 같으면 산봉우리 리스트에 추가하고, bfs 그 지점부터 이어서 돌기 
					mountains.add(new Point(nr,nc));
					queue.add(new Point(nr, nc)); // 산봉우리 리스트에 추가 (현재까지 살펴본 지점중 높이가 가장 높으면서 높이가 같은 지점들이 들어있음)
				}
				visited[nr][nc] = true; //bfs를 위한 방문 배열 
				
			}
		}
//		System.out.println("r: "+r+", c:"+c);
		for(int i=0; i<mountains.size(); i++) { // 산봉우리에 해당되는 지점들은 main문에서 더이상 bfs돌지 말아야 하므로 구별해두기 
			Point cur = mountains.get(i);
			mountain[cur.r][cur.c]=true;
		}
		answer++;

	}

}
