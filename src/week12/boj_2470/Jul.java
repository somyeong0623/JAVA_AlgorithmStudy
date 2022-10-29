package week12.boj_2470;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jul {
	static int n,left,right,canX,canY;
	static int[] input;
	static long ph,candy;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		input = new int[n];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
//		System.out.println(Arrays.toString(input));
		left = 0;
		right = n-1;
		canX = left;
		canY = right;
		candy = 2000000001;
//		System.out.println(candy);
		while (left!=right) {
			long mid = input[left]+input[right];
//			System.out.println("left : "+left+" right : "+right+" mid : "+mid);
			if(Math.abs(mid)<=Math.abs(candy)) {
				candy = Math.abs(mid);
				canX = left;
				canY = right;
			}
			if(mid<0) {
				left++;
				candy = Math.min(candy, Math.abs(mid));
			}
			else if(mid>0) {
				right--;
				candy = Math.min(candy, Math.abs(mid));
			}
			else if(mid == 0) {
				canX = left;
				canY =right;
				break;
			}
		}
		System.out.println(input[canX]+" "+input[canY]);
	}

}
