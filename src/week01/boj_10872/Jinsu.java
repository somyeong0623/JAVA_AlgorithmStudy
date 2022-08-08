package week01.boj_10872;

import java.util.Scanner;

public class Jinsu {
	
	private static int Factorial(int n) {
		if (n <= 1)
			return 1;
		
		return n*Factorial(n-1);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		System.out.println(Factorial(n));
	}
}
