package week04.boj_14888;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PJH {
	static int numbers[];
	static int operator[];
	static int num_min;
	static int num_max;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(bf.readLine());
		numbers = new int[N];
		operator = new int[4];
		num_min = Integer.MAX_VALUE;
		num_max = Integer.MIN_VALUE;

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		bfs(1, numbers[0]);
		System.out.println(num_max);
		System.out.println(num_min);
	}

	static void bfs(int cnt, int num) {
		if (cnt == N) {
			if (num_min > num)
				num_min = num;
			if (num_max < num)
				num_max = num;
			return;
		}

		if (operator[0] > 0) {
			operator[0]--;
			bfs(cnt + 1, num + numbers[cnt]);
			operator[0]++;
		}
		if (operator[1] > 0) {
			operator[1]--;
			bfs(cnt + 1, num - numbers[cnt]);
			operator[1]++;
		}
		if (operator[2] > 0) {
			operator[2]--;
			bfs(cnt + 1, num * numbers[cnt]);
			operator[2]++;
		}
		if (operator[3] > 0) {
			operator[3]--;
			bfs(cnt + 1, num / numbers[cnt]);
			operator[3]++;
		}

	}

}