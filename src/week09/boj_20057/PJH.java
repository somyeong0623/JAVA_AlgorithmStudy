package week09.boj_20057;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PJH {
	static Node[][] table;
	static int[] dr_list = { 0, 1, 0, -1 };
	static int[] dc_list = { 1, 0, -1, 0 };
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(bf.readLine());
		answer = 0;
		table = new Node[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				table[i][j] = new Node(i, j);
				table[i][j].sand = 0;
			}
		}
		int tr = 0;
		int tc = 0;
		int tmp_idx = 0;
		int cnt = 1;
		for (int i = 0; i < (N * N) - 1; i++) {

			table[tr][tc].sand = cnt;

			int next_tr = tr + dr_list[tmp_idx];
			int next_tc = tc + dc_list[tmp_idx];

			if (isIn(next_tr, next_tc) && table[next_tr][next_tc].sand == 0) {
				table[tr][tc].next = table[next_tr][next_tc];
				table[next_tr][next_tc].set_pre(tr, tc);
				tr = next_tr;
				tc = next_tc;

			} else {
				tmp_idx = (tmp_idx + 1) % 4;
				table[tr][tc].next = table[tr + dr_list[tmp_idx]][tc + dc_list[tmp_idx]];
				table[tr + dr_list[tmp_idx]][tc + dc_list[tmp_idx]].set_pre(tr, tc);
				tr += dr_list[tmp_idx];
				tc += dc_list[tmp_idx];
			}
			cnt++;
		}
		table[N / 2][N / 2].sand = N * N;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				table[i][j].sand = Integer.parseInt(st.nextToken());
			}
		}

		dfs(table[0][1]);
		System.out.println(answer);

	}

	static void dfs(Node node) {
		if (node.next != null) {
			dfs(node.next);
		}
		int way_r = node.pre_r - node.r;
		int way_c = node.pre_c - node.c;
		Node pre_node = table[node.pre_r][node.pre_c];
		int origin_sand = pre_node.sand;
		if (Math.abs(way_c) == 1) {// 좌우
			int level = 0;
			// 0단계 1% 1%
			move_sand(node.r + 1, node.c + (level * way_c), origin_sand / 100, pre_node);
			move_sand(node.r - 1, node.c + (level * way_c), origin_sand / 100, pre_node);

			level++;
			// 1단계 2% 7% y 2% 7%;
			move_sand(node.r + 1, node.c + (level * way_c), 7 * origin_sand / 100, pre_node);
			move_sand(node.r - 1, node.c + (level * way_c), 7 * origin_sand / 100, pre_node);
			move_sand(node.r + 2, node.c + (level * way_c), 2 * origin_sand / 100, pre_node);
			move_sand(node.r - 2, node.c + (level * way_c), 2 * origin_sand / 100, pre_node);

			level++;
			// 2단계 10% a 10%
			move_sand(node.r + 1, node.c + (level * way_c), origin_sand / 10, pre_node);
			move_sand(node.r - 1, node.c + (level * way_c), origin_sand / 10, pre_node);

			level++;
			// 3단계 5%
			move_sand(node.r, node.c + (level * way_c), 5 * origin_sand / 100, pre_node);

			// a
			move_sand(node.r, node.c + (2 * way_c), pre_node.sand, pre_node);

		} else { // 상하
			int level = 0;
			// 0단계 1% 1%
			move_sand(node.r + (level * way_r), node.c + 1, origin_sand / 100, pre_node);
			move_sand(node.r + (level * way_r), node.c - 1, origin_sand / 100, pre_node);

			level++;
			// 1단계 2% 7% y 2% 7%;
			move_sand(node.r + (level * way_r), node.c + 1, 7 * origin_sand / 100, pre_node);
			move_sand(node.r + (level * way_r), node.c - 1, 7 * origin_sand / 100, pre_node);
			move_sand(node.r + (level * way_r), node.c + 2, 2 * origin_sand / 100, pre_node);
			move_sand(node.r + (level * way_r), node.c - 2, 2 * origin_sand / 100, pre_node);

			level++;
			// 2단계 10% a 10%
			move_sand(node.r + (level * way_r), node.c + 1, origin_sand / 10, pre_node);
			move_sand(node.r + (level * way_r), node.c - 1, origin_sand / 10, pre_node);

			level++;
			// 3단계 5%
			move_sand(node.r + (level * way_r), node.c, 5 * origin_sand / 100, pre_node);

			// a
			move_sand(node.r + (2 * way_r), node.c, pre_node.sand, pre_node);
		}
	}

	static void move_sand(int r, int c, int sand, Node node) {
		if (isIn(r, c)) {
			table[r][c].sand += sand;
		} else {
			answer += sand;
		}
		node.sand -= sand;
	}

	static boolean isIn(int r, int c) {
		int n = table.length;
		if (r >= 0 && c >= 0 && r < n && c < n) {
			return true;
		}

		return false;
	}

	static class Node {
		int r;
		int c;
		Node next;
		int sand;
		int pre_r;
		int pre_c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
			next = null;
		}

		public void set_pre(int r, int c) {
			pre_r = r;
			pre_c = c;
		}
	}

}