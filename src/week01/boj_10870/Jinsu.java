package week01.boj_10870;

import java.util.Scanner;

public class Jinsu {

	private static int Fibonacci(int n) {
		if (n <= 1)
			return n;
		else
			return Fibonacci(n-1)+ Fibonacci(n-2);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		System.out.println(Fibonacci(n));
	}

}
