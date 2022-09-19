package week06.boj_1405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 미친 로봇
public class Jul {
	static int n,E,W,S,N,unsimple,total;
	static int[] per;
	static boolean[][] visited;
	static int x;
	static int y;
	static int[] com;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		per = new int[] {E,W,S,N};
		com = new int[n];
		cal(0);
		float a = (float) ((total-unsimple)*(1.0)/(total));

		System.out.printf("%.10f",a);
//		System.out.println();
//		System.out.println(total+" "+(total-unsimple));
	}
	private static void cal(int count) {
		if(count == n) {
			total++;
			if(!isValid())unsimple++;
			return;
		}
		for (int i = 0; i < 4; i++) {
			if(per[i]>0) {
				per[i]--;
				com[count] = i;
				cal(count+1);
				per[i]++;
			}
		}
	}
	private static boolean isValid() {
//		System.out.println(Arrays.toString(com)+" "+unsimple);
		visited = new boolean[30][30];
		x = 15;
		y = 15;
		visited[x][y] = true;
		for (int i = 0; i < com.length; i++) {
			if(com[i]==0) {
				y++;
			}else if(com[i] == 1) {
				y--;
			}else if(com[i] == 2) {
				x++;
			}else if(com[i] == 3) {
				x--;
			}
			if(visited[x][y]) return false;
			visited[x][y] =true;
		}
		return true;
	}
	
}
