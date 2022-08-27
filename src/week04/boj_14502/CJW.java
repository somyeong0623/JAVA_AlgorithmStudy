package week04.boj_14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CJW {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

    }

    static int Y, X;
    // 상 우 하 좌
    static int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int[][] map;
    static List<Point> virus;
    static List<Point> space;
    static int answer;

    private static boolean isMoveVirus(int x, int y) {
        if (x < 0 || x >= X || y < 0 || y >= Y) {
            return false;
        }

        if (map[y][x] == 0) {
            return true;
        }

        return false;

    }

    private static int[][] copyMap(int[][] map) {
        int[][] result = new int[Y][X];
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                result[y][x] = map[y][x];
            }
        }

        return result;
    }

    private static void simulation(int[][] map) {
        boolean[][] visited = new boolean[Y][X];
        for (Point p : virus) {
            visited[p.y][p.x] = true;
        }

        Queue<Point> queue = new ArrayDeque<>(virus);
        while (true) {
            boolean isSpread = false;
            while (!queue.isEmpty()) {
                Point p = queue.poll();
                for (int[] dir : dirs) {
                    int nx = p.x + dir[0];
                    int ny = p.y + dir[1];
                    if (isMoveVirus(nx, ny) && !visited[ny][nx]) {
                        isSpread = true;
                        visited[ny][nx] = true;
                        map[ny][nx] = 2;
                        queue.offer(new Point(nx, ny));
                    }
                }

            }
            if (!isSpread)
                break;
        }
        int result = 0;
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if (map[y][x] == 0)
                    result++;
            }
        }
        answer = Math.max(answer, result);
    }

    private static void comb(int depth, int start) {
        if (depth == 3) {
            simulation(copyMap(map));
            return;
        }

        for (int i = start; i < space.size(); i++) {
            Point _space = space.get(i);
            map[_space.y][_space.x] = 1;
            comb(depth + 1, i + 1);
            map[_space.y][_space.x] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        virus = new ArrayList<>();
        space = new ArrayList<>();
        answer = 0;
        map = new int[Y][X];
        for (int y = 0; y < Y; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < X; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 0) {
                    space.add(new Point(x, y));
                } else if (map[y][x] == 2) {
                    virus.add(new Point(x, y));
                }
            }
        }
        comb(0, 0);
        System.out.println(answer);
    }
}
