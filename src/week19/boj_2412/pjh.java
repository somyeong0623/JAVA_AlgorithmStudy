import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList[] node_list;
	static Node[] real_node;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(bf.readLine());
		int n = Integer.parseInt(st.nextToken()); // n개의 손잡이
		int T = Integer.parseInt(st.nextToken()); // 목표 Y

		node_list = new ArrayList[T+1];
		real_node = new Node[n];

		for (int i = 0; i < T+1; i++) {
			node_list[i] = new ArrayList<Node>();
		}

		PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> {
			return e1.move - e2.move;
		});
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(bf.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			real_node[i] = new Node(x, y, Integer.MAX_VALUE);
			node_list[y].add(real_node[i]);
		}

		
		pq.add(new Node(0, 0, 0));
		
		int answer = -1;
		
		while (!pq.isEmpty()) {			
			Node target = pq.poll();
			int limit_x_min = Math.max(target.y - 2, 0);
			int limit_x_max = Math.min(target.y + 2, T);
			for (int i = limit_x_min; i <= limit_x_max; i++) {
				for(int j = 0;j<node_list[i].size();j++) {
					Node search_node = (Node) node_list[i].get(j);
					//System.out.println(target + "," + search_node);
					if(search_node.move>target.move && Math.abs(search_node.x-target.x)<=2) {
						search_node.move = target.move+1;
						pq.add(search_node);
						if(T==search_node.y) {
							answer = search_node.move;
							break;
						}
						node_list[i].remove(j);
						j--;
					}
				}
			}
			//System.out.println();
		}
		System.out.println(answer);
		
	}

	static class Node {
		int x;
		int y;
		int move;

		public Node(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", move=" + move + "]";
		}

	}

}