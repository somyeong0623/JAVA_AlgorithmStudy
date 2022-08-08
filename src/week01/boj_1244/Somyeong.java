package week01.boj_1244;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Somyeong {

	static int n;// 스위치 개
	static int[] arr;
	static int student, gender, num;
	static int range;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		student = Integer.parseInt(st.nextToken());

		for (int i = 0; i < student; i++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());

			if (gender == 1) { // 남학생 경우
				for (int j = num; j <= n; j += num) {
					if (arr[j] == 0)
						arr[j] = 1;
					else
						arr[j] = 0;
				}

			} else {// 여학생 경우
				range = 0;
				while (true) {
					range++;
					if (num - range <= 0 || num + range > n || arr[num + range] != arr[num - range]) {
						range--;
						break;
					}

				}
				for (int j = num - range; j <= num + range; j++) {
					if (arr[j] == 0)
						arr[j] = 1;
					else
						arr[j] = 0;
				}

			}
		}

		//스위치 상태 출력: 한줄에 20개씩
		for(int i=1; i<=n; i++) { //21, 41, 61,,번째마다 개행.
			if(i!=1 && i%20==1) {
				System.out.println();
			}
			System.out.print(arr[i]+" ");
			
		}
	
		

	}
}