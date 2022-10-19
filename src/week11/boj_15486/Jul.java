package week11.boj_15486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jul {
	static int n;
	static int[][] graph;
	static int[] d;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		graph = new int[n][2];
		d= new int[n+1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			d[n-i-1] = graph[i][1];
		}
		
//		System.out.println(Arrays.toString(d));
		for (int i = n-1; i > -1; i--){
			if(graph[i][0]+i>n) d[i] = d[i+1];
			else d[i] = Math.max(d[i+1], graph[i][1]+d[i+graph[i][0]]);
//			System.out.println(i);
//			System.out.println(i+"번쨰 -> "+Arrays.toString(d));
		}
		System.out.println(d[0]);
	}

}
