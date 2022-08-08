package week01.boj_2447;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


// 별찍기
public class SJW {
	static char[][] star;
	static int limit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		star = new char[n][n];
		/**
		 * star의 초기값은 공백과 다르기 때문에 star를 ' '으로 초기화
		 * **/
		for (int i = 0; i < n; i++) {
			Arrays.fill(star[i],' ');
		}
		StringBuilder sb = new StringBuilder();
		int num = 1;
		for (int i = 1; i <= 8; i++) {
			num *= 3;
			if (num == n) {
				limit = i;
				break;
			}
		}
		star[0][0] = '*';
		astring(0);
		// ToPrint : star 출력문
		for (char[] cs : star) {
			for (char c : cs) {
				sb.append(c);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	// Bottom to Top 방식으로 3x3 배열 -> 9x9 -> 27x27 방식
	static void astring(int count) {
		// 탈출 조건
		if (count == limit) {
			return ;
		}
		
		int size = (int) Math.pow(3, count);

		for (int i = 1; i < 9; i++) {
			int x = (i)/3;
			int y = (i)%3;
			if (i == 4) continue;
			for (int j = x*size; j < x*size+size; j++) {
				for (int j2 = y*size; j2 < y*size+size; j2++) {
					star[j][j2] = star[j-x*size][j2-y*size];
				}
			}
		}
		astring(count+1);
	}
}
