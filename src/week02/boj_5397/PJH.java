package week02.boj_5397;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class PJH {// 5397

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String input = bf.readLine();
			Stack<Character> answer = new Stack<>();
			Stack<Character> record = new Stack<>();

			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) == '<') { // 왼쪽
					if (!answer.isEmpty()) {
						record.push(answer.pop());
					}
				} else if (input.charAt(i) == '>') { // 오른쪽
					if (!record.isEmpty()) {
						answer.push(record.pop());
					}
				} else if (input.charAt(i) == '-') {
					if (!answer.isEmpty()) {
						answer.pop();
					}
				} else {
					answer.push(input.charAt(i));
				}

			}
			while (!answer.isEmpty()) {
				record.push(answer.pop());
			}
			while (!record.isEmpty()) {
				sb.append(record.pop());
			}

			sb.append("\n");

		}

		System.out.println(sb);

	}

}
