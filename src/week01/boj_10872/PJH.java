package week01.boj_10872;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PJH {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		System.out.println(factorial(N));
		
	}
	public static int factorial(int a) {
		if(a <= 1) {
			return 1; 
		}
		return a*factorial(a-1);
		
		
	}
}
