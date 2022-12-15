package week14.boj_1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 1005.ACM craft 
public class Somyeong {
	
	static int TC;
	static int N,K;
	static int time[];
	static int dp[];
	static int indegree[];
	static ArrayList<Integer> edges[];
	static int target;
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC= Integer.parseInt(br.readLine());
		
		for(int t=1; t<=TC; t++) {
			st = new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			 
			time=new int[N+1]; // 건설하는데 걸리는 시간 
			dp = new int[N+1]; 
			edges = new ArrayList[N+1];
			indegree = new int[N+1]; //진입차수 저장 
			st = new StringTokenizer(br.readLine());
			
//			edges[0]=new ArrayList<Integer>();
			for(int i=1; i<=N; i++) {
				edges[i]= new ArrayList<Integer>();
				time[i]=Integer.parseInt(st.nextToken());
			}
		
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				edges[from].add(to);
				indegree[to]++;
			}
			target=Integer.parseInt(br.readLine());
			
			Queue<Integer> queue = new ArrayDeque<Integer>();
			
			for(int i=1; i<=N; i++) {
				if(indegree[i]==0) {
					queue.add(i);
					dp[i]=time[i];
				}
			}
			
			while(!queue.isEmpty()) {
				int cur = queue.poll();
//				System.out.println("cur: "+cur);
				
				for(int i=0; i<edges[cur].size(); i++) {
					int next=edges[cur].get(i);
					dp[next]=Math.max(dp[next], dp[cur]+time[next]);
					indegree[next]--;
					
					if(indegree[next]==0) //진입차수가 0인 것만 queue에 넣기 
						queue.add(next);
				}
			}
			
			System.out.println(dp[target]);
		}
		
	}

}
