package week12.boj_2470;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class PJH {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(bf.readLine());
		int[] table = new int[N];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < N; i++) {
			table[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(table);
		int start = 0;
		int end = N - 1;
		int answer_start = start;
		int answer_end = end;

		while (start < end) {
			int state = table[start] + table[end];
			int answer = table[answer_start] + table[answer_end];
			if(Math.abs(state)<Math.abs(answer)) {
				answer_start = start;
				answer_end = end;
			}
			if(state<0) {
				start++;
			}else if(state>0) {
				end--;
			}else {
				break;
			}
		}
		System.out.println(table[answer_start]+" "+table[answer_end]);

	}

}