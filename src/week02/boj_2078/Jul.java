package week02.boj_2078;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jul {
	static int left, right, a, b;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		while(true) {
			if (a == 1 && b == 1) {
				break ;
			}
			if (a > b) {
				left++;
				a = a-b;
				
			}else if (a < b) {
				right++;
				b = b-a;
			}
		}
		System.out.println(left+" "+right);

	}
}
