package week18.boj_1167;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jul {
	static class Node{
		int node,cost;

		public Node(int node, int cost) {
			super();
			this.node = node;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node [node=" + node + ", cost=" + cost + "]";
		}
		
	}
	static int v;
	static ArrayList<ArrayList<Node>> graph;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		v = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i < v+1; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 1; i < v+1; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int b = Integer.parseInt(st.nextToken());
			while (true) {
				int a = Integer.parseInt(st.nextToken());
				if(a == -1)break;
				int amount = Integer.parseInt(st.nextToken());
				graph.get(b).add(new Node(a, amount));
			}
		}
//		for (ArrayList<Node> is : graph) {
//			System.out.println(is.toString());
//		}
		// node 1에서 시작해서 가장 끝 한 노드를 구한다.
		int start = 0;
		int result = 0;
		visited = new boolean[v+1];
		visited[1] = true;
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {1,0});
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			if(result < temp[1]) {
				start = temp[0];
				result = temp[1];
			}
			for (int i = 0; i < graph.get(temp[0]).size(); i++) {
				Node node = graph.get(temp[0]).get(i);
				if(visited[node.node])continue;
				visited[node.node] = true;
				queue.add(new int[] {node.node,temp[1]+node.cost});
			}
		}
//		System.out.println(start +" "+result);
		
		visited = new boolean[v+1];
		visited[start] = true;
		queue = new ArrayDeque<>();
		queue.add(new int[] {start,0});
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			result = Math.max(result, temp[1]);
			for (int i = 0; i < graph.get(temp[0]).size(); i++) {
				Node node = graph.get(temp[0]).get(i);
				if(visited[node.node]) continue;
				visited[node.node] = true;
				queue.add(new int[] {node.node,temp[1]+node.cost});
			}
		}
		System.out.println(result);
	}

}
