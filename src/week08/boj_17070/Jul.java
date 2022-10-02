package week08.boj_17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 파이프 옮기기 1
public class Jul {
	static int n,result;
	static int[][] graph;
	static int[][][] d;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		graph  = new int[n+1][n+1];
		d = new int[n+1][n+1][2];
		for (int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 1; j < n+1; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(1,1,1,2,0);
		System.out.println(result);
	}
	// 가로 0 세로 1 대각선 2
	private static void dfs(int x, int y, int xp, int yp,int type) {
		// x,y,x',y' 순서 x',y'이 도착 조건
		System.out.println(x+","+y+" "+xp+","+yp);
		if(xp==n && yp == n) {
			d[xp][yp][type]++;
			return;
		}
		if(x-xp==0&&y-yp<0) {		// 파이프 가로
			//가로로 가는것관 대각선 아래로 가는것
			//가로
			if(yp+1<=n && graph[xp][yp+1]==0) {
				dfs(xp,yp,xp,yp+1,0);
			}
			
			if(xp+1<=n && yp+1<=n && graph[xp+1][yp+1]==0 && graph[xp+1][yp]==0&&graph[xp][yp+1]==0) {// 대각선
				dfs(xp,yp,xp+1,yp+1,2);
			}
		}
		if(x-xp<0&&y-yp==0) {	//파이프 세로
			if(xp+1<=n && graph[xp+1][yp] == 0) {
				dfs(xp,yp,xp+1,yp,1);
			}
			if(xp+1<=n && yp+1<=n && graph[xp+1][yp+1]==0 && graph[xp+1][yp]==0&&graph[xp][yp+1]==0) {
				dfs(xp,yp,xp+1,yp+1,2);
			}
		}
		if(x-xp<0&&y-yp<0) {	//파이프 대각선
			if(yp+1<=n && graph[xp][yp+1]==0) {
				dfs(xp,yp,xp,yp+1,0);
			}
			if(xp+1<=n && graph[xp+1][yp] == 0) {
				dfs(xp,yp,xp+1,yp,1);
			}
			if(xp+1<=n && yp+1<=n && graph[xp+1][yp+1]==0 && graph[xp+1][yp]==0&&graph[xp][yp+1]==0) {
				dfs(xp,yp,xp+1,yp+1,2);
			}
		}
		
		
		
	}

}
