package week04.boj_14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 연구소
public class Jul {
	static int n,m,result = Integer.MIN_VALUE;
	static int[] dx = new int[] {0,1,0,-1};
	static int[] dy = new int[] {1,0,-1,0};
	static int[][] graph;
	static ArrayList<int[]> safty = new ArrayList<>();
	static Queue<int[]> virus;
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
				if(graph[i][j] == 0) safty.add(new int[] {i,j});
			}
		}
		setWall(0,0);
		System.out.println(result);
	}

	private static void setWall(int index, int count) {
		if(count == 3) {
			visited = new boolean[n][m];
			virusSpread();
			result = Math.max(result, findSafty());
			return;
		}
		for (int i = index; i < safty.size(); i++) {
			graph[safty.get(i)[0]][safty.get(i)[1]] = 1;
			setWall(i+1, count+1);
			graph[safty.get(i)[0]][safty.get(i)[1]] = 0;
		}
	}

	private static int findSafty() {
		int zero = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(graph[i][j] == 0 && !visited[i][j]) zero++;
			}
		}
		return zero;
	}

	private static void virusSpread() {
		virus = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(graph[i][j] == 2) virus.offer(new int[] {i,j});
			}
		}
		visited[virus.peek()[0]][virus.peek()[1]] = true;
		while (!virus.isEmpty()) {
			int[] temp = virus.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = temp[0] + dx[i];
				int ny = temp[1] + dy[i];
				if(0>nx||0>ny||n<=nx||m<=ny||graph[nx][ny] == 1||graph[nx][ny] == 2||visited[nx][ny]) continue;
				visited[nx][ny] = true;
				virus.offer(new int[] {nx,ny});
			}
		}
	}

}
