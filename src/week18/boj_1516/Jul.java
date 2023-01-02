package week18.boj_1516;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jul {
	static int n;
	static int[][] graph;
	static int[] degree,time;
	static int[] result;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		graph = new int[n+1][n+1];
		degree= new int[n+1];
		time = new int[n+1];
		result = new int[n+1];
		visited = new boolean[n+1];
		for (int i = 1; i < n+1; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			time[i] = cost;
			while (true) {
				int j = Integer.parseInt(st.nextToken());
				if(j == -1) break;
				graph[j][i] = 1;
				degree[i]++;
			}
		}
//		for (int[] is : graph) {
//			System.out.println(Arrays.toString(is));
//		}
//		System.out.println(Arrays.toString(time));
//		System.out.println(Arrays.toString(degree));
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i < n+1; i++) {
			if(degree[i] == 0) {
				queue.add(i);
				visited[i] = true;
				result[i] = time[i];
			}
		}
		
		while (!queue.isEmpty()) {
			int temp = queue.poll();
//			System.out.println(Arrays.toString(degree));
			for (int i = 1; i < n+1; i++) {
				if(graph[temp][i] == 1 && !visited[i]) {
					degree[i]--;
					result[i] = Math.max(result[i], result[temp]+time[i]);
				}
			}
			for (int i = 1; i < n+1; i++) {
				if(degree[i] == 0 && !visited[i]) {
					queue.add(i);
					visited[i] = true;
//					result[i] = result[temp]+time[i];
				}
			}
		}
		for (int i = 1; i < n+1; i++) {
			System.out.println(result[i]);
		}
		
	}

}
