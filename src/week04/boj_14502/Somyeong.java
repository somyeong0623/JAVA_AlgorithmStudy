package week04.boj_14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

//14502. 연구소 
public class Somyeong {
	static class Info {
		int r, c;

		public Info(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Info [r=" + r + ", c=" + c + "]";
		}
	}

	static int N, M; // 세로, 가로 크기
	static int arr[][];
	static int numbers[]; // 선택한 빈칸 3개
	static ArrayList<Info> blanks; // 빈칸정보 담는 리스트
	static int answer = Integer.MIN_VALUE;
	static int len; // blanks 리스트의 길이 (==빈칸의 갯수)
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		blanks = new ArrayList<Info>();
		numbers = new int[3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0) { // 빈칸정도 blanks 리스트에 넣기
					blanks.add(new Info(i, j));
				}
			}
		}
		// 빈칸의 갯수 
		len = blanks.size();

		comb(0, 0); // cnt, start
		System.out.println(answer);

	}

	//빈칸 리스트중에서 3개 고르기 
	static void comb(int cnt, int start) {
		if (cnt == 3) {
			build(numbers); // 3개 골랐으면 build함수 시작

			return;
		}
		for (int i = start; i < len; i++) {
			numbers[cnt] = i;
			comb(cnt + 1, i + 1);
		}
	}

	static void build(int[] numbers) { // arr2: arr의 카피본

		//arr을  arr2에 복사 
		int arr2[][] = new int[N][M]; //배열복사 이렇게 해야함 (매개변수로 하면 복사 안되고, 원본배열이 바뀐다) 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr2[i][j] = arr[i][j];
			}
		}

		// 조합으로 뽑힌 3곳의 빈칸을 벽으로 막기
		for (int i = 0; i < 3; i++) {
			Info info = blanks.get(numbers[i]);
			arr2[info.r][info.c] = 1;
		}

		//바이러스가 있는 좌표에서 bfs돌려서 바이러스 확산 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr2[i][j] == 2) { // 2인곳에서부터 바이러스 확산
					Queue<Info> queue = new ArrayDeque<Info>();
					queue.add(new Info(i, j));
					while (!queue.isEmpty()) {
						Info cur = queue.poll();
						for (int d = 0; d < 4; d++) {
							int nr = cur.r + dr[d];
							int nc = cur.c + dc[d];

							if (nr >= 0 && nc >= 0 && nr < N && nc < M && arr2[nr][nc] == 0) {
								arr2[nr][nc] = 2;
								queue.add(new Info(nr, nc));
							}
						}
					}
				}
			}
		}
		//안전 구역 갯수 세기 
		int safe = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr2[i][j] == 0)
					safe++;
			}
		}
		//최댓값 갱신 
		answer = Math.max(answer, safe);
	}
}
