package week19.boj_2412;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jul {
	static int n,t;
	static ArrayList graph[];
	static ArrayList visited[];
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		graph = new ArrayList[t+1];
		visited = new ArrayList[t+1];
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
			visited[i] = new ArrayList<>();
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[b].add(a);
		}
//		for (ArrayList arrayList : graph) {
//			System.out.println(arrayList);
//		}
		int result = -1;
		PriorityQueue<int[]> pqueue = new PriorityQueue<>((o1,o2)->o1[0]!=o2[0]?o2[0]-o1[0]:o2[1]-o1[1]);
		pqueue.add(new int[] {0,0,0});
		visited[0].add(0);
		while (!pqueue.isEmpty()) {
//		for (int q = 0; q < 4; q++) {
			
			int[] temp = pqueue.poll();
//			System.out.println("temp : "+Arrays.toString(temp));
			if(temp[1] == t) {
				result = temp[2];
				break;
			}
			for (int i = temp[0]-2; i < temp[0]+3; i++) {
				for (int j = temp[1]-2; j < temp[1]+3; j++) {
					if(i<0 || j<0 || j>t) continue;
					if(visited[j].contains(i))continue;
//					System.out.println("i : "+i+" j : "+j);
					if(graph[j].contains(i)) {
						pqueue.add(new int[] {i,j,temp[2]+1});
						visited[j].add(i);
					}
				}
			}
//			for (int[] is : pqueue) {
//				System.out.print(Arrays.toString(is)+" ");
//			}
		}
//		}
		if(result == -1) System.out.println("-1");
		else System.out.println(result);
	
	}

}
