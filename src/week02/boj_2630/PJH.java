package week02.boj_2630;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PJH { // 2630
	static int[][] table;
	static int white;
	static int blue;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(bf.readLine());
		white = 0;
		blue = 0;
		table = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 0; j < N; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cut_paper(0, 0, N);
		sb.append(white+ "\n");
		sb.append(blue+ "\n");
		System.out.println(sb);
	}

	static void cut_paper(int x, int y, int n) {

		int paper = check(x,y,n);
		if(paper>=0||n==1) {
			if(table[x][y]==1) blue++;
			else white++;
			return;
		}
		int[] x_arr = { x, x, x + n / 2, x + n / 2 };
		int[] y_arr = { y, y + n / 2, y, y + n / 2 };
		for(int idx = 0; idx<4;idx++) {
			cut_paper(x_arr[idx], y_arr[idx], n / 2);
		}

	}
	static int check(int x, int y, int n) {
		for(int i = x; i < x+n ; i ++) {
			for(int j = y; j < y+n ; j ++) {
				if(table[i][j]!=table[x][y]) {
					return -1;
				}
			}
		}
		return table[x][y];
	}

}