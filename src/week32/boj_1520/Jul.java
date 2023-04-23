package week32.boj_1520;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jul {
	static int n,m,result;
	static int[][] graph;
	static int[][] visited;
	static int[] dx = {0,1,0,-1},dy = {1,0,-1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		graph= new int[n][m];
		visited = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.valueOf(st.nextToken());
			}
//			System.out.println(Arrays.toString(graph[i]));
		}
		bfs(0,0);
		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(visited[i]));
		}
		System.out.println(visited[n-1][m-1]);
	}

	private static void bfs(int i, int j) {
		visited[i][j]++;
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((o1,o2)->o2[2]-o1[2]);
		queue.add(new int[] {i,j,graph[i][j]});
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			if(temp[0] == n && temp[1] == m)result++;
			for (int k = 0; k < 4; k++) {
				int nx = temp[0] + dx[k];
				int ny = temp[1] + dy[k];
				if(nx<0|| nx>=n||ny<0||ny>=m||graph[temp[0]][temp[1]]<=graph[nx][ny])continue;
				if(visited[nx][ny]==0)queue.add(new int[] {nx,ny,graph[nx][ny]});
				visited[nx][ny]+=visited[temp[0]][temp[1]];
			}
		}
		
	}

}
