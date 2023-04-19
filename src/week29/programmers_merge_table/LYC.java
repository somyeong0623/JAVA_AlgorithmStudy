import java.util.*;

class Solution {
    private static final int SIZE = 50 * 50;
    private static final int[] parent = new int[SIZE];
    private static final String[] arr = new String[SIZE];

    public String[] solution(String[] commands) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            parent[i] = i;
        }
        for (String command: commands) {
            String[] cmd = command.split(" ");
            switch (cmd[0]) {
                case "UPDATE":
                    if (cmd.length == 3) update(cmd[1], cmd[2]);
                    else update(Integer.parseInt(cmd[1]) - 1, Integer.parseInt(cmd[2]) - 1, cmd[3]);
                    break;
                case "MERGE":
                    merge(
                            Integer.parseInt(cmd[1]) - 1,
                            Integer.parseInt(cmd[2]) - 1,
                            Integer.parseInt(cmd[3]) - 1,
                            Integer.parseInt(cmd[4]) - 1
                    );
                    break;
                case "UNMERGE":
                    unmerge(Integer.parseInt(cmd[1]) - 1, Integer.parseInt(cmd[2]) - 1);
                    break;
                case "PRINT":
                    list.add(print(Integer.parseInt(cmd[1]) - 1, Integer.parseInt(cmd[2]) - 1));
                    break;
            }
        }
        return list.toArray(new String[0]);
    }

    private static void update(int r, int c, String value) {
        int num = pointToNum(r, c);
        int parentOfNum = find(num);
        arr[parentOfNum] = value;
    }

    private static void update(String value1, String value2) {
        for (int idx = 0; idx < SIZE; idx++) {
            if (arr[idx] != null && arr[idx].equals(value1)) arr[idx] = value2;
        }
    }

    private static void merge(int r1, int c1, int r2, int c2) {
        int num1 = find(pointToNum(r1, c1));
        int num2 = find(pointToNum(r2, c2));
        if (num1 == num2) return;
        if (arr[num1] == null && arr[num2] != null) {
            union(num2, num1);
        } else {
            if (arr[num2] != null) arr[num2] = null;
            union(num1, num2);
        }
    }

    private static void unmerge(int r, int c) {
        int num = pointToNum(r, c);
        int parentNum = find(num);
        String tmp = arr[parentNum];
        for (int idx = 0; idx < SIZE; idx++) {
            if (parent[idx] != parentNum) continue;
            parent[idx] = idx;
            arr[idx] = null;
        }
        arr[num] = tmp;
    }

    private static String print(int r, int c) {
        int num = pointToNum(r, c);
        int parentNum = find(num);
        if (arr[parentNum] == null) return "EMPTY";
        else return arr[parentNum];
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parent[b] = a;
        for (int idx = 0; idx < SIZE; idx++) {
            find(idx);
        }
    }

    private static int find(int num) {
        if (num == parent[num]) return num;
        return parent[num] = find(parent[num]);
    }

    private static int pointToNum(int r, int c) {
        return r * 50 + c;
    }
}