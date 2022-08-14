package week02.boj_2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 색종이 만들기
public class Jul {
	static int[][] graph;
	static int blue,white;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine().trim());
		graph = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		divide(0,0,n,n);
		System.out.println(white);
		System.out.println(blue);
	}
	private static void divide(int i, int j, int k, int l) {
		
		if (check(i,j,k,l)) {
			if (graph[i][j] == 1) {
				blue++;
			}else {
				white++;
			}
//			System.out.println("white :" + white+" blue :"+blue);
			return ;
		}
		if (k-i == 1) {
			return ;
		}
		divide(i,j,(k-i)/2+i,(l-j)/2+j);
		divide(i,(l-j)/2+j,(k-i)/2+i,l);
		divide((k-i)/2+i,j,k,(l-j)/2+j);
		divide((k-i)/2+i,(l-j)/2+j,k,l);
	}
	private static boolean check(int i, int j, int k, int l) {
//		System.out.println(i+" "+j+" "+k+" "+l);
		int color = graph[i][j];
//		System.out.println("color :" + color);
		for (int m = i; m < k; m++) {
			for (int m2 = j; m2 < l; m2++) {
				if (graph[m][m2] != color) {
					return false;
				}
			}
		}
		return true;
	}

}
