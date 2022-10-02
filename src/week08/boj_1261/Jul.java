package week08.boj_1261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 알고스팟
public class Jul {
	static int n,m;
	static int[][] graph;
	static boolean[][] visited;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[m][n];
		visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				graph[i][j] = str.charAt(j)-'0';
			}
		}
		System.out.println(bfs(0,0));
	}
	private static int bfs(int i, int j) {
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)->o1[2]-o2[2]);
		visited[i][j] = true;
		queue.add(new int[] {i,j,0});
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			int cost = temp[2];
			if(x==m-1 && y == n-1) return cost;
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y +dy[k];
				if(nx<0||ny<0||nx>=m||ny>=n||visited[nx][ny])continue;
				visited[nx][ny] = true;
				if(graph[nx][ny] == 0) {
					queue.add(new int[] {nx,ny,cost});
				}else if(graph[nx][ny] == 1) {
					queue.add(new int[] {nx,ny,cost+1});
				}
			}
		}
		return 0;
	}

}
