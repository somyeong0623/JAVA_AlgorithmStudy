package week05.boj_2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

//2252. 줄 세우기 
public class Somyeong {
	static int N,M;
	static int indegree[];
	static ArrayList<Integer> answer;
	static boolean visited[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		StringBuilder sb= new StringBuilder();
		
		N=Integer.parseInt(st.nextToken()); //
		M=Integer.parseInt(st.nextToken());
		indegree = new int[N+1]; // 해당정점에서의 indegree 이 정점으로 들어오는 간선이 있는 정점 갯수)
		answer = new ArrayList<Integer>(); 
		visited = new boolean[N+1];
		
		
		ArrayList<Integer> edges[] = new ArrayList[N+1]; //간선 리스트배열 
		for(int i=0; i<=N; i++) { 
			edges[i]=new ArrayList<Integer>();  //간선 리스트배열 초기화 
		}
		
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			indegree[to]++; //정점 to의 indegree수 증가 
			edges[from].add(to);
			
		}
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for(int i=1; i<=N;i++) {
			if(indegree[i]==0) { // 해당정점으로 오는 간선이 없으면 queue에 넣기 
				queue.add(i);
				visited[i]=true;
			}
		}
		while(!queue.isEmpty()) {
			int cur= queue.poll();
			answer.add(cur); //방문한 순서대로 queue에서 poll되고, 그 값을 정답 리스트에 넣기 
			for(int i=0; i<edges[cur].size();i++) {
				int next= edges[cur].get(i);
				if(visited[next]) //visited 체크 안해도 정답나옴 (근데 왜 visited 체크하면 시간단축 안되지?? )
					continue;
				indegree[next]--; // 이미 방문한 정점의 간선을 끊어줌 (정점 next의 indegree 1 감소)
				if(indegree[next]==0)  //next의 indegree가 0되면 그시점에 방문할수 있는 정점이 되었으므로 queue에 넣기 
					queue.add(next);
			}
		}
		
		for(int i : answer) {
//			System.out.print(i+" ");
			sb.append(i+" ");
		}
		System.out.println(sb); // sb썼더니900ms-> 500ms로 줄어듦 
		
		

	}
}
