import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int R, C;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        search();
    }

    private static final int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    private static final int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

    private static Point nextPoint(int r, int c) {
        int minValue = board[r][c];
        Point ret = new Point(r, c);
        for (int d = 0; d < 8; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            if (board[nr][nc] < minValue) {
                ret = new Point(nr, nc);
                minValue = board[nr][nc];
            }
        }
        return ret;
    }

    private static Point[][] move() {
        Point[][] goal = new Point[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
//                System.out.println(r + " " + c);
                Queue<Point> q = new ArrayDeque<>();
                Point cur = new Point(r, c);
                while (true) {
                    if (goal[cur.r][cur.c] != null) break;
                    q.add(cur);
                    Point next = nextPoint(cur.r, cur.c);
                    if (cur.equals(next)) break;
                    cur = next;
                }
                if (goal[cur.r][cur.c] != null) cur = goal[cur.r][cur.c];
                while (!q.isEmpty()) {
                    Point point = q.poll();
                    goal[point.r][point.c] = cur;
                }
            }
        }
        return goal;
    }

    private static void search() {
        Point[][] goal = move();
        int[][] countMap = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                Point goalPoint = goal[r][c];
                countMap[goalPoint.r][goalPoint.c]++;
            }
        }
        print(countMap);
    }

    private static void print(int[][] countMap) {
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                sb.append(countMap[r][c]).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public boolean equals(Point o) {
            return (this.r == o.r && this.c == o.c);
        }
    }
}