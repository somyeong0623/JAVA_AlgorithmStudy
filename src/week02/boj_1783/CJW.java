package week02.boj_1783;

import java.util.Scanner;

public class CJW {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Y, X;
        Y = sc.nextInt();
        X = sc.nextInt();
        int result;
        if (Y == 1)
            result = 1;
        else if (Y == 2)
            result = Math.min(4, (X + 1) / 2);
        else if (X < 7)
            result = Math.min(4, X);
        else
            result = X - 7 + 5;
        System.out.println(result);
    }
}
