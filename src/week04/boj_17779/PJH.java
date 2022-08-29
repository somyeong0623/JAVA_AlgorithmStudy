package week04.boj_17779;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PJH {
	static int[][] table;
	static int N;
	static ArrayList<Info> info_list;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int answer = Integer.MAX_VALUE;
		N = Integer.parseInt(bf.readLine());
		table = new int[N + 1][N + 1];
		info_list = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= N; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int d1 = 1; d1 <= 20; d1++) {
			boolean check = true;
			for (int d2 = 1; d2 <= 20; d2++) {
				if (d1 + d2 + 1 <= N) {
					for (int x = 1; x + d1 + d2 <= N; x++) {
						if (d1 + d2 + 1 <= N) {
							for (int y = d1 + 1; y + d2 <= N; y++) {
								info_list.add(new Info(d1, d2, x, y));
								check = false;
							}
						}
					}
				}
			}
			if (check) {
				break;
			}
		}

		int[][] new_t = new int[N + 1][N + 1];

		for (Info tmp : info_list) {
			int[] nums = new int[5];
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			new_t = new int[N + 1][N + 1];
			for (int i = 0; i <= tmp.d1; i++) {
				new_t[tmp.x + i][tmp.y - i] = 5;
			}
			for (int i = 0; i <= tmp.d2; i++) {
				new_t[tmp.x + i][tmp.y + i] = 5;
			}
			for (int i = 0; i <= tmp.d2; i++) {
				new_t[tmp.x + tmp.d1 + i][tmp.y - tmp.d1 + i] = 5;
			}
			for (int i = 0; i <= tmp.d1; i++) {
				new_t[tmp.x + tmp.d2 + i][tmp.y + tmp.d2 - i] = 5;
			}
			
			for(int i = 1 ; i <= N ; i++) {
				int start = -1;
				int end = -1;
				for(int j =1; j<= N; j++) {
					if(new_t[i][j]==5){
						if(start==-1) {
							start = j;
						}else {
							end = j;
						}
					}
				}
				if(end!=-1) {
					for(int k = start; k <= end;k++) {
						new_t[i][k] = 5;
					}
				}
				
				
			}

			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (new_t[i][j] == 5) {
						nums[4] += table[i][j];
					} else if (1 <= i && i < tmp.d1 + tmp.x && 1 <= j && j <= tmp.y) {
						nums[0] += table[i][j];
						new_t[i][j] = 1;
					} else if (1 <= i && i <= tmp.d2 + tmp.x && tmp.y < j && j <= N) {
						nums[1] += table[i][j];
						new_t[i][j] = 2;
					} else if (tmp.x + tmp.d1 <= i && i <= N && 1 <= j && j < tmp.y - tmp.d1 + tmp.d2) {
						nums[2] += table[i][j];
						new_t[i][j] = 3;
					} else if (tmp.x + tmp.d2 < i && i <= N && tmp.y - tmp.d1 + tmp.d2 <= j && j <= N) {
						nums[3] += table[i][j];
						new_t[i][j] = 4;
					}

				}
			}

//			for (int i = 1; i <= N; i++) {
//				System.out.println(Arrays.toString(new_t[i]));
//			}

			for (int i = 0; i < 5; i++) {
				min = Math.min(min, nums[i]);
				max = Math.max(max, nums[i]);
			}
			answer = Math.min(answer, max - min);
//			System.out.println(tmp);
//			System.out.println(Arrays.toString(nums));
		}
		System.out.println(answer );

	}
}

class Info {
	int d1;
	int d2;
	int x;
	int y;

	public Info(int d1, int d2, int x, int y) {
		this.d1 = d1;
		this.d2 = d2;
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Info [d1=" + d1 + ", d2=" + d2 + ", x=" + x + ", y=" + y + "]";
	}

}