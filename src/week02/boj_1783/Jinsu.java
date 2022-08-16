package week02.boj_1783;
import java.util.Scanner;

public class Jinsu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int result = 0;
		
		if (n == 1) result = 1;
		else if (n == 2) result = Math.min(4, (m+1) / 2);
		else {
			if (m < 7) {
				result = Math.min(4, m);
			}
			else {
				result = m - 2;
			}
		}
		System.out.println(result);
	}

}
