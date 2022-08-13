package week02.boj_2078;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jinsu {
	static int a, b;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		int L = 0;
		int R = 0;
		
		while (!(a==1 && b== 1)) {
			if (a < b) {
				b -= a;
				R++;
			}
			else if (a > b){
				a -=b;
				L++;
			}
		}
		
		sb.append(L).append(" ").append(R);
		System.out.println(sb);
	}
	
}

