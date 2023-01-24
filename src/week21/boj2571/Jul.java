package week21.boj2571;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jul {
	static int n,result;
	static int[][] graph;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		graph = new int[100][100];
		result = -1;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int j = x; j < x+10; j++) {
				for (int j2 = y; j2 < y+10; j2++) {
					graph[j][j2]=1;
				}
			}
		}

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length-1; j++) {
				
				if(graph[i][j] != 0 && graph[i][j+1] != 0) graph[i][j+1] = graph[i][j]+1;
			}
		}
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				int ma = 100;
				for (int k = j; k < 100; k++) {
					ma = Math.min(ma, graph[k][i]);
					if(ma==0)break;
					result = Math.max(result, ma*(k-j+1));
				}
			}
		}
		System.out.println(result);
	}
	

}
