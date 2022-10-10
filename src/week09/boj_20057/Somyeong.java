package week09.boj_20057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//20057. 마법사 상어와 토네이도 
public class Somyeong {
	static int N;
	static int arr[][];
	static int curR, curC;
	// 왼쪽, 아래, 오른쪽, 위 순서
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { -1, 0, 1, 0 };
	static int dLength[] = { 1, 1, 2, 2 }; // 방향별 이동 거리
	static int totalOut; // 격자 밖으로 나간 모래 양

	// 왼쪽, 아래, 오른쪽, 위 바라보고있을때의 퍼센트
	static int pr[][] = { { -2, -1, -1, -1, 0, 1, 1, 1, 2 }, { 0, 1, 0, -1, 2, 1, 0, -1, 0 },
			{ 2, 1, 1, 1, 0, -1, -1, -1, -2 }, { 0, -1, 0, 1, -2, -1, 0, 1, 0 } };
	static int pc[][] = { { 0, -1, 0, 1, -2, -1, 0, 1, 0 }, { -2, -1, -1, -1, 0, 1, 1, 1, 2 },
			{ 0, 1, 0, -1, 2, 1, 0, -1, 0 }, { 2, 1, 1, 1, 0, -1, -1, -1, -2 } };
	static int percent[] = { 2, 10, 7, 1, 5, 10, 7, 1, 2 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		curR = N / 2;
		curC = N / 2;
		int cnt = 0;
		totalOut = 0; // 정답변수: 밖으로 나간 모래의 총 양

		loop: while (true) {
			for (int d = 0; d < 4; d++) {
				for (int i = 0; i < dLength[d]; i++) {
					int nextR = curR + dr[d];
					int nextC = curC + dc[d];

					if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N)
						break loop;

					int sand = arr[nextR][nextC];
					System.out.println("sand: " +sand);
					arr[nextR][nextC] = 0;

					int spreadTotal = 0; // 퍼센트 범위의 칸으로 이동한 모래 양

					for (int k = 0; k < 9; k++) {
						int sandR = nextR + pr[d][k];
						int sandC = nextC + pc[d][k];
						int temp = sand * percent[k] / 100;

						if (sandR < 0 || sandC < 0 || sandR >= N || sandC >= N) {
							totalOut += temp;
						} else {
							arr[sandR][sandC] += temp;
						}

						spreadTotal += temp;

					}

					// 알파에 담길 모래의 양
					int alphaR = nextR + dr[d];
					int alphaC = nextC + dc[d];
					int alphaAmount = sand - spreadTotal;
					if (alphaR < 0 || alphaC < 0 || alphaR >= N || alphaC >= N) {
						totalOut += alphaAmount;
					} else {
						arr[alphaR][alphaC] += alphaAmount;
					}

					curR = nextR;
					curC = nextC;

					System.out.println("totalOut: "+totalOut);
				}
				// 방향별 이동할 거리(가중치) 업데이트
				for (int index = 0; index < 4; index++) {
					dLength[index] += 2;
				}
			}
		}
		System.out.println(totalOut);
	}

}
