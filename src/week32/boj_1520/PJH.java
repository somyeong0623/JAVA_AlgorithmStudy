import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static boolean[][] visit;
	static int[][] dp;
	static int M, N;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		visit = new boolean[M][N];
		dp = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		System.out.println(dp[0][0]);
	}

	static int dfs(int r, int c) {
		if (r == M - 1 && c == N - 1) {
			return 1;
		}
		if (!visit[r][c]) {
			for (int i = 0; i < 4; i++) {
				int nr = r+dr[i];
				int nc = c+dc[i];
				if(check(nr,nc)&&map[nr][nc]<map[r][c]) {
					dp[r][c] += dfs(nr,nc);
					visit[r][c]=true;
				}
			}
		}

		return dp[r][c];
	}

	static boolean check(int r, int c) {
		if (r < 0 || c < 0 || r >= M || c >= N)
			return false;
		return true;
	}

}
