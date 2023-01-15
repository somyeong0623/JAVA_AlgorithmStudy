import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] table = new int[N + 1][M + 1];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j < M+1; j++) {
				table[i][j] = Integer.parseInt(st.nextToken())+table[i-1][j]+table[i][j-1]-table[i-1][j-1];
			}
		}

		st = new StringTokenizer(bf.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(bf.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int answer = table[r2][c2]-table[r2][c1-1]-table[r1-1][c2]+table[r1-1][c1-1];
			System.out.println(answer);
		}

	}

}
