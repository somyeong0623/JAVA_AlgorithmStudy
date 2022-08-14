package week02.boj_15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CJW {
    static class Point {
        public int x, y, d;

        public Point(int x, int y) {
            this(x, y, 0);
        }

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Point other = (Point) obj;
            return x == other.x && y == other.y;
        }
    }

    public static ArrayList<ArrayList<Point>> getChickens(int depth, int M, int select_cnt, ArrayList<Point> selected,
                                                          ArrayList<Point> chickens) {

        ArrayList<ArrayList<Point>> result = new ArrayList<>(new ArrayList<>());

        if (depth == chickens.size()) {
            if (select_cnt <= M) {
                result.add(selected);
                return result;
            } else {
                return null;
            }

        }

        selected.add(chickens.get(depth));
        ArrayList<ArrayList<Point>> res1 = getChickens(depth + 1, M, select_cnt + 1,
                (ArrayList<Point>) selected.clone(), (ArrayList<Point>) chickens.clone());
        if (res1 != null)
            result.addAll(res1);
        selected.remove(selected.size() - 1);
        ArrayList<ArrayList<Point>> res2 = getChickens(depth + 1, M, select_cnt, (ArrayList<Point>) selected.clone(),
                (ArrayList<Point>) chickens.clone());
        if (res2 != null)
            result.addAll(res2);

        return result;
    }

    public static int[][] copyMap(int[][] map) {
        int[][] result = new int[map.length][map.length];
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map.length; x++) {
                result[y][x] = map[y][x];
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int result = Integer.MAX_VALUE;
        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        ArrayList<Point> houses = new ArrayList<>();
        ArrayList<Point> chickens = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                int val = Integer.parseInt(st.nextToken());
                map[y][x] = val;
                if (map[y][x] == 1) {
                    houses.add(new Point(x, y));
                } else if (map[y][x] == 2) {
                    chickens.add(new Point(x, y));
                    map[y][x] = 0;
                }
            }
        }

        ArrayList<ArrayList<Point>> comb = getChickens(0, M, 0, new ArrayList<>(), chickens);

        for (ArrayList<Point> items : comb) {
            if (items.size() == 0)
                continue;

            int tresult = 0;
            for (Point ho : houses) {
                int temp = Integer.MAX_VALUE;
                for (Point ch : items) {
                    temp = Math.min(Math.abs(ch.y - ho.y) + Math.abs(ch.x - ho.x), temp);
                }
                tresult += temp;
            }

            if (tresult != 0)
                result = Math.min(result, tresult);
            for (Point p : items) {
                map[p.y][p.x] = 0;
            }
        }
        System.out.println(result);
    }
}
