package week08.boj_1309;

import java.util.Scanner;

//1309. 동물원 
public class Somyeong {
	static int N;
	static int[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new int[N + 1][3];

		// dp[i][0]: i번 행의 1열 2열에 둘다 사자가 없는 경우
		// dp[i][1]: i번 행의 1열에만 사자가 있는 경우
		// dp[i][2]: i번 행의 2열에만 사자가 있는 경우

		dp[1][0] = 1;
		dp[1][1] = 1;
		dp[1][2] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2])%9901;
			dp[i][1] = (dp[i - 1][0] + dp[i - 1][2])%9901;
			dp[i][2] = (dp[i - 1][0] + dp[i - 1][1])%9901;
		}
		System.out.println((dp[N][0]+dp[N][1]+dp[N][2])%9901);
	}

}
