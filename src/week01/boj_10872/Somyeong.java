package week01.boj_10872;

import java.util.Scanner;

//10872.팩토리얼 

public class Somyeong {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt();
		System.out.println(func(n));
	}
	
	static int func(int n) {
		if(n==1 || n==0) { //재귀 종료조건 
			return 1;
		}
		return n*func(n-1);
	}
	
}
