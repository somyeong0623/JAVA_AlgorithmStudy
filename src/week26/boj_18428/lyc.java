import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int N;
    private static char[][] map;
    private static final Point[] teachers = new Point[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][];

        for (int n = 0; n < N; n++) {
            map[n] = br.readLine().replaceAll(" ", "").toCharArray();
        }
        int idx = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 'T') teachers[idx++] = new Point(r, c);
            }
        }
        if (comb(new Point(0, 0), 0)) System.out.println("YES");
        else System.out.println("NO");
    }

    private static final Point[] ret = new Point[3];

    private static boolean comb(Point cur, int n) {
        if (n == 3) {
            for (int i = 0; i < 3; i++) {
                map[ret[i].r][ret[i].c] = 'O';
            }
            if (canAvoid()) return true;
            for (int i = 0; i < 3; i++) {
                map[ret[i].r][ret[i].c] = 'X';
            }
            return false;
        }
        if (cur.r == N) return false;
        if (map[cur.r][cur.c] == 'X') {
            ret[n] = cur;
            if (comb(cur.getNextPoint(), n + 1)) return true;
        }
        return comb(cur.getNextPoint(), n);
    }

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, -1, 0, 1};

    private static boolean canAvoid() {
        for (int i = 0; i < 10; i++) {
            if (teachers[i] == null) break;
            for (int d = 0; d < 4; d++) {
                int nr = teachers[i].r + dr[d];
                int nc = teachers[i].c + dc[d];
                while(nr >= 0 && nr < N && nc >= 0 && nc < N) {
//                    System.out.println(nr + " " + nc);
                    if (map[nr][nc] == 'O') break;
                    else if (map[nr][nc] == 'S') return false;
                    nr += dr[d];
                    nc += dc[d];
                }
            }
        }
        return true;
    }

    private static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point getNextPoint() {
            Point nextPoint = new Point(this.r, this.c + 1);
            if (nextPoint.c == N) {
                nextPoint.r++;
                nextPoint.c = 0;
            }
            return nextPoint;
        }
    }
}