package week08.boj_1261;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PJH {
	static Node[][] table;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };

		st = new StringTokenizer(bf.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		table = new Node[N][M];

		for (int i = 0; i < N; i++) {
			char[] str = bf.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (str[j] == '0') {
					table[i][j] = new Node(i, j, -1, 0);
				} else {
					table[i][j] = new Node(i, j, -1, 1);
				}

			}
		}
		table[0][0].weight = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2) -> {
			return e1.weight - e2.weight;
		});
		pq.add(table[0][0]);

		while (!pq.isEmpty()) {
			Node target = pq.poll();
			if (target.r == N - 1 && target.c == M - 1) {
				System.out.println(target.weight);
				break;
			} else {
				for (int i = 0; i < 4; i++) {
					int cur_r = target.r + dr[i];
					int cur_c = target.c + dc[i];

					if (cur_r >= 0 && cur_r < N && cur_c >= 0 && cur_c < M) {// 탐색하는 것이 범위 안이라면
						if (table[cur_r][cur_c].weight == -1) {// 한번도 검색x
							table[cur_r][cur_c].weight = table[target.r][target.c].weight + table[cur_r][cur_c].wall;
							pq.add(table[cur_r][cur_c]);
						}
					}

				}
			}
		}

	}

	static class Node {
		int r;
		int c;
		int weight;
		int wall;

		public Node(int r, int c, int weight, int wall) {
			this.r = r;
			this.c = c;
			this.weight = weight;
			this.wall = wall;
		}

	}

}
