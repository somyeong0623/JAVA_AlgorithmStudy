package week01.boj_11729;

import java.util.Scanner;

public class CJW {
    static StringBuilder sb = new StringBuilder();
    static int cnt = 0;

    static void Hanoi(int N, int start, int mid, int to) {
        // 이동할 원반의 수가 1개라면?
        if (N == 1) {
            cnt++;
            sb.append(start).append(" ").append(to).append("\n");
            return;
        }
        // A -> C로 옮긴다고 가정할 떄,
        // STEP 1 : N-1개를 A에서 B로 이동 (= start 지점의 N-1개의 원판을 mid 지점으로 옮긴다.)
        Hanoi(N - 1, start, to, mid);
        // STEP 2 : 1개를 A에서 C로 이동 (= start 지점의 N번째 원판을 to지점으로 옮긴다.)
        sb.append(start).append(" ").append(to).append("\n");
        cnt++;
        // STEP 3 : N-1개를 B에서 C로 이동 (= mid 지점의 N-1개의 원판을 to 지점으로 옮긴다.)
        Hanoi(N - 1, mid, start, to);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Hanoi(N, 1, 2, 3);
        System.out.println(cnt);
        System.out.print(sb.toString());
    }
}
