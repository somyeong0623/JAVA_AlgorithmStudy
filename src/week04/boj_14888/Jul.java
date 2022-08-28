package week04.boj_14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 연산자 끼워넣기
public class Jul {
	static int n,big = Integer.MIN_VALUE,small = Integer.MAX_VALUE;
	static int[] number,cal,calList;
	static int[] permulist;
	static boolean[] visited;
//	static HashMap<Integer, Integer> cal = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		number = new int[n];
		permulist= new int[n-1];
		cal = new int[4];
		calList = new int[n-1];
		visited = new boolean[n-1];
		
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < n; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < 4; i++) {
			cal[i] = Integer.parseInt(st.nextToken());
		}
		int index = 0;
		for (int i = 0; i < cal.length; i++) {
			for (int j = 0; j < cal[i]; j++) {
				calList[index++] = i;
			}
		}
		permutate(0);
		System.out.println(big);
		System.out.println(small);
	}
	private static void permutate(int count) {
		if(count == n-1) {
			System.out.println(Arrays.toString(permulist));
			go(permulist);
			return;
		}
		for (int i = 0; i < n-1; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			permulist[count] = calList[i];
			permutate(count+1);
			visited[i] = false;
		}
	}
	private static void go(int[] permulist2) {
		int value = number[0];
		for (int i = 0; i < permulist2.length; i++) {
			if(permulist2[i] == 0) value+= number[i+1];
			if(permulist2[i] == 1) value-= number[i+1];
			if(permulist2[i] == 2) value*= number[i+1];
			if(permulist2[i] == 3) {
				if(value < 0) {
					value = Math.abs(value)/number[i+1]; 
				}else {
					value = value/number[i+1];
				}
			}
		}
		big = Math.max(big, value);
		small = Math.min(small, value);
		
	}

}
