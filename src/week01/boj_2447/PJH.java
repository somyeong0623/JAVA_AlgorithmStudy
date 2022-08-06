package week01.boj_2447;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class PJH {
	static char[][] table;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int input = Integer.parseInt(br.readLine());
		table = new char[input][input];
		for(int i = 0 ;i<input;i++) {
			Arrays.fill(table[i], ' ');
		}
		solution(input, 0, 0);
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table.length; j++) {
				sb.append(table[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	static void solution(int size, int x, int y) {

		if (size == 3) { //가장 작은 단위 
			for (int i = x; i < x + 3; i++) {
				for (int j = y; j < y + 3; j++) {
					table[i][j] = '*';
				}
			}
			table[x + 1][y + 1] = ' '; // 마지막부분에 빈 부분을 추가
			
		} else {
			for (int i = x; i < x + size; i += (size / 3)) { //현재 포커스에서 가로세로 삼등분씩 해서 좌표로 재귀호출
				for (int j = y; j < y + size; j += (size / 3)) {
					if (!((i == (x + (size / 3))) && (j == (y + (size / 3))))) { //마지막 빈 부분 무시하기 위한 조건
						solution(size / 3, i, j);
					}
				}
			}
		}

	}


}
