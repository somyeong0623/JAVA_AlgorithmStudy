package week06.boj_1405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *(이동경로가 단순할 확률) = 1 - (이동경로가 단순하지 않을 확률)로 구했다.
 * 단순하지 않는 경우는 이미 방문한 곳을 또 방문하는 경우이다.
 *
 */
//1405. 미친 로봇 
public class Somyeong {
	static int n;
	static double p[];
	static double answer;
	static double sum; // 단순하지 않을 확률의 합
	static boolean visited[][];
	static int r, c;
	static int dr[] = { 0, 0, 1, -1 }; // 동 서 남 북
	static int dc[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		p = new double[4];
		visited = new boolean[29][29];
		n = Integer.parseInt(st.nextToken());
		for (int i = 0; i < 4; i++) {
			p[i] =Double.parseDouble(st.nextToken());
		}
		
		// N<=14이므로 29*29 배열을 선언해서 딱 가운데 위치(14,14)에 현재 로봇이 있다고 가정한다.
		r = 14;
		c = 14;
		visited[r][c]=true;
		dfs(0, r, c, 1);
		answer=1-sum;
		System.out.println(answer);

	}

	public static void dfs(int cnt, int r, int c, double pro) { //(이동횟수, 로봇의 r좌표, 로봇의 c좌표, 현재지점에 오는데에 필요한 확률)
		if (cnt == n) {
			return;
		}
	
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];

			if(visited[nr][nc]==false) { 
				visited[nr][nc]=true;
				dfs(cnt + 1, nr, nc, pro * (p[i] / 100));
				visited[nr][nc]=false;
			}
			else // 이미 방문했던곳을 또 방문할경우 현재까지의 확률을 sum에 더해주고 더이상 dfs를 들어가지 않는다.
				sum+=pro*(p[i]/100);

		}
	}

}
