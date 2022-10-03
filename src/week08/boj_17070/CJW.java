package week08.boj_17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CJW {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        // [y][x][dir]
        /*
         * dir = 0 : r dir = 1 : rd dir = 2 : d
         */
        int[][][] dp = new int[N][N][3];
        dp[0][1][0] = 1;

        StringTokenizer st;

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 2; i < N; i++) {
            if (map[0][i] == 1)
                break;
            dp[0][i][0] = dp[0][i - 1][0];
        }

        for (int y = 1; y < N; y++) {
            for (int x = 1; x < N; x++) {
                if (map[y][x] == 1)
                    continue;

                // 수평 파이프의 경우의 수
                // 수평
                dp[y][x][0] += (dp[y][x - 1][0] + dp[y][x - 1][1]);

                // 수직 파이프의 경우의 수
                // 수직
                dp[y][x][2] += (dp[y - 1][x][2] + dp[y - 1][x][1]);

                // 대각선 파이프의 경우의 수
                // 현재 놓을 자리의 위칸, 왼쪽칸이 비어있어야 함.
                if (map[y - 1][x] == 0 && map[y][x - 1] == 0) {
                    dp[y][x][1] += dp[y - 1][x - 1][0];
                    dp[y][x][1] += dp[y - 1][x - 1][1];
                    dp[y][x][1] += dp[y - 1][x - 1][2];
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += dp[N - 1][N - 1][i];
        }
        System.out.println(sum);
    }
}
