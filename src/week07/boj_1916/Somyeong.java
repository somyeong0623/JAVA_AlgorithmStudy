package week07.boj_1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//1916. 최소비용 구하기 
public class Somyeong  {
	static class Info implements Comparable<Info>{
		int to;
		int cost;
		public Info(int to, int cost) {
			this.to=to;
			this.cost=cost;
		}
		@Override
		public int compareTo(Info o) {
			return this.cost-o.cost;
		}
		
		
	}
	
	static int N, M;
	static ArrayList<Info> edges[];
	static int start, end;
	static int dist[];
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N=Integer.parseInt(br.readLine());
		M=Integer.parseInt(br.readLine());
		
		edges=new ArrayList[N+1];
		dist=new int[N+1];
		visited = new boolean[N+1];
		
		
		for(int i=0; i<=N; i++) {
			edges[i]=new ArrayList<Info>();
			dist[i]=Integer.MAX_VALUE;
		}
		
		for(int i=0; i<M; i++) {
			int from, to, cost;
			st=new StringTokenizer(br.readLine());
			from=Integer.parseInt(st.nextToken());
			to=Integer.parseInt(st.nextToken());
			cost=Integer.parseInt(st.nextToken());
			
			edges[from].add(new Info(to, cost));
		}
		
		st=new StringTokenizer(br.readLine());
		start=Integer.parseInt(st.nextToken());
		end=Integer.parseInt(st.nextToken());
		
		dist[start]=0;
		func(start);
		
//		for(int i=1; i<=N; i++) {
//			System.out.print(dist[i]+" ");
//		}
//		System.out.println();
		System.out.println(dist[end]);
		
	}
	
	public static void func(int v) {
		PriorityQueue<Info> pq = new PriorityQueue<Info>();
		pq.add(new Info(v,0));
		dist[v]=0;
		
		while(!pq.isEmpty()) {
			Info cur = pq.poll();
			int cur_num=cur.to;
			
			if(visited[cur_num]) continue; 
			
			visited[cur_num]=true; // 여기좀 헷갈린다.. 방문체크를 for문안의 if문에서 하는게아니라 여기서 해야하는지.. 이유 
			
			for(Info info : edges[cur.to]) {
				int next_num=info.to;
				int next_cost=info.cost;
				
				if(visited[next_num]==false && dist[next_num]>dist[cur_num]+next_cost) {
					dist[next_num]=dist[cur_num]+next_cost;
					pq.add(new Info(next_num,dist[next_num]));
				}
			}
		}
	}


}
