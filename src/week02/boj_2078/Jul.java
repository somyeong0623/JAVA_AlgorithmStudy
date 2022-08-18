package week02.boj_2078;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jul {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int left = 0,right = 0;
		while(!(a == 1 && b == 1)) {
			if (a > b) {
				left++;
				a = a-b;
				
			}else if (a < b) {
				right++;
				b = b-a;
			}
		}
		sb.append(left).append(" ").append(right);
		System.out.println(sb);

	}
}
