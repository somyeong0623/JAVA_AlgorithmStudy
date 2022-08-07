package week01.boj_1244;

import java.util.Scanner;

public class CJW {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        int command_cnt = sc.nextInt();
        for (int i = 0; i < command_cnt; i++) {
            int gender, number;
            gender = sc.nextInt();
            number = sc.nextInt();

            if (gender == 1) {
                int temp = number;
                while (temp <= size) {
                    arr[temp - 1] = arr[temp - 1] == 1 ? 0 : 1;
                    temp += number;
                }
            } else if (gender == 2) {
                // 여자면
                int result;
                for (result = 1; ; result++) {
                    if (number - 1 + result >= size || number - 1 - result < 0)
                        break;

                    if (arr[number - 1 - result] != arr[number - 1 + result])
                        break;
                }

                for (int t = 0; t < result; t++) {
                    int val = arr[number - 1 + t] == 0 ? 1 : 0;
                    arr[number - 1 + t] = val;
                    arr[number - 1 - t] = val;
                }
            }
        }

        for(int i=0;i<size;i++){
            System.out.print(arr[i]);
            if(i != size-1)
                System.out.println();
        }
    }
}
