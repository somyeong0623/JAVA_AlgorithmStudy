package week09.boj_11779;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PJH {
	static ArrayList<Bus>[] table;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		//입력값 및 초기화
		int N = Integer.parseInt(bf.readLine());// 도시수
		int M = Integer.parseInt(bf.readLine());// 버스수
		int[] MinEdge = new int[N + 1];
		Bus[] MinBus = new Bus[N + 1];
		
		table = new ArrayList[N + 1];
		Arrays.fill(MinEdge, Integer.MAX_VALUE);
		for (int i = 0; i <= N; i++) {
			table[i] = new ArrayList<>();
		}
		//버스정보 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			ADD_Bus(s, d, cost);
		}
		
		//기타 정보 입력
		PriorityQueue<Bus> pq = new PriorityQueue<>((e1, e2) -> { return e1.cost - e2.cost; });
		st = new StringTokenizer(bf.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		//큐 기저상태 기입
		Bus start_bus = new Bus(start, 0);
		start_bus.log.add(start);
		pq.add(start_bus);
		MinEdge[start] = 0;
		
		//다익스트라
		while (!pq.isEmpty()) {
			Bus bus = pq.poll();
			
			
			
			if (MinEdge[bus.dist] < bus.cost)
				continue;
			for (Bus next_bus : table[bus.dist]) {
				if (MinEdge[next_bus.dist] > next_bus.cost + bus.cost) {
					MinEdge[next_bus.dist] = next_bus.cost + bus.cost;
					Bus tmp_bus = new Bus(next_bus.dist, MinEdge[next_bus.dist]);
					for(int a:bus.log) {
						tmp_bus.log.add(a);
					}
					tmp_bus.log.add(next_bus.dist);
					MinBus[next_bus.dist] = tmp_bus;
					pq.add(tmp_bus);
				}
			}
		}
		
		sb.append(MinEdge[end]+"\n");		
		sb.append(MinBus[end].log.size()+"\n");
		for(int tmp:MinBus[end].log) {
			sb.append(tmp+" ");
		}
		System.out.println(sb);
	}

	static void ADD_Bus(int s, int d, int cost) {
		for (Bus bus : table[s]) {
			if (bus.dist == d) {
				if (bus.cost > cost) {
					bus.cost = cost;
				}
				return;
			}
		}
		table[s].add(new Bus(d, cost));
	}
	static class Bus {
		int dist;
		int cost;
		ArrayList<Integer> log;

		public Bus(int dist, int cost) {
			this.dist = dist;
			this.cost = cost;
			log = new ArrayList<>();
		}
	}
}
