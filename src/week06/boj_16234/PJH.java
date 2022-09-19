package week06.boj_16234;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PJH {
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int answer = 0;
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		Country[][] table = new Country[N][N];

		int[] rotate_r = { -1, 0, 1, 0 };
		int[] rotate_c = { 0, 1, 0, -1 };

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				table[i][j] = new Country(i * N + j, i * N + j, tmp);
			}
		}

		while (true) {
			boolean[][] check = new boolean[N][N];
			int union_check = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!check[i][j]) {// 확인해보지 않은 것
						union_check++;
						Queue<Point> q = new LinkedList<>();
						check[i][j] = true;
						q.add(new Point(i, j));
						table[i][j].union_cnt++;
						table[i][j].plus_value+=table[i][j].union.value;
						
						while (!q.isEmpty()) {
							Point tmp = q.poll();
							for (int k = 0; k < 4; k++) {// 사방탐색
								int cur_r = rotate_r[k] + tmp.r;
								int cur_c = rotate_c[k] + tmp.c;

								if (check(cur_r, cur_c) && !check[cur_r][cur_c]) {// 테이블넘어가지 않는지 확인한다. 그리고 이전에 탐색이 되지
																					// 않았다면 수행
									int target = Math.abs(table[cur_r][cur_c].value - table[tmp.r][tmp.c].value); // 두
																													// 국가의
																													// 인구수
																													// 차이
									if (target >= L && target <= R) {// 두 국가 차이가 유효하다.
										check[cur_r][cur_c] = true; // 해당 국가 체크 후
										table[cur_r][cur_c].union = table[i][j]; // 연합 리더를 처음 탐색하고자 하는 국가로 지정한다.
										table[cur_r][cur_c].union.union_cnt++;
										table[cur_r][cur_c].union.plus_value+=table[cur_r][cur_c].value;
										q.add(new Point(cur_r, cur_c));
										
									}
								}

							}
						}

					}
					
				}
			}
			if(union_check==N*N) {
				System.out.println(answer);

				break;	
			}else {
//				System.out.println(union_check);
//				for(int i = 0 ; i < N;i++) {
//					for(int j = 0 ; j < N;j++) {
//						System.out.print(table[i][j].union.id +" ");
//					}
//					System.out.println();
//				}
//				System.out.println();
//				
				for(int i = 0 ; i < N;i++) {
					for(int j = 0 ; j < N;j++) {
						table[i][j].value= (table[i][j].union.plus_value/table[i][j].union.union_cnt);
						//System.out.print(table[i][j].value+" ");
					}
					//System.out.println();
				}
//				System.out.println("------------------------------------------------------------------");
				
				answer++;
				for(int i = 0 ; i < N;i++) {
					for(int j = 0 ; j < N;j++) {
						table[i][j].init(); //다음 처리를 위해 초기화 필요
					}
				}
			}
		}

	}

	public static boolean check(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return true;
		}
		return false;
	}

}

class Point {
	int r;
	int c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

class Country {
	int id;
	Country union;//초기화 필요
	int value;
	int union_cnt; //초기화 필요
	int plus_value; //초기화 필요
	public Country(int id, int union, int value) {
		this.id = id;
		this.union = this;
		this.value = value;
		union_cnt = 0;
		int plus_value = 0;
	}
	public void init() {
		union = this;
		union_cnt = 0;
		plus_value = 0;
	}
}
