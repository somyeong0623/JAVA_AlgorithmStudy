package week07.boj_3190;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jinsu {
	static class Snake{
		int cnt;
		String dir;
		
		public Snake(int cnt, String dir) {
			this.cnt = cnt;
			this.dir = dir;
		}
	}
	
	static int N, K, L;
	static int[][] map;
	static int total_cnt = 0;
	static int dir_cnt = 0;
	static ArrayList<Snake> snake;
	static Queue<Point> len;
	//우, 하, 좌, 상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());	//NxN 보
		K = Integer.parseInt(br.readLine());	//사과의 갯수
		
		map = new int[N+1][N+1];
		
		map[1][1] = 1;	//뱀의 위치는 1로 표기
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int apple_x = Integer.parseInt(st.nextToken());
			int apple_y = Integer.parseInt(st.nextToken());
			
			map[apple_x][apple_y] = 2;	//사과 좌표에 2로 표기
		}
		
		L = Integer.parseInt(br.readLine());
		
		snake = new ArrayList<>();
		len = new ArrayDeque<>();
		
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			snake.add(new Snake(sec, dir));
		}//입력 완료
		
		int m = 0;	//처음엔 오른쪽
		int x = 1, y = 1;	//초기 뱀 위치
		len.add(new Point(x, y));
		int idx = 0;	//snake 위치 이동하기 위한 인덱스
		
		while(true) {
			int nx = x+dx[m];
			int ny = y+dy[m];
			
			
			total_cnt++;
			
			if(nx >= 1 && ny >= 1 && nx <= N && ny <= N && map[nx][ny] != 1) {
				if(map[nx][ny] == 2) {
					len.add(new Point(nx, ny));
				}
				else {
					Point l = len.poll();
					map[l.x][l.y] = 0;
					len.add(new Point(nx, ny));
				}
					
				map[nx][ny] = 1;
				
				if(idx < L && total_cnt == snake.get(idx).cnt) {
					if (snake.get(idx).dir.equals("D")) m = (m+1) % 4;
					else m = (m+3) % 4;
					idx++;
				}
				
				x = nx;
				y = ny;
				
			}
			
			else break;
		}
		System.out.println(total_cnt);
	}
}

