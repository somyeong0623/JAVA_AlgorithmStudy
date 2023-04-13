package week31.boj_20366;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Jul {
	static int n;
	static Long result= Long.MAX_VALUE;
	static ArrayList<Long> graph;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		graph = new ArrayList<>();
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			graph.add(Long.parseLong(st.nextToken()));
		}
		Collections.sort(graph);
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				long snow1 = graph.get(i)+ graph.get(j);
				int start = 0;
				int end = n-1;
				while (start < end) {
					if(start == i||start == j) {
						start++;
						continue;
					}
					if(end == i||end == j) {
						end--;
						continue;
					}
					long snow2 = graph.get(start)+graph.get(end);
					result = Math.min(result, Math.abs(snow2-snow1));
					if(snow1<snow2) {
						end--;
					}else if(snow1>snow2) {
						start++;
					}else {
						result = 0l;
						break;
					}
				}
			}
		}
		System.out.println(result);
	}

}
