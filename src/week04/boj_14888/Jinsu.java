package week04.boj_14888;

import java.util.Scanner;

public class Jinsu {

	static int N, max, min;
	static int[] num;
	static int[] oper;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		num = new int[N];
		oper = new int[4];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			num[i] = sc.nextInt();
		}
		
		for(int i=0; i<4; i++) {
			oper[i] = sc.nextInt();
		}
		
		dfs(1, num[0]);
		
		System.out.println(max);
		System.out.println(min);
		
	}
	
	private static void dfs(int index, int sum) {
		if (index == N) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		
		for (int i=0; i<4; i++) {
			
			if (oper[i] > 0) {
				
				oper[i]--;
				
				switch (i) {
					case 0: dfs(index+1, sum+num[index]); break;
					case 1: dfs(index+1, sum-num[index]); break;
					case 2: dfs(index+1, sum*num[index]); break;
					case 3: dfs(index+1, sum/num[index]); break;
				}
				
				oper[i]++;
			}
			
		}
	}

}
