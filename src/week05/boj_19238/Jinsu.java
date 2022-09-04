package week05.boj_19238;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jinsu {
	
	static class move {
		int start_x, start_y;
		int end_x, end_y;
		
		public move(int start_x, int start_y, int end_x, int end_y) {
			this.start_x = start_x;
			this.start_y = start_y;
			this.end_x = end_x;
			this.end_y = end_y;
			
		}
	}
	
	static int N, M, fuel;
	static int[][] map;
	static move[] list;
	
	//상, 좌, 하, 우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0 , -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		list = new move[M];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int taxi_x = Integer.parseInt(st.nextToken());
		int taxi_y = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start_x = Integer.parseInt(st.nextToken());
			int start_y = Integer.parseInt(st.nextToken());
			int end_x = Integer.parseInt(st.nextToken());
			int end_y = Integer.parseInt(st.nextToken());
			
			list[i] = new move(start_x, start_y, end_x, end_y);
		}
		
		
		bfs(taxi_x, taxi_y);
	}
	
	private static void bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		
		q.offer(new Point(x, y));
		
		while (!q.isEmpty()) {
			Point cur  = q.poll();
			int r = cur.x;
			int c = cur.y;
			
			for(int k=0; k<4; k++) {
				int nx = r+dx[k];
				int ny = c+dy[k];
				
				if (nx<0 || ny<0 || nx>=N || ny>=N || map[nx][ny] == 1) continue;
				
				for(int i=0; i<M; i++) {
					if(nx == list[i].start_x && ny == list[i].start_y) {
						person_move(nx, ny);	//승객 좌표면 다시 목적지까지 bfs
					}
					else {
						q.offer(new Point(nx, ny));
					}
				}
			}
		}
		
	}

	private static void person_move(int taxi_x, int taxi_y) {
		
	}
}