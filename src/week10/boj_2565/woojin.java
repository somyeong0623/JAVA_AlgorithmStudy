package week10.boj_2565;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static class go implements Comparable<go> {

		int x, y;

		public go(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(go o) {
			// TODO Auto-generated method stub
			return this.x - o.x;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		// 교차 판단 ??

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());

		ArrayList<go> q = new ArrayList<>();

		for (int i = 0; i < n; i++) {

			int a1, a2;
			StringTokenizer s = new StringTokenizer(in.readLine(), " ");
			a1 = Integer.parseInt(s.nextToken());
			a2 = Integer.parseInt(s.nextToken());

			q.add(new go(a1, a2));

		}

		Collections.sort(q);

		int arr[] = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = q.get(i).y;
		}

		int dp[] = new int[n + 1];
		int answer = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				
				if(arr[j]<arr[i]&&dp[i]<dp[j]+1)
					dp[i]=dp[j]+1;
			}
			answer=Math.max(answer, dp[i]);

		}
		System.out.println(n-answer);
	}

}
