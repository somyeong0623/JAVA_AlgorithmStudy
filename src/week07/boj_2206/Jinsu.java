package week07.boj_2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jinsu {

	static class Point {
		int x, y, count, wall;

		public Point(int x, int y, int count, int wall) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.wall = wall;
		}
	}
	
	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	static int min = Integer.MAX_VALUE;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M][2];
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(s.substring(j, j+1));
			}
		}	//입력 완료
		
		min = bfs(0, 0);
		
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static int bfs(int r, int c) {
		Queue<Point> q = new ArrayDeque<>();
		
		q.add(new Point(r, c, 1, 0));
		visited[r][c][0] = true;
		
		while(!q.isEmpty()) {
			
			Point cur = q.poll();
			
			if (cur.x == N-1 && cur.y == M-1) {
				return cur.count;
			}
			
			
			for(int k=0; k<4; k++) {
				int nx = cur.x + dx[k];
				int ny = cur.y + dy[k];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
					if(!visited[nx][ny][cur.wall]) {
						visited[nx][ny][cur.wall] = true;
						q.add(new Point(nx, ny, cur.count+1, cur.wall));
					}
				}
			}
			
			if (cur.wall < 1) {
				for(int k=0; k<4; k++) {
					int nx = cur.x + dx[k];
					int ny = cur.y + dy[k];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
					
					if(!visited[nx][ny][cur.wall+1] && map[nx][ny] == 1) {
						q.add(new Point(nx, ny, cur.count+1, cur.wall+1));
					}
						
				}
				
			}
			
		}
		
		return min;
	}

}
