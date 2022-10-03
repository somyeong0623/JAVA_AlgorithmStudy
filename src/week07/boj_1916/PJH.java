package week07.boj_1916;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PJH {
	static ArrayList<Edge>[] node_list;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(bf.readLine()); // 도시
		int M = Integer.parseInt(bf.readLine()); // 노선

		int[] minEdge = new int[N];
		node_list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			node_list[i] = new ArrayList<>();
			minEdge[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			node_list[s].add(new Edge(d, w));
			
		}

		st = new StringTokenizer(bf.readLine());
		int target = Integer.parseInt(st.nextToken())-1;
		int end = Integer.parseInt(st.nextToken())-1;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> {
			return e1.weight - e2.weight;
		});
		pq.add(new Edge(target, 0));
		minEdge[target] = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
//			System.out.println(edge);
//			System.out.println(node_list[edge.dest]);
			if (minEdge[edge.dest] < edge.weight) {
				continue;// 해당경로는 최단경로 이후 진행할 필요없다. 우선순위 큐라서
			}

			// 목적지까지 가는 최단 경로가 수정되었다.
			// 목적지를 타겟으로 잡고 타겟에서 뻗어나가는 간선을 확인한다.
			// 간선을 확인했을 때 다시 최단 경로를 만들 수 있는 간선이라면 큐에 넣는다.
			// dest가 같으며 가중치가 다른 요소가 큐에 동시에 존재할 수 있다.
			// 시작 지점이 다르고 dest가 같은 경우 큐에 같은 dest가 있을 수 있다.
			// 가중치가 작은 것이 우선 선택되도록 우선순위 큐 사용
			// 가중치 작은게 우선 적용 된 후 가중치가 큰 요소는 위에서 필터링되어버린다.
			for (int i = 0; i < node_list[edge.dest].size(); i++) {
				int target_dest = node_list[edge.dest].get(i).dest;
				int target_weight = node_list[edge.dest].get(i).weight;

				if (minEdge[target_dest] > edge.weight + target_weight) {
					minEdge[target_dest] = edge.weight + target_weight;
					pq.add(new Edge(target_dest, minEdge[target_dest]));
				}

			}
			//System.out.println(Arrays.toString(minEdge));
		}
//		for (int a : minEdge) {
//			if (a == Integer.MAX_VALUE) {
//				System.out.println("INF");
//			} else
//				System.out.println(a);
//		}
		
		System.out.println(minEdge[end]);

	}

	static class Edge {
		int dest;
		int weight;

		public Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [dest=" + dest + ", weight=" + weight + "]";
		}

	}
}
