package week19.boj_1300;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jul {
	static int n,k;
	static int[] arr,add_arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		int left = 1;
		int right = k;
		while (left != right) {
			int mid = (left+right)/2;
			int count = 0;
			for (int i = 1; i < n+1; i++) {
				System.out.println(Math.min(mid/i, n));
				count += Math.min(mid/i, n);
			}
			System.out.println(count);
			if(count >= k) right = mid;
			else left = mid+1;
			System.out.println("left =>"+left+ " right=>"+right);
		}
		System.out.println(left);
	}

}
