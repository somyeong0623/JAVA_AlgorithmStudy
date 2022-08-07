package week01.boj_11729;

import java.util.Scanner;

public class Jinsu {
	static StringBuilder sb = new StringBuilder();
	static int cnt = 0;
	private static void hanoi(int n, int start, int tmp, int end) {
		if (n == 0) {
			return;
		}
		else {
			cnt++;
			hanoi(n-1, start, end, tmp);
			sb.append(start+" "+end+"\n");
			hanoi(n-1, tmp, start, end);
		}
		
		
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		hanoi(N, 1, 2, 3);
		System.out.println(cnt);
		System.out.println(sb);
	}

}
