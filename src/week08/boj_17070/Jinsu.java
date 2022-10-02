package week08.boj_17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jinsu {
	static int N;
	static int[][] area;
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		area = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}	//입력완료
		
		dfs(0, 1, 0);
		
		System.out.println(result);
	}

	private static void dfs(int i, int j, int d) {
		if(i == N-1 && j == N-1) {
			result++;
			return;
		}
		
		// 대각선으로 이동이 가능한 경우
		if(i+1 < N && j+1 < N) {
			if(area[i][j+1] == 0 && area[i+1][j] == 0 && area[i+1][j+1] == 0) {
				dfs(i+1, j+1, 2);
			}
		}
		
		//파이프가 가로인 경우
		if (d==0 || d == 2) {
			if(j+1 < N) {	//이동 시 범위를 벗어나지 않는 경우
				if(area[i][j+1] == 0)
					dfs(i, j+1, 0);
			}
		}
		
		//파이프가 세로인 경우
		if(d==1 || d==2) {
			if(i+1 < N) {	
				if(area[i+1][j] == 0) {
					dfs(i+1, j, 1);
				}
			}
		}
		
	}
	
}
