package week02.boj_2078;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PJH {//2078
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int left = 0, right = 0;

		st = new StringTokenizer(bf.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		while (a!=1 && b!=1) {// 루트가 아니면 무조건 다르다.
			int tmp_quotient;
			int tmp_remainder;
			if (a > b) {
				tmp_quotient = a / b;
				tmp_remainder = a % b;
				left += tmp_quotient;
				a = tmp_remainder;
			} else {
				tmp_quotient = b / a;
				tmp_remainder = b % a;
				right += tmp_quotient;
				b = tmp_remainder;
			}
		}
		
		a--;
		b--;
		left+=a;
		right+=b;
		
		
		System.out.println(left + " " + right);

	}
}
