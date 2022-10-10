package week09.boj_11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jinsu {

	static class Node implements Comparable<Node> {
		int index, weight;

		public Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
	}
	
	static int n, bus, cost, city=1;
	static ArrayList<Node>[] list;
	static int[] distance;
	static int[] parent;
	static boolean[] visited;
	static ArrayList<Integer> route;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		bus = Integer.parseInt(br.readLine());
		
		
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		distance = new int[n+1];
		visited = new boolean[n+1];
		parent = new int[n+1];
		route = new ArrayList<>();
		
		for(int i=0; i<bus; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[s].add(new Node(e, w));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		dijkstra(start);
		System.out.println(Arrays.toString(distance));
		System.out.println(distance[end]);
		
		int current = end;
		while(current != 0) {
			route.add(current);
			current = parent[current];
		}
		System.out.println(route.size());
		
		for(int i=route.size()-1; i>=0; i--) {
			System.out.print(route.get(i)+" ");
		}
		
	}

	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.add(new Node(start, 0));
		distance[start] = 0;
		parent[start] = 0;
		
		//for(int i=1; i<=n; i++) {
		//	if(!visited[i] && map[start][i] != 0) {
				//distance[i] = map[start][i];
			//}
		//}
		
		while(!pq.isEmpty()) {
			
			Node cur = pq.poll();
			int idx = cur.index;
			
			if(visited[idx]) continue;
			visited[idx] = true;
			
			for(int i=0; i<list[idx].size(); i++) {
				Node next = list[idx].get(i);
				if(distance[next.index] > next.weight + distance[idx]) {
					distance[next.index] = next.weight + distance[idx];
					pq.add(new Node(next.index, distance[next.index]));
					parent[next.index] = idx;
				}
			
			}
		}
		
	}

}
