import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        char[][] board = new char[H][];

        for (int h = 0; h < H; h++) {
            board[h] = br.readLine().toCharArray();
        }

        Point[] points = new Point[2];
        int cnt = 0;
        for (int r = 0; r < H; r++) {
            for (int c = 0; c < W; c++) {
                if (board[r][c] == 'C') {
                    points[cnt++] = new Point(r, c, -1, 0);
                }
            }
        }
        System.out.println(search(H, W, points, board));
    }

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, -1, 0, 1};

    private static int search(int H, int W, Point[] points, char[][] board) {
        int[][][] visit = new int[H][W][4];
        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                Arrays.fill(visit[h][w], Integer.MAX_VALUE);
            }
        }
        Point start = points[0];
        Point end = points[1];
        Queue<Point> pq = new PriorityQueue<>();
        pq.add(start);

        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            if (cur.r == end.r && cur.c == end.c) return cur.turn;
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                int nextTurn = cur.turn;
                if (cur.d != -1 && cur.d != d) nextTurn++;
                if (nr < 0 || nr >= H || nc < 0 || nc >= W || board[nr][nc] == '*' || visit[nr][nc][d] <= nextTurn) continue;
                pq.add(new Point(nr, nc, d, nextTurn));
                visit[nr][nc][d] = nextTurn;
            }
        }
        return -1;
    }

    private static class Point implements  Comparable<Point>{
        int r, c, d;
        int turn;

        public Point(int r, int c, int d, int turn) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.turn = turn;
        }

        @Override
        public int compareTo(Point o) {
            return turn - o.turn;
        }
    }
}