package week02.boj_2075;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PJH {
	static int[] idx_list;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(bf.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> {
			return e2 - e1;
		});

		idx_list = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}
		int cnt = 0;
		int answer = 0;
		while (cnt != N) {
			answer = pq.poll();
			cnt++;
		}
		System.out.println(answer);

	}
}