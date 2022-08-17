package week02.boj_15686;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PJH{// 15686
	static int[][] table;
	static int[] select;
	static int[] numbers;
	static List<int[]> k_point; // 치킨집
	static List<int[]> c_point; // 고객
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String[] tmp_input;
		int N, M;

		tmp_input = bf.readLine().split(" ");
		N = Integer.parseInt(tmp_input[0]);
		M = Integer.parseInt(tmp_input[1]);

		k_point = new ArrayList<>(); // x y 거리
		c_point = new ArrayList<>(); // x y 거리
		table = new int[N + 2][N + 2];
		answer = Integer.MAX_VALUE;
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(bf.readLine(), " ");
			for (int j = 1; j < N + 1; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
				if (table[i][j] == 2) {
					int[] tmp = { i, j };
					k_point.add(tmp);
				}
				if (table[i][j] == 1) {
					int[] tmp = { i, j };
					c_point.add(tmp);
				}
			}
		}
		select = new int[k_point.size()];
		numbers = new int[M];
		for (int i = 0; i < select.length; i++) {
			select[i] = i;
		}
		combi(0, 0, M);
		System.out.println(answer);
	}

	public static void combi(int cnt, int start, int M) {
		if (cnt == M) {
			int user_len_sum = 0;
			for (int[] user : c_point) {
				int user_len = Integer.MAX_VALUE;
				for (int i = 0; i < numbers.length; i++) { // 유저와 치킨집과 거리 계산
					int k_idx = numbers[i];
					int target = Math.abs(user[0] - k_point.get(k_idx)[0]) + Math.abs(user[1] - k_point.get(k_idx)[1]);
					user_len = user_len > target ? target : user_len;
				}
				user_len_sum += user_len; //해당 경우의 수의 치킨집 길이합계
			}
			if(user_len_sum<answer) {
				answer = user_len_sum;
			}
			
			
			return;
		}
		for (int i = start; i < k_point.size(); i++) {
			numbers[cnt] = select[i];
			combi(cnt + 1, i + 1, M);
		}
	}

}
