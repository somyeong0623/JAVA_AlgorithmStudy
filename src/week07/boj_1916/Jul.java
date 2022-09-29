package week07.boj_1916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 최소비용 구하기
public class Jul {
	static class Node{
		int to,cost;

		public Node(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}
		
	}
	static int n,m,start,end;
	static int[] distance;
	static ArrayList<ArrayList<Node>> node;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim());
		distance = new int[n+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		m = Integer.parseInt(st.nextToken());
		node = new ArrayList<>();
		for (int i = 0; i < n+1; i++) {
			node.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			node.get(a).add(new Node(b, cost));
		}
		st = new StringTokenizer(br.readLine().trim());
		start= Integer.parseInt(st.nextToken());
		end= Integer.parseInt(st.nextToken());
		dij(start);
		System.out.println(distance[end]);
	}
	private static void dij(int start) {
		PriorityQueue<int[]> pqueue = new PriorityQueue<>((o1,o2)->o1[0]-o2[0]);
		pqueue.add(new int[] {0,start});
		distance[start] = 0;
		while (!pqueue.isEmpty()) {
			int[] temp = pqueue.poll();
			int dist = temp[0];
			int now = temp[1];
			if(distance[now] < dist)continue;
			for (int i = 0; i < node.get(now).size(); i++) {
				Node n = node.get(now).get(i);
				int cost = dist+n.cost;
				if(cost<distance[n.to]) {
					distance[n.to] = cost;
					pqueue.add(new int[] {cost,n.to});
				}
			}
		}
	}
}
