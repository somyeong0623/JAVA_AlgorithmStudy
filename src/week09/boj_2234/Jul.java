package week09.boj_2234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

// 성곽
public class Jul {
	static int n,m,size,room,breaksize;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {-1,0,1,0};
	static int[][] graph;
	static int[][] visited;
	static HashMap<Integer, Integer> roominfo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[m][n];
		visited = new int[m][n];
		roominfo = new HashMap<>();
		for (int i = 0; i < m; i++) {
			st= new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < n; j++) {
				visited[i][j] = -1;
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(visited[i][j] == -1) {
					int val = bfs(i,j);
					roominfo.put(room, val);
					size = Math.max(size, val);
					room++;
				}
			}
		}
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(nx<0||ny<0||nx>=m||ny>=n||visited[i][j] == visited[nx][ny]) continue;
					breaksize = Math.max(breaksize, roominfo.get(visited[i][j])+roominfo.get(visited[nx][ny]));
				}
			}
		}
		System.out.println(room);
		System.out.println(size);
		System.out.println(breaksize);
	}
	
	private static int bfs(int i, int j) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {i,j});
		int count = 1;
		visited[i][j] = room;
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			for (int k = 0; k < 4; k++) {
				if((graph[temp[0]][temp[1]] & (1<<k))>0) continue;
				int nx = temp[0] + dx[k];
				int ny = temp[1] + dy[k];
				if(visited[nx][ny] != -1) continue;
				visited[nx][ny] = room;
				count++;
				queue.add(new int[] {nx,ny});
			}
		}
		return count;
	}

}
