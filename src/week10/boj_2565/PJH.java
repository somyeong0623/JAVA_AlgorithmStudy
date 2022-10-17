package week10.boj_2565;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Edge> list;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		list = new ArrayList<>();
		
		// st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(bf.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.add(new Edge(a,b));
			
		}
		Collections.sort(list);
		int[] new_list = new int[N];
		for(int i = 0 ; i<N;i++) {
			new_list[i] = list.get(i).b;
		}
		
		int[] dp = new int[N];
		int answer = 0;
		for(int i = 0 ; i < list.size(); i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(list.get(i).b > list.get(j).b && dp[i] < dp[j]+1){
                    dp[i] = dp[j] + 1;
                }
            }
            answer = answer > dp[i] ? answer : dp[i];
        }
		System.out.println(N-answer);
		//System.out.println(list);
		
		
	}

	static class Edge implements Comparable<Edge> {
		int a;
		int b;

		public Edge(int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(Edge o) {
			return this.a - o.a;
		}

		@Override
		public String toString() {
			return "Edge [a=" + a + ", b=" + b + "]";
		}

	}

}