package week05.boj_2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 줄 세우기
public class Jul {
	static class Node{
		int vertex;
		Node next;
		public Node(int vertex,Node next) {
			this.vertex = vertex;
			this.next = next;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Node[] node = new Node[n+1];
		int[] isDegree = new int[n+1];
		for(int i = 0;i<m;i++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			node[from] = new Node(to,node[from]);
			isDegree[to]++;
		}
		ArrayList<Integer> list = topologySort(isDegree,node);
		for(int i : list) {
			if(i == 0)continue;
			System.out.print(i+" ");
		}
	}
	static ArrayList<Integer> topologySort(int[] isDegree,Node[] node){
		ArrayList<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		for(int i =1; i < isDegree.length ; i++) {
			if(isDegree[i] == 0) queue.offer(i);
			//			System.out.print(i+" ");
		}
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			list.add(cur);
			for(Node temp = node[cur]; temp != null; temp = temp.next) {
				if(--isDegree[temp.vertex] == 0) queue.offer(temp.vertex);
			}
		}
		return list;
	}
}
