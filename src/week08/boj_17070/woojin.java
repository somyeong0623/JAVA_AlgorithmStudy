import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer s;
		int n = Integer.parseInt(in.readLine());
		int graph[][][];
		graph = new int[n][n][3];

		int map[][] = new int[n][n];

		for (int i = 0; i < n; i++) {
			s = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(s.nextToken());
			}
		}
		graph[0][1][0] = 1;
		int check=0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n; j++) {
				if (i == 0) {
					if(check==1)
						continue;
					if (map[i][j] == 0) {
						graph[i][j][0] = 1;// 여기는 가로 방향임
					}
					else
					{
						check=1;
					}
					
				} else {

					if (map[i][j] == 1)
						continue;

					// 가로
					// 이전에 가로와 대각선으로 놓았던 경우

					graph[i][j][0] = graph[i][j - 1][0] + graph[i][j - 1][2];// 여기는 가로 방향임

					// 세로
					// 이전에 세로, 대각선으로 놓았던 경우
					graph[i][j][1] = graph[i - 1][j][1] + graph[i - 1][j][2];// 여기는 가로 방향임

					// 대각선 방향 탐색
					// 이전에 가로 세로 대각선으로 놓았던 경우
					if (map[i - 1][j] == 1 || map[i][j - 1] == 1)
						continue;
					graph[i][j][2] = graph[i - 1][j - 1][1] + graph[i - 1][j - 1][2] + graph[i - 1][j - 1][0];// 여기는 가로
																												// 방향임

				}
			}
		}

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				System.out.print((graph[i][j][0] + graph[i][j][1] + graph[i][j][2]) + " ");
//			}
//			System.out.println();
//		}

		System.out.println(graph[n - 1][n - 1][0] + graph[n - 1][n - 1][1] + graph[n - 1][n - 1][2]);

	}

}