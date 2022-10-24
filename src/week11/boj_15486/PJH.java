package week11.boj_15486;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class PJH {
	static int[] T;
	static int[] P;
	static int[] dp;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(bf.readLine());
		T = new int[N];
		P = new int[N];
		dp = new int[N + 1];
		answer = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			T[i] = t;
			P[i] = p;
		}

		for (int i = 0; i < N; i++) {
			answer = Math.max(answer, dp[i]);
			if (i + T[i] < dp.length && dp[i + T[i]] < answer + P[i]) {
				dp[i + T[i]] = answer + P[i];
			}
			 System.out.println(Arrays.toString(dp));
		}
		answer = Math.max(answer, dp[N]);
		System.out.println(answer);

	}

}