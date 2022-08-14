package week02.boj_2078;

import java.util.Scanner;

public class CJW {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1, x2;
        x1 = sc.nextInt();
        x2 = sc.nextInt();
        // 큰쪽에서 작은 족을 빼야함.
        int L = 0, R = 0;
        while (!(x1 == 1 && x2 == 1)) {
            if (x1 > x2) {
                x1 -= x2;
                L++;
            } else if (x1 < x2) {
                x2 -= x1;
                R++;
            }
        }
        System.out.println(L + " " + R);
    }
}
