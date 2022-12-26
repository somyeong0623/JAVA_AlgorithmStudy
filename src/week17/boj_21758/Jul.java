package week17.boj_21758;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jul {
	static int n;
	static int[] input;
	static long[] accumulateRight,accumulateLeft;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		input = new int[n];
		accumulateRight = new long[n];
		accumulateLeft = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			if(i==0) accumulateRight[i] = input[i];
			else accumulateRight[i] +=accumulateRight[i-1]+input[i];
		}
		for (int i = n-1; i > -1; i--) {
			if(i == n-1) accumulateLeft[i] = input[i];
			else accumulateLeft[i] += accumulateLeft[i+1]+input[i];
		}
//		System.out.println(n);
//		System.out.println(Arrays.toString(accumulateLeft));
//		System.out.println(Arrays.toString(accumulateRight));
		long answer = 0;
		for (int i = 1; i < n-1; i++) {
			answer = Math.max(answer, accumulateRight[n-1]-accumulateRight[0]+accumulateRight[n-1]-accumulateRight[i]-input[i]);
			answer = Math.max(answer, accumulateRight[i] - input[0]-input[n-1]+accumulateLeft[i]);
		}
		for (int i = n-2; i > 0; i--) {
			answer = Math.max(answer, accumulateLeft[0]-accumulateLeft[n-1]-input[i]+accumulateLeft[0]-accumulateLeft[i]);
		}
		System.out.println(answer);
	}

}
