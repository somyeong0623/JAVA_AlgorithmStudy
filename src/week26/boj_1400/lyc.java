import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int m, n;
    private static char[][] map;
    private static Signal[] signal;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 0; t < 20; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) break;

            map = new char[m][];

            for (int r = 0; r < m; r++) {
                map[r] = br.readLine().toCharArray();
            }

            Point start = null;
            Point end = null;

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (map[r][c] == 'A') start = new Point(r, c, 0);
                    else if (map[r][c] == 'B') end = new Point(r, c, 0);
                }
            }

            signal = new Signal[10];

            String nextLine = br.readLine();
            while (nextLine != null && !nextLine.equals("")) {
                st = new StringTokenizer(nextLine);
                int idx = Integer.parseInt(st.nextToken());
                char direction = st.nextToken().charAt(0);
                int hTime = Integer.parseInt(st.nextToken());
                int vTime = Integer.parseInt(st.nextToken());
                boolean startV = direction == '|';
                signal[idx] = new Signal(hTime, vTime, startV);
                nextLine = br.readLine();
            }
            if (start == null || end == null) {
                sb.append("impossible\n");
                continue;
            }
            search(start, end);
        }
        System.out.println(sb);
    }

    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, -1, 0, 1};

    private static void search(Point start, Point end) {
        int[][] dp = new int[m][n];
        Queue<Point> q = new ArrayDeque<>();
        q.add(start);
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || map[nr][nc] == '.' || map[nr][nc] == 'A') continue;
                if (map[nr][nc] == '#') {
                    if (dp[nr][nc] != 0 && dp[nr][nc] <= cur.time + 1) continue;
                    q.add(new Point(nr, nc, cur.time + 1));
                    dp[nr][nc] = cur.time + 1;
                } else if (map[nr][nc] == 'B') {
                    if (dp[nr][nc] == 0 || dp[nr][nc] > cur.time + 1) dp[nr][nc] = cur.time + 1;
                } else {
                    int intersection = map[nr][nc] - '0';
                    if (intersection < 0 || intersection > 9) continue;
                    int nextT = cur.time + 1 + signal[intersection].getTimeToNextChange(cur.time + 1, d % 2 == 0);
                    if (dp[nr][nc] != 0 && dp[nr][nc] <= nextT) continue;
                    dp[nr][nc] = nextT;
                    q.add(new Point(nr, nc, nextT));
                }
            }
        }
        if (dp[end.r][end.c] == 0) sb.append("impossible\n");
        else sb.append(dp[end.r][end.c]).append('\n');
    }

    private static class Signal {
        int hTime, vTime;
        boolean startV;

        public Signal(int hTime, int vTime, boolean startV) {
            this.hTime = hTime;
            this.vTime = vTime;
            this.startV = startV;
        }

        public int getTimeToNextChange(int currentTime, boolean vertical) {
            int tmp = currentTime % (hTime + vTime);
            if (tmp == 0) tmp = hTime + vTime;
            if (!vertical) {    // 수평으로 들어올 때
                if (!startV) {  // 수평부터 켜질 때
                    if (tmp <= hTime) return 0;
                    else return (vTime + hTime + 1) - tmp;
                } else {          // 수직부터 켜질 때
                    if (tmp > vTime) return 0;
                    else return (vTime + 1) - tmp;
                }
            } else {            // 수직으로 들어올 때
                if (!startV) {  // 수평부터 켜질 때
                    if (tmp > hTime) return 0;
                    else return (hTime + 1) - tmp;
                } else {          // 수직부터 켜질 때
                    if (tmp <= vTime) return 0;
                    else return (vTime + hTime + 1) - tmp;
                }
            }
        }
    }

    private static class Point {
        int r, c, time;

        public Point(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}