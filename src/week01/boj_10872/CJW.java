package week01.boj_10872;

import java.util.Scanner;

public class CJW {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long result = 1;
        int n = sc.nextInt();
        for (int i = 2; i <= n; i++) {
            result *= i;
        }

        System.out.println(result);
    }
}
