package week01.boj_1244;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PJH {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());
		int[] table = new int[input];
		String[] tmp_table = br.readLine().split(" ");
		for (int i = 0; i < input; i++) {
			table[i] = Integer.parseInt(tmp_table[i]);
		}

		int cnt_student = Integer.parseInt(br.readLine());
		for (int i = 0; i < cnt_student; i++) {

			String[] tmp_student = br.readLine().split(" ");
			
			int idx = Integer.parseInt(tmp_student[1]);
			
			
			if (tmp_student[0].equals("1")) { // 남자
				for (int init_idx = idx - 1; init_idx < input; init_idx += idx) {
					table[init_idx] = table[init_idx] == 1 ? 0 : 1;
				}
			} else if (tmp_student[0].equals("2")) {// 여자
				idx--;
				table[idx] = table[idx] == 1 ? 0 : 1;
				for (int cnt = 1; ((idx + cnt) < input) && ((idx - cnt) >= 0); cnt++) {
					if (table[idx + cnt] == table[idx - cnt]) {
						table[idx + cnt] = table[idx + cnt] == 1 ? 0 : 1;
						table[idx - cnt] = table[idx - cnt] == 1 ? 0 : 1;
					}else {
						break;
					}
				}
			}

		}
		for (int k = 0; k < input; k++) {
			System.out.print(table[k]);
			if (k % 20 == 19) {
				System.out.print(" ");
				System.out.println();
			} else if (k != input - 1) {
				System.out.print(" ");
			}
		}
	}
}