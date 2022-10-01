package week08.boj_1309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 동물원
public class Jul {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int n = Integer.parseInt(st.nextToken());
		int[][] d = new int[n+1][3];
		if(n==1) {
			System.out.println(3);
		}else {
			d[2][0] = 3;
			d[2][1] = 2;
			d[2][2] = 2;
			for (int i = 3; i < n+1; i++) {
				d[i][0] = (d[i-1][0]+d[i-1][1]+d[i-1][2])%9901;
				d[i][1] = (d[i-1][0]+d[i-1][2])%9901;
				d[i][2] = (d[i-1][0]+d[i-1][1])%9901;
			}
			System.out.println((d[n][0]+d[n][1]+d[n][2])%9901);
		}
	}

}
