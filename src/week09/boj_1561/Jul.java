package week09.boj_1561;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jul {
	static int m;
	static long n,left,right,mid,result,count;
	static int[] input;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		input = new int[m];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < m; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		if(n<=m) System.out.println(n);
		else{
			left = 0;
			right = 30*n+1;
			while (left < right) {
				mid = (left+right)/2;
//				System.out.println(left+" "+right+" "+"mid : "+mid);
				if(f(mid)) {
					right = mid ;
//					System.out.println("정답일 가능성있음");
				}else {
//					System.out.println("정답일 가능성없음");
					left = mid+1;
				}
//				System.out.println(count);
			}
			result = left;
			int index = m;
			for (int i = 0; i < input.length; i++) {
				index += (result-1)/input[i];
			}
//			System.out.println(left-1+"까지 "+index+" 번으로 옴");
//			System.out.println(result);
			int answer = 0;
			for (int i = 0; i < input.length; i++) {
				if(result%input[i] == 0) {
					index++;
					answer = i;
				}
				if(index == n)break;
			}

			System.out.println(answer+1);
		}
	}
	private static boolean f(long val) {
		count = m;
		for (int i : input) {
			
			count += val/i;
		}
		return count>=n;
	}

}
