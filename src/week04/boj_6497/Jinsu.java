package week04.boj_6497;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Jinsu {
	
	static class Node implements Comparable<Node>{
		int from, to, weight;

		public Node(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
		
	}
	
	static int m, n;
	static int[] parents;
	static ArrayList<Node> edgeList;
	
	static void make() {
		parents = new int[m];
		
		for(int i=0; i < m; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			if (m==0 && n==0) break;
			
			edgeList = new ArrayList<>();
			
			int result = 0;
			
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edgeList.add(new Node(from, to, weight));
				result += weight;
				
			}
			
			make();
			
			Collections.sort(edgeList);
			
			int count = 0;
			int min = 0;
			for(Node e : edgeList) {
				if (union(e.from, e.to)) {
					min += e.weight;
					if(++count == n-1) break;
				}
			}
			
			System.out.println(result-min);
		}
		
	}

}
