package week16.boj_2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//2573. 빙산 
public class Somyeong {

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	static int R,C;
	static int arr[][];
	static int answer;
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static boolean visit[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}

		}
		while (true) {
			if (melt()) { // 두 덩어리 이상으로 분리되기 전에 다 녹았으면 0 출력하고 종료 
				answer = 0;
				break;
			}
			
			simulate();
			answer++;
			
			if (separate()) //두 덩어리 이상으로 분리되면 반복 종료하고 정답 출력 
				break;
		}
		System.out.println(answer);
	}

	public static boolean melt() { // 다 녹았으면 true, 아니면 false
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != 0)
					cnt++;
			}
		}

		if (cnt == 0)
			return true;
		else
			return false;
	}

	public static boolean separate() { // 두덩어리 이상으로 분리되면 true, 아니면 false
		int cnt=0;
		visit= new boolean[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0;j <C; j++) {
				if(arr[i][j]>0 && visit[i][j]==false) {
					cnt++;
					visit[i][j]=true;
					bfs(i,j);
				}
			}
		}
		if(cnt>=2)
			return true;
		else
			return false;
	}

	public static void bfs(int r, int c) { //같은 덩어리인지 확인하는 bfs 
		
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.add(new Point(r,c));
		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d]; 
				if (nr >= 0 && nc >= 0 && nr < R && nc < C && visit[nr][nc] == false && arr[nr][nc]>0) {// arr[nr][nc]>0 체크 주의 
					visit[nr][nc] = true;
					queue.add(new Point(nr, nc));
				}
			}

		}
	}
	public static void simulate() { // 사방에 0이 저장된 칸의 갯수만큼 줄어듦 
		int copy[][] = new int[R][C]; 
		// 빙산배열을 새로운 배열에 복사해둔 후, 복사해둔 배열의 사방을 확인하면서 기존 빙산배열의 수를 줄여주어야 한다.
		// 이전 simulate한 결과를 기준으로 변경해야하는데, 기존배열 하나로 체크하면서 수 줄여주면 이전 상태가 유지되지않아서 잘못된 결과가 나올 수 있다. 
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				copy[i][j]=arr[i][j];
			}
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(copy[i][j]>0) {
					for(int d=0; d<4; d++) {
						int nr=i+dr[d];
						int nc=j+dc[d];
						if(nr>=0 && nc>=0 && nr<R && nc<C) {
							if(copy[nr][nc]==0) // 복사한 배열 기준으로 확인하기 
								arr[i][j]--;
						}
					}
				}
				if(arr[i][j]<0) // 음수라면 0으로 변경 
					arr[i][j]=0;
			}
		}
	}

}
