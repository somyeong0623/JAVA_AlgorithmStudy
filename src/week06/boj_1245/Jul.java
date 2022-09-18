package week06.boj_1245;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 농장 관리
public class Jul {
	static int n,m;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {0,0,1,-1,-1,1,-1,1};
	static int[] dy = {1,-1,0,0,1,1,-1,-1};
	static int result = 0;
	static Queue<int[]> queue;

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

		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(graph[i][j]>0 && !visited[i][j]) {
					queue = new ArrayDeque<int[]>();
//					System.out.println(i+" "+j);
					findTop(i,j);
//					System.out.println(queue.size());
					if(isTop(i,j)) {
						result++;
					}
				}
			}
		}
		System.out.println(result);
	}

	private static void findTop(int i, int j) {
		Queue<int[]> temp = new ArrayDeque<>();
		queue.add(new int[] {i,j});
		boolean[][] tempVisited = new boolean[n][m];
		tempVisited[i][j] = true;
		temp.add(new int[] {i,j});
		while (!temp.isEmpty()) {
			int[] t = temp.poll();
			int x = t[0];
			int y = t[1];
			for (int k = 0; k < 8; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx<0||ny<0||nx>=n||ny>=m||tempVisited[nx][ny]) continue;
				if(graph[nx][ny] == graph[x][y]) {
					queue.add(new int[] {nx,ny});
					tempVisited[nx][ny] = true;
					temp.add(new int[] {nx,ny});
				}
			}
		}
		
	}

	private static boolean isTop(int i, int j) {
//		queue.add(new int[] {i,j});
//		visited[i][j] = true;
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			visited[x][y] = true;
			for (int k = 0; k < 8; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx<0||ny<0||nx>=n||ny>=m) continue;
				if (graph[nx][ny] > graph[x][y]) {
					return false;
				}
			}
		}
		return true;
	}

}
