package week07.boj_2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class jul {
	static class Point{
		int x,y,count,wall;

		public Point(int x, int y, int count, int wall) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
			this.wall = wall;
		}

	}
	static int n,m;
	static int[][] graph;
	static int[][] vector;
	static int[][] w;
	static boolean[][][] visited;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][m];
		vector = new int[n][m];
		w = new int[n][m];
		visited = new boolean[n][m][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			String str = st.nextToken();
			for (int j = 0; j < m; j++) {
				graph[i][j] = str.charAt(j)-'0';
			}

		}
		System.out.println(bfs());
		
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(vector[i]));
		}

	}
	static int bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(0, 0, 1, 0));
		vector[0][0] = 0;
		visited[0][0][0] = true;
		while (!queue.isEmpty()) {
			Point temp = queue.poll();
			int x = temp.x;
			int y = temp.y;
			int count =temp.count;
			int wall = temp.wall;
			if(x == n-1 && y == m-1) return count;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0||nx>=n||ny<0||ny>=m||visited[nx][ny][wall])continue;
				if(graph[nx][ny] == 1) {
					if(wall >0) continue;
					else {
						visited[nx][ny][wall] = true;
						vector[nx][ny] = count+1;
						queue.add(new Point(nx, ny, count+1, wall+1));
					}
				}else {
					visited[nx][ny][wall] = true;
					vector[nx][ny] = count+1;
					queue.add(new Point(nx, ny, count+1, wall));
				}
			}
		}
		return -1;
		
	}
}