package week26.boj_15591;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jul {
	static int n,q;
	static ArrayList<int[]>[] graph;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		graph = new ArrayList[n+1];
		for (int i = 0; i < n+1; i++) {
			graph[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[a].add(new int[] {b,c});
			graph[b].add(new int[] {a,c});
		}
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dfs(a,b);
		}
//		for (int i = 0; i < n+1; i++) {
//			System.out.println(Arrays.toString(graph[i]));
//		}
	}
	private static int dfs(int i, int j) {
		int result = 0;
		boolean[] visited = new boolean[n+1];
		Queue<Integer> queue = new ArrayDeque<>();
		visited[j] = true;
		queue.add(j);
		while (!queue.isEmpty()) {
			int temp = queue.poll();
//			if(temp == j)return temp[1];
			for (int k = 0; k < graph[temp].size(); k++) {
				if(visited[graph[temp].get(k)[0]])continue;
				if(graph[temp].get(k)[1]>=i) {
//					result = Math.min(result, graph[temp[0]][k]);
					visited[graph[temp].get(k)[0]] = true;
					queue.add(graph[temp].get(k)[0]);
					result++;
				}
			}
		}
		System.out.println(result);
		return 0;
	}

}
