package week04.boj_14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//14888. 연산자 끼워넣기 
public class Somyeong {
	static int n,numbers[]; 
	static int input_oper[]; // 입력받은 연산자갯수 +,-,x,/ 순서
	static int oper[]; // 연산자 n-1개 나열
	static int select[]; // 순열로 만들어진 연산자 나열순서 담아놓는 배열
	static boolean isSelected[];
	static int answer_max = Integer.MIN_VALUE, answer_min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		numbers = new int[n];
		input_oper = new int[4];
		oper = new int[n - 1];
		select = new int[n - 1];
		isSelected = new boolean[n - 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			input_oper[i] = Integer.parseInt(st.nextToken());
		}

		// input_oper의 연산자를 나열해서 oper배열에 채우기 (총 n-1)
		// 1:+ , 2:- , 3:x ,4: /

		int index = 0;
		// 1. +채우기
		for (int i = 0; i < input_oper[0]; i++) {
			oper[index + i] = 1;
		}
		index += input_oper[0];
		for (int i = 0; i < input_oper[1]; i++) {
			oper[index + i] = 2;
		}
		index += input_oper[1];
		for (int i = 0; i < input_oper[2]; i++) {
			oper[index + i] = 3;
		}
		index += input_oper[2];
		for (int i = 0; i < input_oper[3]; i++) {
			oper[index + i] = 4;
		}

		perm(0);
		System.out.println(answer_max);
		System.out.println(answer_min);

	}

	// 순열로 n-1개의 연산자 나열 구하기
	static void perm(int cnt) {
		if (cnt == n - 1) { // 순열하나 구할때마다 calculate
			calculate(select);

			return;
		}
		for (int i = 0; i < n - 1; i++) {
			if (isSelected[i])
				continue;

			isSelected[i] = true;
			select[cnt] = oper[i];
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}

	static void calculate(int[] oper) {
		int result = numbers[0];// 첫번째 원소로 시

		for (int i = 1; i < n; i++) { // numbers는인덱스 1부터 계산해야하고, oper는 인덱스0부터 유효함
			if (oper[i - 1] == 1)
				result += numbers[i];
			else if (oper[i - 1] == 2)// 뺄셈
				result -= numbers[i];
			else if (oper[i - 1] == 3)
				result *= numbers[i];
			else
				result /= numbers[i];

		} 
		answer_max = Math.max(answer_max, result);
		answer_min = Math.min(answer_min, result);

	}
}
