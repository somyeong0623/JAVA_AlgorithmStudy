package week02.boj_2075;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jul {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int n = Integer.parseInt(st.nextToken());
		long result = 0;
		PriorityQueue<Long> pqueue = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < n; j++) {
				pqueue.add(-Long.parseLong(st.nextToken()));
			}
		}
		for (int i = 0; i < n; i++) {
			result = pqueue.poll();
		}
		System.out.println(-result);
	}

}
