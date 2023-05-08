import java.io.*;
import java.util.*;

public class Main {
	static int M,N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		int[] table = new int[2*M-1];
		int[][] answer = new int[M][M];
		Arrays.fill(table,1);
		for(int i = 0 ; i < N ;i++) {
			st = new StringTokenizer(br.readLine());
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			int index = table.length-1;
			int cnt = 0;
			while(0!=two--) {
				table[index--] += 2;
			}
			while(0!=one--) {
				table[index--] += 1;
			}
			
		}
		for(int i = 0 ; i < M-1;i++) {
			answer[M-1-i][0] = table[i];
		}
		for(int i = 0 ; i < M;i++) {
			answer[0][i] = table[M-1+i];
		}
		for(int i = 1 ; i < M ; i++) {
			for(int j = 1 ; j < M ; j++) {
				answer[i][j] = answer[0][j];
			}
		}
		for(int i = 0 ; i < M ; i ++) {
			for(int j = 0 ; j < M ; j++) {
				System.out.print(answer[i][j]+" ");
			}
			System.out.println();
		}
	}



}