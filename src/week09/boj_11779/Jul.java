package week09.boj_11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jul {
	static class Qobj{
		int node,cost;
		ArrayList<Integer> route;
		public Qobj(int node, int cost, ArrayList<Integer> route) {
			super();
			this.node = node;
			this.cost = cost;
			this.route = route;
		}
	}
	static int n,m,start,end;
	static int[] distance;
//	static ArrayList<Integer> route;
	static int[][] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		graph = new int[n+1][n+1];
		distance = new int[n+1];
		for (int i = 0; i < n+1; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE);
		}
		Arrays.fill(distance, Integer.MAX_VALUE);
		st = new StringTokenizer(br.readLine().trim());
		m = Integer.parseInt(st.nextToken());
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[x][y] = Math.min(cost, graph[x][y]);
		}
		st = new StringTokenizer(br.readLine().trim());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		distance[start] = 0;
//		route.add(start);
		dijstra(start);
	}
	private static void dijstra(int start2) {
		PriorityQueue<Qobj> pqueue = new PriorityQueue<>((o1,o2)->o1.cost!=o2.cost?o1.cost-o2.cost:o1.node-o2.node);
		pqueue.add(new Qobj(start2, 0, new ArrayList<>(Arrays.asList(start2))));
		while (!pqueue.isEmpty()) {
			Qobj temp = pqueue.poll();
			if(temp.node == end) {
				System.out.println(distance[end]);
				System.out.println(temp.route.size());
				for (int i = 0; i < temp.route.size(); i++) {
					System.out.print(temp.route.get(i)+" ");
				}
				return ;
			}
			for (int i = 1; i < graph[temp.node].length ;i++) {
				if(graph[temp.node][i] == Integer.MAX_VALUE) continue;
				if(distance[temp.node]<temp.cost)continue;
				int cost = graph[temp.node][i] + temp.cost;
				if(distance[i] > cost) {
					distance[i] = cost;
					ArrayList<Integer> t = new ArrayList<>();
					for (int j = 0; j < temp.route.size(); j++) {
						t.add(temp.route.get(j));
					}
					t.add(i);
					pqueue.add(new Qobj(i, cost, t));
				}
			}
					
		}
	}

}
