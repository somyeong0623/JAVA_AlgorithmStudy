package week06.boj_1405;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class PJH{
	public static double answer = 1.0;
	public static int[] percent;
	public static int[] rotate_r = {0,0,1,-1};
	public static int[] rotate_c = {1,-1,0,0};
	public static int[][] table;
	public static int r;
	public static int c;
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		table = new int[40][40];
		percent = new int[4];
		
		
		r = 20;
		c = 20;
		table[r][c] = 1;
		
		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		for(int i = 0 ; i < 4;i++) {
			percent[i] = Integer.parseInt(st.nextToken());
		}
		
		DFS(N,1.0);
		
		System.out.println(answer);
		
	}
	
	public static void DFS(int N,double val) {
		if(N==0) {
			return;
		}
		
		for(int i = 0 ; i < 4;i++) {
			if(table[r+rotate_r[i]][c+rotate_c[i]]==0) { //갈수 있다.
				r +=rotate_r[i];
				c += rotate_c[i];
				table[r][c] = 1;
				DFS(N-1,val*0.01*percent[i]);
				table[r][c] = 0;
				r -=rotate_r[i];
				c -= rotate_c[i];
				
			}else {
				answer-=val*percent[i]*0.01;
			}
		}
		
	}
	
}
