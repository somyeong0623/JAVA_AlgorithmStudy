package week01.boj_10870;

import java.util.Scanner;

public class Somyeong {
// 10870. 피보나치 수 5
	static int n;
	public static void main(String[] args) {
		
		Scanner sc= new Scanner(System.in);
		n=sc.nextInt();
		
		System.out.println(func(n));
	}
	static int func(int n) {
		if(n==0)
			return 0;
		if(n==1)
			return 1;
		if (n==2)
			return 1;
		
		return func(n-2)+func(n-1);
	}
}
