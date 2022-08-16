package week02.boj_5397;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

//5397. 키로거 
/*
 처음에 생각한 풀이 방법은 Stack을 두개 선언해서 <, > 명령어를 기준으로 문자를  담은다음 마지막에 stack1, stack2를 한번에 출력하는 방법이다.
 그런데 스택은 LIFO니까 안쪽에서부터 출려하는게 애매할것같아서 양방향 삽입,삭제가 가능한 Deque를 이용하였다.
 시간초과가나서 Deque가 문제인가 싶었는데 String Builder를 안써서였다. (주의하기)
 */
public class Somyeong {
	static int TC;
	static String answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		StringBuilder sb;
		for (int t = 0; t < TC; t++) {
			sb=new StringBuilder();
			Deque<Character> dq1 = new ArrayDeque<>();
			Deque<Character> dq2 = new ArrayDeque<>();

			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				char command = str.charAt(j);
				if (command == '<') {
					if (dq1.isEmpty())
						continue;
					char temp = dq1.removeLast();// 마지막을 제거한다음 리턴
					dq2.addFirst(temp);
				} else if (command == '>') {
					if (dq2.isEmpty())
						continue;
					char temp = dq2.removeFirst();// 마지막을 제거한다음 리턴
					dq1.addLast(temp);
				} else if (command == '-') {
					if (dq1.isEmpty())
						continue;
					dq1.removeLast();
				} else { // 문자이면
					dq1.addLast(command);
				}

			}
			for (char c : dq1) {
				sb.append(c);
			}
			for (char c : dq2) {
				sb.append(c);
			}
			System.out.println(sb);
		}

	}
}
