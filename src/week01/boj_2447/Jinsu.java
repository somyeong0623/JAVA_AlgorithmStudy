package week01.boj_2447;

import java.util.Scanner;

public class Jinsu {
	
	static char[][] arr;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		arr = new char[N][N];
		
		recursion(0, 0, N, false);
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static void recursion(int x, int y, int N, boolean blank) {
		if (blank) {
			for (int i = x; i < x + N; i++) {
				for (int j = y; j < y + N; j++) {
					arr[i][j] = ' ';
				}
			}
			return;
		}
		
		if (N==1) {
			arr[x][y] = '*';
			return;
		}
		
		int size = N / 3;
		int count = 0;
		for (int i = x; i < x + N; i += size) {
			for (int j = y; j < y + N; j += size) {
				count++;
				if (count == 5) { // 공백 칸일 경우
					recursion(i, j, size, true);
				} else {
					recursion(i, j, size, false);
				}
			}
		}
	}

}
