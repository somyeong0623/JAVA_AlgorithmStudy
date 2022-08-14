package week02.boj_2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CJW {
    static int blue;
    static int white;

    public static int isArea(int[][] map) {

        for (int i = 0; i < 2; i++) {
            boolean isResult = true;
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[0].length; x++) {
                    if (map[y][x] != i) {
                        isResult = false;
                        break;
                    }
                }
                if (!isResult)
                    break;
            }
            if (isResult)
                return i;
        }
        return -1;
    }

    public static void dac(int[][] map) {
        int temp = isArea(map);
        if (temp == 0) {
            white++;
            return;
        } else if (temp == 1) {
            blue++;
            return;
        }
        int[][][] area = new int[4][map.length / 2][map.length / 2];
        int boundary = map.length / 2;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                area[(y / boundary * 2) + (x / boundary)]
                        [y % boundary]
                        [x % boundary] = map[y][x];
            }
        }

        for (int i = 0; i < 4; i++) {
            dac(area[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        white = 0;
        blue = 0;
        dac(map);
        System.out.println(white);
        System.out.println(blue);
    }
}
