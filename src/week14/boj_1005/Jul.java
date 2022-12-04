package week14.boj_1005;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jul{
	static int T,N,K,target;
	static int[] time,degree;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph;
	static ArrayList<Integer> result ;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		result = new ArrayList<>();
		T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N =Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			time = new int[N+1];
			degree = new int[N+1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			graph = new ArrayList<>();
			for (int i = 0; i < N+1; i++) {
				graph.add(new ArrayList<>());
			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph.get(a).add(b);
				degree[b]++;
			}
			st = new StringTokenizer(br.readLine());
			target = Integer.parseInt(st.nextToken());
			PriorityQueue<int[]> pqueue = new PriorityQueue<>((o1,o2)->o1[0]-o2[0]);
			for (int i = 1; i < degree.length; i++) {
				if(degree[i] == 0) {
					pqueue.add(new int[] {time[i],i});
				}
			}
			while (!pqueue.isEmpty()) {
				int[] temp = pqueue.poll();
				int delay = temp[0];
				int node = temp[1];
				if(node == target) {
					result.add(delay);
					break;
				}
				for (int i : graph.get(node)) {
					degree[i]--;
					if(degree[i] == 0) {
						pqueue.add(new int[] {delay+time[i],i});
					}
				}
			}
		}
		
		for (Integer integer : result) {
			System.out.println(integer);
		}
	}
	
}
