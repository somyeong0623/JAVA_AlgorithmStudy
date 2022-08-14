package week02.boj_1758;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CJW {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(sc.nextInt());
        }

        Collections.sort(list);
        Collections.reverse(list);
        long sum = 0;
        for (int i = 0; i < size; i++) {
            sum += Math.max(list.get(i) - i, 0);
        }
        System.out.println(sum);
    }
}
