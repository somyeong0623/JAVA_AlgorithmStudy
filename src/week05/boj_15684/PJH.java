package week05.boj_15684;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class PJH {
	static int[][] table;
	static int N, M, H;
	static boolean able = false;
	static int answer = Integer.MAX_VALUE;
	static int cnt = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		table = new int[N][H];
		
		while((M--)>0) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1; // 1 1 이면 여기서는 0과 1줄의 0번째부터 스왑 
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			table[b][a] = 1;
			table[b+1][a] = -1;
		}
		// N-2까지 입력받고, H-1만큼입력받고
		DFS(0,0,0);
		if(able) {
			sb.append(answer);
		}else {
			sb.append(-1);
		}
		System.out.println(sb);
		//System.out.println(cnt);
	}

	public static void DFS(int depth, int start_i, int start_j) {
		cnt++;
		if (check()) {
			able = true;
			answer = Math.min(answer, depth);
			//System.out.println("찾음");
			return;
		}
		if(depth<=answer-2&&depth<=2) {
			for (int i = start_i; i < N - 1; i++) {
				for (int j = start_j; j < H; j++) {
					//System.out.println(as + " " + answer);
					if(answer>(depth+1)) {
						if (table[i][j] == 0 && table[i + 1][j]==0) {
							table[i][j] = 1;
							table[i + 1][j] = -1;
							DFS(depth + 1, start_i,start_j);
							table[i][j] = 0;
							table[i + 1][j] = 0;

						}
					}
				}
			}
		}
		

	}

	public static boolean check() {

		for (int i = 0; i < table.length; i++) {
			int answer = i;
			for (int j = 0; j < table[0].length; j++) {
				answer += table[answer][j];
			}
			if (i != answer)
				return false;

		}

		return true;
	}
}

