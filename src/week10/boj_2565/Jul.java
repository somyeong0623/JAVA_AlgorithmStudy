package week10.boj_2565;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Jul {
	static int n ,result;
	static int[] d;
	static int[][] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		graph = new int[n][2];
		d = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[i]=new int[] {a,b};
		}
		Arrays.sort(graph,new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0]-o2[0];
			}
		});
		for (int i = 0; i < n; i++) {
			d[i] = 1;
			System.out.println(Arrays.toString(graph[i]));
			for (int j = 0; j < i; j++) {
				if(graph[i][1]>graph[j][1]) {
					d[i] = Math.max(d[i], d[j]+1);
				}
			}
			result = Math.max(result, d[i]);
		}
		System.out.println(Arrays.toString(d));
		System.out.println(n-result);
	}

}
