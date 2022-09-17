package week06.boj_18188;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CJW {
    // WASD
    final static String move = "WASD";
    final static int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private static boolean isMove(int x, int y) {
        if (x < 0 || x >= X || y < 0 || y >= Y)
            return false;
        if (map[y][x] == '.' || map[y][x] == 'Z')
            return true;
        return false;
    }

    private static boolean subset(int x, int y, int depth) {
        if (depth >= N) {
            if (map[y][x] == 'Z') {
                selectedCount = depth;
                return true;
            } else {
                return false;
            }

        }

        if (map[y][x] == 'Z') {
            selectedCount = depth;
            return true;
        }

        for (int i = 0; i < 2; i++) {
            isSelected[depth] = command[depth][i];
            int[] dir = dirs[move.indexOf(command[depth][i])];
            if (isMove(x + dir[0], y + dir[1])) {
                boolean res = subset(x + dir[0], y + dir[1], depth + 1);
                if (res)
                    return true;
            }
        }
        return false;
    }

    static int selectedCount;
    static char[] isSelected;
    static int X, Y;
    static int N;
    static char[][] map;
    static char[][] command;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        map = new char[Y][X];
        int px = -1, py = -1;

        for (int y = 0; y < Y; y++) {
            map[y] = br.readLine().toCharArray();
            for (int x = 0; x < X; x++) {
                if (map[y][x] == 'D') {
                    px = x;
                    py = y;
                    map[y][x] = '.';
                }
            }
        }

        N = Integer.parseInt(br.readLine());
        isSelected = new char[N];
        command = new char[N][2];
        for (int c = 0; c < N; c++) {
            st = new StringTokenizer(br.readLine());
            command[c][0] = st.nextToken().charAt(0);
            command[c][1] = st.nextToken().charAt(0);
        }

        boolean res = subset(px, py, 0);
        if (res) {
            System.out.println("YES");
            for (int i = 0; i < selectedCount; i++) {
                System.out.print(isSelected[i]);
            }
        } else {
            System.out.println("NO");
        }
    }
}
