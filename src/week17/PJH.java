//21758 꿀따기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static boolean[] check;
	static int[] select;

	static int[] table;
	static int[] sum_table;

	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		answer = -1;
		int N = Integer.parseInt(bf.readLine());
		table = new int[N];
		select = new int[2];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			table[i] = Integer.parseInt(st.nextToken());
		}

		for (int n = 0; n < N; n++) {
			int sum_cnt;
			check = new boolean[N];
			check[n] = true;

			sum_table = new int[N];
			sum_cnt = table[n];
			for (int left = n - 1; left >= 0; left--) {
				sum_table[left] = sum_cnt;
				sum_cnt += table[left];
			}

			sum_cnt = table[n];
			for (int right = n + 1; right < N; right++) {
				sum_table[right] = sum_cnt;
				sum_cnt += table[right];
			}

			//System.out.println(Arrays.toString(check));
			//System.out.println(Arrays.toString(sum_table));
			combi(0, 0, n);
			//System.out.println();
		}
		System.out.println(answer);
	}

	static void combi(int cnt, int start, int target) {
		if (cnt == 2) {
			score(select[0], select[1], target);

			return;
		}
		for (int i = start; i < table.length; i++) {
			if (!check[i]) {
				check[i] = true;
				select[cnt] = i;
				combi(cnt + 1, i + 1, target);
				check[i] = false;
			}
		}

	}

	static void score(int select1, int select2, int target) {
		int tmp_score = sum_table[select1] + sum_table[select2];
		if (target < select1) {// 꿀통 왼쪽에 위치
			tmp_score -= table[select1];
		} else if (target > select2) {// 꿀통 왼쪽에 위치
			tmp_score -= table[select2];
		}
		if (answer < tmp_score) {
			answer = tmp_score;
		}
	}

}