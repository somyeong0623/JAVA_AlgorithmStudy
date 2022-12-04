package week14.boj_17140;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jul{
	static int r,c,k,count,row,col;
	static int[][] graph;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		graph = new int[101][101];
		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 3; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		row = 3;
		col = 3;
		while (count<=101) {
			if(graph[r][c]==k) {
				System.out.println(count);
				break;
			}else if(count>100) {
				System.out.println(-1);
				break;
			}
			if(row>=col) {
				for (int i = 1; i < 101; i++) {
					int[] temp = new int[101];
					HashMap<Integer,Integer > count = new HashMap<>();
					for (int j = 1; j < 101; j++) {
						if(graph[i][j] == 0) continue;
						// 카운팅
						count.put(graph[i][j], count.getOrDefault(graph[i][j], 0)+1);
					}
					// temp에 대입하기
					PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o1[1]==o2[1]?o1[0]-o2[0]:o1[1]-o2[1]);
					col = Math.max(col, count.keySet().size()*2);
					for (Entry<Integer, Integer> js : count.entrySet()) {
						pq.add(new int[] {js.getKey(),js.getValue()});
					}
					int index = 1;
					while (!pq.isEmpty()) {
						int[] t = pq.poll();
						temp[index++] = t[0];
						temp[index++] = t[1];
					}
					Arrays.fill(graph[i], 0);
					for (int j = 0; j < temp.length; j++) {
						graph[i][j] = temp[j];
					}
				}
			}else {
				for (int i = 1; i < 101; i++) {
					int[] temp = new int[101];
					HashMap<Integer, Integer> count = new HashMap<>();
					for (int j = 1; j < 101; j++) {
						if(graph[j][i] == 0) continue;
						count.put(graph[j][i],count.getOrDefault(graph[j][i], 0)+1);
					}
					PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->o1[1]==o2[1]?o1[0]-o2[0]:o1[1]-o2[1]);
					row = Math.max(row, count.keySet().size()*2);
					for (Entry<Integer, Integer> js : count.entrySet()) {
						pq.add(new int[] {js.getKey(),js.getValue()});
					}
					int index = 1;
					while (!pq.isEmpty()) {
						int[] t = pq.poll();
						temp[index++] = t[0];
						if(index>100)break;
						temp[index++] = t[1];
						if(index>100)break;
					}
					for (int j = 0; j < 101; j++) {
						graph[j][i] = 0;
					}
					for (int j = 0; j < temp.length; j++) {
						graph[j][i] = temp[j];
					}
					
				}
			}
			count++;
		}
//		System.out.println("row => "+row);
//		System.out.println("col => "+col);
	}
}
