package week02.boj_1758;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

//1758. 알바생 강호 
/*
 * 팁이 음수이면 어차피 0취급되므로 배열을 내림차순 정렬하여 계산했을 때 답이 최대가 된다. 
 */
public class Somyeong {
	static int n;
	static Integer[] arr; //역순정렬을 하려면 기본타입의 배열을 Wrapper클래스로 박싱해주어야 한다. 
	static long answer; //int범위 넘어가므로 long형 선언 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		arr= new Integer[n];
		for(int i=0; i<n; i++) {
			arr[i]=sc.nextInt();
		}
		
		
		Arrays.sort(arr, (i1,i2)->i2-i1); 
		for(int i=0; i<n; i++) {
			if(arr[i]-(i+1-1)<0)
				continue;
			answer+=arr[i]-(i+1-1);
		}
		System.out.println(answer);
	}
}
