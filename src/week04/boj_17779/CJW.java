package week04.boj_17779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CJW {
    static int N;
    static int[][] map;

    private static int[][] copyMap(int[][] map) {
        int[][] result = new int[N][N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                result[y][x] = map[y][x];
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        for (int y = 1; y < N; y++) {
            for (int x = 0; x < N; x++) {
                for (int d1 = 1; d1 < y; d1++) {
                    for (int d2 = 1; true; d2++) {
                        int[][] nmap = copyMap(map);
                        System.out.println(x + " " + y + " " + d1 + " " + d2);
                    }
                }
            }
        }
    }
}
