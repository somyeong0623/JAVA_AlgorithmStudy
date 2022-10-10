package week09.boj_2234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PJH {
	static Field[][] table;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		//크기 받기
		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());// 가로
		int M = Integer.parseInt(st.nextToken());// 세로
		
		//초기값 세팅
		table = new Field[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				table[i][j] = new Field();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				table[i][j].search(i, j, Integer.parseInt(st.nextToken()));
			}
		}
		
		
		//영역나누기
		int union_idx = 0;
		ArrayList<Integer> size_list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				int tmp_size = 0;
				if (!table[i][j].visit) {
					Queue<Field> union_list = new LinkedList<>();
					union_list.add(table[i][j]);
					table[i][j].visit=true;
					while (!union_list.isEmpty()) {
						Field tmp = union_list.poll();
						tmp.union = union_idx;
						tmp_size++;
						
						for (Field next : tmp.list) {
							if (!next.visit) {
								next.visit=true;
								union_list.add(next);
							}
						}

					}
					size_list.add(tmp_size);
					union_idx++;
				}

			}
		}
		
		//벽부셨을 때 가장 큰 값 찾기
		int break_max_size = 0;
		for (int i = 0; i < M-1; i++) {
			for (int j = 0; j < N-1; j++) {
				if(table[i][j].union!=table[i+1][j].union) {
					break_max_size = Math.max(break_max_size, size_list.get(table[i][j].union)+size_list.get(table[i+1][j].union));
				}
				if(table[i][j].union!=table[i][j+1].union) {
					break_max_size = Math.max(break_max_size, size_list.get(table[i][j].union)+size_list.get(table[i][j+1].union));
				}
			}
			if(table[i][N-1].union!=table[i+1][N-1].union) {
				break_max_size = Math.max(break_max_size, size_list.get(table[i][N-1].union)+size_list.get(table[i+1][N-1].union));
			}
		}
		for(int i = 0 ; i < N-1;i++) {
			if(table[M-1][i].union!=table[M-1][i+1].union) {
				break_max_size = Math.max(break_max_size, size_list.get(table[M-1][i].union)+size_list.get(table[M-1][i+1].union));
			}
		}
		
		//사이즈내의 가장 큰 값 지정
		int size_list_max=0;
		for(int tmp:size_list) {
			size_list_max = Math.max(size_list_max, tmp);
		}

//		for (int i = 0; i < M; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(table[i][j].union + " ");
//			}
//			System.out.println();
//		}
//		System.out.println(size_list);
		System.out.println(union_idx);
		System.out.println(size_list_max);
		System.out.println(break_max_size);
	}

	static class Field {
		boolean visit;
		ArrayList<Field> list;
		int union;

		public Field() {
			list = new ArrayList<>();
			visit = false;
		}

		void search(int r, int c, int num) {
			boolean[] check = new boolean[4];// 북동남서
			Arrays.fill(check, true);
			if (num >= 8) {
				check[2] = false;
				num -= 8;
			}
			if (num >= 4) {
				check[1] = false;
				num -= 4;
			}
			if (num >= 2) {
				check[0] = false;
				num -= 2;
			}
			if (num >= 1) {
				check[3] = false;
			}
			for (int i = 0; i < 4; i++) {
				if (check[i]) {
					list.add(table[r + dr[i]][c + dc[i]]);
				}
			}

		}

	}

}