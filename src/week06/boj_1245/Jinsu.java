package week06.boj_1245;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jinsu {
	
	static int N, M;
	static boolean[][] visited;
	static int[][] farm;
	static int result = 0;
	static boolean flag;
	//8방 탐색 (상, 하, 좌, 우, 좌상, 좌하, 우상, 우하)
	static int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
	static int[] dy = {0, 0, -1, 1, -1, -1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		farm = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				farm[i][j] = Integer.parseInt(st.nextToken());
			}
		}	//입력 완료
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j]) {
					flag = true;
					dfs(i, j);
					if(flag) result++;
				}
			}
		}
		
		System.out.println(result);
	}

	private static void dfs(int i, int j) {
			visited[i][j] = true;
			
			for(int k=0; k<8; k++) {
				int nx = i+dx[k];
				int ny = j+dy[k];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				
				if (farm[i][j] < farm[nx][ny]) flag = false;
				
				if (!visited[nx][ny] && farm[i][j] == farm[nx][ny]) {
					dfs(nx, ny);
				}
			}
	}

}
