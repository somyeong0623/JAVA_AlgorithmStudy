package week05.boj_14503;

import java.io.*;
import java.util.*;

// 로봇 청소기
public class Jul {
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < m; j++) {
				graph[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		simulate(r,c,d);

		System.out.println(result);
	}
	private static void simulate(int r, int c, int direction) {
		if(!visited[r][c]&&graph[r][c] == 0) {
			visited[r][c]= true;
			result++;
			
		}
		int d = direction;
		for (int i = 0; i < 4; i++) {
			direction = direction-1<0?3:direction-1;
			int nr = r + dx[direction];
			int nc = c + dy[direction];
			if(!visited[nr][nc] && graph[nr][nc] == 0) {
				simulate(nr, nc, direction);
				return;
			}
		}
		int bd = d+2;
		bd = bd%4;
		int nr = r + dx[bd];
		int nc = c + dy[bd];
		if(graph[nr][nc] == 1) {
			return;
		}
		simulate(nr, nc, direction);
	}

}
