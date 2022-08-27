package week04.boj_20055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CJW {
    static int N, K;
    static int[][] map;
    // 우 하 좌 상
    static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int zeroDurabilityCount;
    static boolean[] container;

    private static boolean isMove(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= 2)
            return false;

        return true;
    }

    private static void setCircuit(int[] values) {
        int nx = 1;
        int ny = 0;
        map[ny][nx] = values[values.length - 1];
        int idx = 0;
        for (int[] dir : dirs) {
            nx += dir[0];
            ny += dir[1];
            do {
                if (!isMove(nx, ny))
                    break;
                map[ny][nx] = values[idx++];

                nx += dir[0];
                ny += dir[1];
            } while (isMove(nx, ny));
            nx -= dir[0];
            ny -= dir[1];
        }
    }

    private static int[] getCircuit() {
        int[] result = new int[2 * N];
        int nx = 0;
        int ny = 0;
        int idx = 0;
        for (int[] dir : dirs) {
            nx += dir[0];
            ny += dir[1];
            do {
                result[idx++] = map[ny][nx];

                nx += dir[0];
                ny += dir[1];
            } while (isMove(nx, ny));
            nx -= dir[0];
            ny -= dir[1];
        }
        return result;
    }

    private static void rotation() {
        setCircuit(getCircuit());
        for (int idx = container.length - 1; idx > 0; idx--) {
            container[idx] = container[idx - 1];
        }
        container[0] = false;
        if (container[container.length - 1]) {
            container[container.length - 1] = false;
        }
    }

    private static void moveRobot() {
        for (int i = container.length - 1; i > 0; i--) {
            if (!container[i] && container[i - 1]) {
                if (map[0][i] > 0) {
                    map[0][i]--;
                    container[i] = true;
                    container[i - 1] = false;
                    if (map[0][i] == 0) {
                        zeroDurabilityCount++;
                    }
                }
            }
        }

        container[container.length - 1] = false;
    }

    private static void putRobot() {
        if (map[0][0] > 0) {
            container[0] = true;
            map[0][0]--;
            if (map[0][0] == 0) {
                zeroDurabilityCount++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[2][N];

        st = new StringTokenizer(br.readLine(), " ");
        container = new boolean[N];

        // 위 컨테이너 내구도
        for (int i = 0; i < N; i++) {
            map[0][i] = Integer.parseInt(st.nextToken());
        }
        // 아래 컨테이너 내구도
        for (int i = 0; i < N; i++) {
            map[1][N - i - 1] = Integer.parseInt(st.nextToken());
        }


        int level = 0;
        while (zeroDurabilityCount < K) {
            level++;
            rotation();
            moveRobot();
            putRobot();
        }

        System.out.println(level);
    }
}
