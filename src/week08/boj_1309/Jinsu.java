package week08.boj_1309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jinsu {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] d = new int[n+1][3];
		
		
		if(n==1) System.out.println(3);
		else {
			d[2][0] = 3;
			d[2][1] = 2;
			d[2][2] = 2;
			
			for(int i=3; i <= n; i++) {
				d[i][0] = (d[i-1][0] +d[i-1][1] + d[i-1][2])%9901;
				d[i][1] = (d[i-1][0] + d[i-1][2])%9901;
				d[i][2] = (d[i-1][0] + d[i-1][1])%9901;
			}
			
			System.out.println((d[n][0] + d[n][1] + d[n][2])%9901);
		}
		
	}

}
