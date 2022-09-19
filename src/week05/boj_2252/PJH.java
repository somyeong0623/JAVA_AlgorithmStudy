package week05.boj_2252;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class PJH{
	
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>> table= new ArrayList<>();
		int[] pre = new int[N];
		
		for(int i = 0 ; i < N ;i++) {
			table.add(new ArrayList<>());
		}

		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1; 
			table.get(a).add(b);
			pre[b]++;
		}
		
		Queue<Integer> q = new LinkedList<>();
		
		for(int i = 0 ; i < N ;i++) {
			if(pre[i]==0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int tmp = q.poll();
			sb.append(tmp+1 +" ");
			
			for(int e:table.get(tmp)) {
				pre[e]--;
				if(pre[e]==0) {
					q.add(e);
				}
			}
		}
		
		
		
		System.out.println(sb);
		
	}

	
}

