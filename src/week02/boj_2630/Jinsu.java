package week02.boj_2630;

import java.util.Scanner;

public class Jinsu {
	static int n, white=0, blue=0;
	static int[][] map;
	public static void main(String[] args) {
		Scanner sc =  new Scanner(System.in);
		n = sc.nextInt();
		
		map = new int[n][n];
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		divide(0, 0, n);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void divide(int r, int c, int size) {
		
		if (check(r, c, size)) {
			if (map[r][c] == 0) {
				white++;
			}
			else {
				blue++;
			}
			return;
		}
		
		divide(r, c, size/2);				//2사분면
		divide(r, c+size/2, size/2);		//1사분면
		divide(r+size/2, c, size/2);		//3사분면
		divide(r+size/2, c+size/2, size/2);	//4사분면
		
	}

	private static boolean check(int row, int col, int size) {
		int color = map[row][col];
		
		for (int i=row; i<row+size; i++) {
			for (int j=col; j<col+size; j++) {
				if (map[i][j] != color)
					return false;
			}
		}
		
		return true;
	}

}
