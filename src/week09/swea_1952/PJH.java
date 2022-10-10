package week09.swea_1952;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PJH {
	static int[] price;
	static int[] input;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			sb.append("#"+tc+" ");
			price = new int[4];
			input = new int[12];
			
			st = new StringTokenizer(bf.readLine());
			for(int i = 0 ; i < 4;i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(bf.readLine());
			for(int i = 0 ; i < 12;i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			int[] dp = new int[12];
			
			dp[0] = Math.min(price[0]*input[0], price[1]);
			dp[1] = dp[0] + Math.min(price[0]*input[1], price[1]);
			dp[2] = Math.min(price[2],dp[1] + Math.min(price[0]*input[2], price[1]));
			for(int i = 3;i<12;i++) {
				int month_day = Math.min(price[0]*input[i], price[1]); // 1일 계산 vs 한달계산
				int month3 = dp[i-3] + price[2]; //3개월 이용 + 3개월 이전까지의 가장 최선의 선택
				dp[i] = Math.min(month3, month_day + dp[i-1]); // 
			}
			
			
			sb.append(Math.min(dp[11], price[3])).append("\n");
		}
		System.out.println(sb);
	}


}
