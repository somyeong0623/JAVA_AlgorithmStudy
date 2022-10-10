package week09.boj_11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

//11779. 최소비용 구하기 2
public class Somyeong {
	static class Edge{
		int to, cost;
		public Edge(int to,int cost) {
			this.to=to;
			this.cost=cost;
		}
	}
	
	static int n,m;
	static int start,end;
	static int answerCost, answerCnt;
	static int cnt[]; //현재 정점까지 거친 정점 갯수 저장 
	static int dist[];// 현재 정점까지의 최소 비용 저장 
	static int prev[];// 현재 정점 기준, 바로 직전 방문한 정점 번호 저장하는 배열 
	static boolean visit[];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		m=Integer.parseInt(br.readLine());
		dist=new int[n+1];
		prev=new int[n+1];
		cnt=new int[n+1];
		visit=new boolean[n+1];
		
		ArrayList<Edge>[] edges = new ArrayList[n+1];
		for(int i=0; i<=n; i++) {
			edges[i]=new ArrayList<Edge>();
		}
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i=0; i<m; i++) {
			st= new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			int cost=Integer.parseInt(st.nextToken());
			edges[from].add(new Edge(to,cost));
		}
		st=new StringTokenizer(br.readLine());
		start=Integer.parseInt(st.nextToken());
		end=Integer.parseInt(st.nextToken());
		
		PriorityQueue<Edge> pq= new PriorityQueue<Edge>((o1,o2)->o1.cost-o2.cost); // 비용 오른차순 
//		Queue<Edge> pq = new ArrayDeque<Edge>();// 그냥 queue쓰면 틀렸습니다인데 왜지 ?????
		pq.add(new Edge(start,0));
		dist[start]=0;
		cnt[start]=1;
		visit[start]=true;
		
		// 다익스트라 
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(cur.to==end) break;
			
			for(int i=0; i<edges[cur.to].size(); i++) {
				Edge next = edges[cur.to].get(i);
				
				if(dist[next.to]>dist[cur.to]+next.cost) {
					dist[next.to]=dist[cur.to]+next.cost;
					prev[next.to]=cur.to;
					cnt[next.to]=cnt[cur.to]+1;
					pq.offer(new Edge(next.to, dist[next.to]));
				}

			}
			
			
		}
		System.out.println(dist[end]); // 최소비용 
		System.out.println(cnt[end]);// 경로에 포함된 도시 갯수 
		
//		System.out.println("====dist====");
//		for(int i=1; i<=n; i++) {
//			System.out.print(dist[i]+" ");
//		}
//		System.out.println();
//		System.out.println("===prev===");
//		for(int i=1; i<=n; i++) {
//			System.out.print(prev[i]+" ");
//		}
//		System.out.println();

		//도착지점부터 시작지점까지 경로를 리스트에 담아놓고 반대로 출력하자 .
		ArrayList<Integer> answerList=new ArrayList<Integer>();
		for(int i=end; i!=start; i=prev[i] ){
			answerList.add(i);
		}
		answerList.add(start);
		for(int i=answerList.size()-1; i>=0; i--) {
			System.out.print(answerList.get(i)+" ");
		}
		//최소비용을 갖는 경로가 여러개만 그중 하나를 출력하면된다. (스페셜 저지)
		
	}
	
}
