package week09.swea_1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jul {
	static int t;
	static int day,month,month3,year;
	static int[][] d ;
	static int[] calendar;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		t= Integer.parseInt(st.nextToken());
		for (int i = 1; i < t+1; i++) {
			st = new StringTokenizer(br.readLine().trim());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			month3 = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			d = new int[4][12];
			calendar = new int[12];
			st = new StringTokenizer(br.readLine().trim());
			int start= -1;
			for (int j = 0; j < 12; j++) {
				calendar[j] = Integer.parseInt(st.nextToken());
				if(start == -1 && calendar[j]>0) start = j;
			}
			d[0][start] = calendar[start]*day;
			d[1][start] = month;
			d[2][start] = month3;
			d[3][start] = year;
			int count = 1;
			int tmonthcount = 1;
			for (int j = start+1; j < 12; j++) {
				int minVal = Integer.MAX_VALUE;
				minVal = Math.min(minVal, d[0][j-1]);
				minVal = Math.min(minVal, d[1][j-1]);
				minVal = Math.min(minVal, d[2][j-1]);
				for (int j2 = 0; j2 < 4; j2++) {
					if(j2==0) {
						d[j2][j] = calendar[j]*day+minVal;
					}else if (j2 == 1) {
						if(calendar[j]>0) d[j2][j] = month+minVal;
						else d[j2][j] = minVal;
					}else if (j2 == 2) {
						
						if(j-3<0)d[j2][j] = month3;
						else {
							int m = Math.min(d[0][j-3], d[1][j-3]);
							m = Math.min(m, d[2][j-3]);
							d[j2][j] = m + month3;
						}						
					}else {
						d[j2][j] = year;
					}
				}
			}
//			System.out.println();
//			System.out.println(Arrays.toString(calendar));
//			for (int j = 0; j < 4; j++) {
//				System.out.println(Arrays.toString(d[j]));
//			}
//			System.out.println("-------------------------------");
			int result = Integer.MAX_VALUE;
			result = Math.min(result, d[0][11]);
			result = Math.min(result, d[1][11]);
			result = Math.min(result, d[2][11]);
			result = Math.min(result, d[3][11]);
			System.out.println("#"+i+" "+result);
		}
		
	}

}
