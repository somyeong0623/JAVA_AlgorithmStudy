package week05.boj_2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jinsu {
	static class Node{
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
		
	}
	
	static int N, M;
	static Node[] adjList;
	static int[] indegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new Node[N+1];	//각 정점별 리스
		indegree = new int[N+1];	//정점별 진입 차수
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			adjList[A] = new Node(B, adjList[A]);
			
			indegree[B]++;
		}
		
		ArrayList<Integer> list = topologySort();
		
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
		
	}

	private static ArrayList<Integer> topologySort() {
		ArrayList<Integer> list = new ArrayList<>();
		Queue<Integer> q = new ArrayDeque<>();
		
		// 진입차수 0인 정점 큐에 대입
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0) q.offer(i);
		}
		
		//BFS
		while(!q.isEmpty()) {
			int cur =q.poll();
			list.add(cur);
			
			for(Node temp = adjList[cur]; temp != null ; temp = temp.next) {
				if(--indegree[temp.vertex] == 0) q.offer(temp.vertex);
			}
		}
		return list;
		
	}

}
