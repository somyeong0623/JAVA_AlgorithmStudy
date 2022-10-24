package week11.boj_15486;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());

		int dp[] = new int[n + 2];
		int time[] = new int[n + 2];
		int val[] = new int[n + 2];

		for (int i = 1; i <= n; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine(), " ");
			int a1, a2;
			a1 = Integer.parseInt(s.nextToken());
			a2 = Integer.parseInt(s.nextToken());

			time[i] = a1;
			val[i] = a2;

		}
		
		int max=0;
		for (int i = 1; i <= n; i++) {
			max=Math.max(dp[i], max);
			
			if(i+time[i]>n+1)
			{
				continue;
			}
			
			dp[time[i]+i]=Math.max(dp[time[i]+i], val[i]+max);
		}
		max=Integer.MIN_VALUE;
		for (int i = 1; i <= n+1; i++) {
			max=Math.max(dp[i],max);
		}
		System.out.println(max);
		//System.out.println(Arrays.toString(dp));
	}