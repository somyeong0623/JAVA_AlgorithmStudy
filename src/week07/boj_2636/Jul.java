package week07.boj_2636;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jul {
	static int n,m,answer,cheeze;
	static int[][] graph;
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,1,0,-1};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(!check()) {
			cheeze = 0;
			makeC();
			melt();
//			System.out.println("-----------------------------");
//			for (int i = 0; i < n; i++) {
//				System.out.println(Arrays.toString(graph[i]));
//			}
			answer++;
		}
		System.out.println(answer);
		System.out.println(cheeze);
	}
	private static void melt() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(graph[i][j] == 2) {
					graph[i][j] = 0;
					cheeze++;
				}
			}
		}
	}
	private static void makeC() {
		visited = new boolean[n][m];
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				if(graph[i][j] == 0 && !visited[i][j]) {
//					oxidation(i,j);
//				}
//			}
//		}
		oxidation(0, 0);
	}
	private static void oxidation(int i, int j) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {i,j});
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx<0||nx>=n||ny<0||ny>=m||visited[nx][ny])continue;
				if(graph[nx][ny] == 1) graph[nx][ny] = 2;
				else if(graph[nx][ny] == 0) {
					visited[nx][ny] = true;
					queue.add(new int[] {nx,ny});
				}
			}
			
		}
	}
	private static boolean check() {
		boolean clean = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(graph[i][j] !=0) return false;
			}
		}
		return clean;
	}

}
