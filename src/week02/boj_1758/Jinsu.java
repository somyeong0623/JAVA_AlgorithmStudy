package week02.boj_1758;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Jinsu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		Integer[] num = new Integer[N];
		long sum = 0;		//long형태로 안쓰면 틀린다
		
		for(int i=0; i<N; i++) {
			num[i] = sc.nextInt();
		}
		
		Comparator<Integer> com = new Comparator<Integer>() {	//내림차순
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		};
			
		Arrays.sort(num, com);
		
		for(int i=0; i<N; i++) {
			if (num[i] - i > 0)
				sum += (num[i]-i);
		}
		
		System.out.println(sum);
	}

}