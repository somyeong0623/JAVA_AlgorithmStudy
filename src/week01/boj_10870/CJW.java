package week01.boj_10870;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CJW {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> dp = new ArrayList<>();
        int n = sc.nextInt();
        dp.add(0);
        dp.add(1);

        while(n >= dp.size()){
            dp.add(dp.get(dp.size() - 1) + dp.get(dp.size() - 2));
        }

        System.out.println(dp.get(n));
    }
}
