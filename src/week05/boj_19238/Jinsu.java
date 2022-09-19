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
	
	//상, 좌, 하, 우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0 , -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());		//NxN
		M = Integer.parseInt(st.nextToken());		//승객수
		fuel = Integer.parseInt(st.nextToken());	//택시 연료 양
		
		map = new int[N+1][N+1];					//지도
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int taxi_x = Integer.parseInt(st.nextToken());	//택시의 좌표
		int taxi_y = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int person_x = Integer.parseInt(st.nextToken());	//승객의 좌표
			int person_y = Integer.parseInt(st.nextToken());
			int finish_x = Integer.parseInt(st.nextToken());	//목적지 좌표
			int finish_y = Integer.parseInt(st.nextToken());
			
			map[person_x][person_y] = 2;						//승객있는 위치
			map[finish_x][finish_y] = 3;						//목적지 위치
			
		}
		
		person_bfs(taxi_x, taxi_y); //승객 찾는 bfs
	}
	
	private static void person_bfs(int r, int c) {
		Queue<Point> q = new ArrayDeque<>();
		
		q.offer(new Point(r, c));
		int cnt = 0;
		
		while (!q.isEmpty()) {
			cnt++;	//택시 이동한 거리 카운트 (나중에 연료 양 빼주기 위함)
			Point cur  = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			for(int k=0; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				
				if (nx < 1 || ny < 1 || nx > N  || ny > N || map[nx][ny] == 1) {
					continue;
				}
				
				if (map[nx][ny] == 2) {	//승객을 발견했다면
					fuel -= cnt;
					cnt = 0;
					finish_bfs(nx, ny);
				}
				
			}
		}
		
	}

	private static void finish_bfs(int nx, int ny) {
		
	}
}