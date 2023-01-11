package week20.boj_15724;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jul {
	static int n,m,k;
	static int[][] graph,acc;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n+1][m+1];
		acc = new int[n+1][m+1];
		for (int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < m+1; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
//		System.out.println();
//		for (int[] is : graph) {
//			System.out.println(Arrays.toString(is));
//		}
//		System.out.println("==============================");
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < m+1; j++) {
//				if(j==0) acc[i][j] = graph[i][j];
//				else {
					acc[i][j] = graph[i][j]+ acc[i][j-1];
//				}
			}
		}
//		for (int[] is : acc) {
//			System.out.println(Arrays.toString(is));
//		}
//		System.out.println("==============================");
		for (int i = 1; i < m+1; i++) {
			for (int j = 1; j < n+1; j++) {
//				if(j==0) acc[j][i] = acc[j][i];
//				else {
					acc[j][i] = acc[j][i] + acc[j-1][i];
//				}
			}
		}
		
//		System.out.println("==============================");
//		for (int[] is : acc) {
//			System.out.println(Arrays.toString(is));
//		}
		
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		ArrayList<Integer> res = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
//			System.out.println(x1+" "+y1+" "+x2+" "+y2);
			int result = acc[x2][y2];
			result -=acc[x1-1][y2];
			result -=acc[x2][y1-1];
			result +=acc[x1-1][y1-1];
			res.add(result);
		}
		for (Integer integer : res) {
			System.out.println(integer);
		}
	}

}

//4 4
//9 14 29 7
//1 31 6 13
//21 26 40 16
//8 38 11 23
//2
//3 3 3 3
//1 1 1 1
