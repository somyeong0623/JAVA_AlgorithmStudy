package week05.boj_15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jinsu {
	static int N, M, H;
	static int[][] ladder;
	//좌, 우
	static int[] dx = {0, 0};
	static int[] dy = {-1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//세로선 갯수(열)
		M = Integer.parseInt(st.nextToken());	//가로선 갯수(행)
		H = Integer.parseInt(st.nextToken());	//가로선을 놓을 수 있는 위치 갯수
		
		ladder = new int[M+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			ladder[a][b] = ladder[a][b+1] = 1;
		}
		
		for(int i =1; i<=N; i++) {
			dfs(i);
		}
		
	}
	
	private static void dfs(int start) {
		
		for(int j=1; j<=M; j++) {
			if(ladder[start][j] == 1) {
				dfs(start+1);
			}
		}
	}

}
