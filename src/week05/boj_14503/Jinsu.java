package week05.boj_14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jinsu {
	static class CurRobot{
		int x, y;
		int d;
		public CurRobot(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		
	}
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int result;		//청소한 곳 카운트(답)
	//북, 동, 남, 서
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//행
		M = Integer.parseInt(st.nextToken());	//열
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());	//로봇 청소기 있는 칸 좌표
		int c = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());	//로봇 청소기가 바라보고 있는 방향
		
		map = new int[N][M];			//청소 맵
		visited = new boolean[N][M];	//해당 칸 청소여부 판별
		result = 0;						//답
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		CurRobot robot = new CurRobot(r, c, dir);
		
		result = bfs(robot);
		System.out.println(result);
		
	}

	private static int bfs(CurRobot robot) {
		int cnt = 0;
		Queue<CurRobot> q = new ArrayDeque<>();
		
		q.offer(robot);
		
		
		while(!q.isEmpty()) {
			CurRobot now = q.poll();
			
			boolean flag = false;
			
			// 1. 현재 위치 청소
			if(map[now.x][now.y] == 0) {
				cnt++;
				map[now.x][now.y] = 2;
			}
			
			//2. 탐색
			for(int k=0; k<4; k++) {
				int nd = (now.d+3)%4;
				int nx = now.x+dx[nd];
				int ny = now.y+dy[nd];
				
				if(nx>=0 && ny>=0 && nx < N || ny < M) {
					//2-1. 왼쪽 방향에 청소하지 않은 공간 존재한다면
					if(map[nx][ny] == 0) {
						q.offer(new CurRobot(nx, ny, nd));
						flag = true;
						break;
					}
				}
				
				//2-2.청소할 공간이 없으면 그 방향으로 회전 
				now.d = (now.d+3) % 4;
			}
			
			//2-3, 2-4 네 방향 모두 청소가 이미 되어 있거나 벽인 경우
			if(!flag) {
				int back = (now.d+2)%4;
				int backx = now.x + dx[back];
				int backy = now.y + dy[back];
				
				if(backx >= 0 && backy >= 0 && backx < N && backy < M) {
					//후진 할 수 있다면
					if(map[backx][backy] != 1) {
						q.offer(new CurRobot(backx, backy, now.d));
					}
				}
			}
		}
		return cnt;
	}

}
