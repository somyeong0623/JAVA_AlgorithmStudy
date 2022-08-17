package week02.boj_1758;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
public class PJH {
	static Integer[] input;

	static int N;
	static long answer;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(bf.readLine());
		input = new Integer[N];
		answer = 0;
		for(int i = 0 ; i < N ; i++) {
			input[i] = Integer.parseInt(bf.readLine());
		}
		Arrays.sort(input,Collections.reverseOrder());
		for(int i = 0 ; i < N ; i++) {
			if(input[i]-i>0)
			answer += (input[i]-i);
		}
		System.out.println(answer);
		
	}

}