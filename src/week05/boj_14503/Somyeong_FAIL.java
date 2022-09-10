package week05.boj_14503;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//14503. 로봇 청소기  (예제 2번 안나오는데 어디서 안맞는건지모르겠다 .. )
public class Somyeong_FAIL {
	static int n, m;
	static int r, c, answer, d; // d: 0 1 2 3 (북, 동, 남 서)
	static int arr[][];
	// 왼쪽, 위, 오른쪽, 아래 (서, 북, 동, 남)
	static int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 } }; //자신이 보고있는 방향에서, 왼쪽 방향부터 살펴봐야 하기 때문에 이렇게 순서를 정함 .
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			// 1.현재 위치를 청소한다.
			if (arr[r][c] == 0) {
				answer++;
				System.out.println("answer: " + answer);
				arr[r][c] = 2;
			}
			// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
			
			// 2-3.네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
			if (impossible() && arr[r + dir[(d + 3) % 4][0]][c + dir[(d +3) % 4][1]] != 1) {
				// 한칸 후진
				r = r + dir[(d + 3) % 4][0];
				c = c + dir[(d + 3) % 4][1];
				continue;
			}

			// 2-4.네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
			if (impossible() && arr[r + dir[(d + 3) % 4][0]][c + dir[(d + 3) % 4][1]] == 1) {
				break;
			}
			
			// 2-1. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면,
			// 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
			if (possibleOne()) {
				continue; // 1번부터 진행
			}

			// 2-2. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
			if (possibleTwo()) {
				continue; // 2번부터 진행 (위치이동을 안했으므로 1번으로 돌아간거랑 마찬가지)
			}

		}

		System.out.println("진짜답:" + answer);

	}

	// 2-1. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면,
	// 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
	public static boolean possibleOne() {
		for (int x = 0; x < 4; x++) {
			if (arr[r + dir[x][0]][c + dir[x][1]] == 0) {
				r = r + dir[x][0];
				c = c + dir[x][1];
				d = (d + x+1) % 4;
				return true;
			}
		}
		return false;
	}
	// 2-2. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
	public static boolean possibleTwo() {
		for (int x = 0; x < 4; x++) {
			if (arr[r + dir[x][0]][c + dir[x][1]] != 0) {
				d = (d + x+1) % 4;
				return true;
			}
		}
		return false;
	}

	public static boolean impossible() { // 로봇의 네방향 모두 청소가 이미 되어있거나 벽인 경우에 true 리턴 ( 즉, 청소불가능한경우세 true 리턴)
		for (int i = 0; i < 4; i++) {
			if (arr[r + dir[i][0]][c + dir[i][1]] != 0) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
}
