package week05.boj_15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 사다리 조작
public class Jul {
	static int n,m,h,answer;
	static int[][] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		graph = new int[h+1][n+2];
		answer =Integer.MAX_VALUE;
		for (int i = 0; i < graph.length; i++) {
			Arrays.fill(graph[i], 0);
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 1;
		}
		dfs(1,1,0);
		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}

	private static void dfs(int i, int j, int count ) {
		if(count >= answer) return;
		if(count > 3) return;
		if(check()) {
			answer = Math.min(answer, count);
			return ;
		}
		for (int k = i; k < h+1; k++) {
			for (int k2 = 1; k2 < n+1; k2++) {
				if(graph[k][k2] != 1) {
					graph[k][k2] = 1;
					dfs(k,k2+2,count+1);
					graph[k][k2] = 0;
				}
			}
		}
		
		
	}

	private static boolean check() {
		for (int i = 1; i < n+1; i++) {
			int down = i;
			for (int j = 1; j < h+1; j++) {
				if(graph[j][down] == 1) {
					down++;
					continue;
				}
				if(graph[j][down-1] == 1) {
					down--;
					continue;
				}
			}
			if(down != i) return false;
		}
		return true;
	}

}
