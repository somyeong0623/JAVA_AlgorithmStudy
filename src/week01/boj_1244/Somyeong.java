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

		int n = Integer.parseInt(st.nextToken()); //스위치 갯수 
		st = new StringTokenizer(br.readLine());
		arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		student = Integer.parseInt(st.nextToken());

		for (int i = 0; i < student; i++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken()); // 성별 입력 
			num = Integer.parseInt(st.nextToken()); // 스위치 번호 

			if (gender == 1) { // 남학생 경우
				for (int j = num; j <= n; j += num) {
					if (arr[j] == 0)
						arr[j] = 1;
					else
						arr[j] = 0;
				}

			} else {// 여학생 경우
				range = 0; //ragne: 양쪽으로 얼마만큼 뻗어나가는지 세는 변수. 
				while (true) {
					range++; 
					if (num - range <= 0 || num + range > n || arr[num + range] != arr[num - range]) { //범위 체크 및 양쪽 수 대칭인지 체크. 
						range--; // 조건 안맞으면 --로 되돌려놓고 while문 끝내기 .
						break;
					}

				}
				for (int j = num - range; j <= num + range; j++) { // 대칭 시작지점~ 끝 지점까지 toggle 해주기 
					if (arr[j] == 0)
						arr[j] = 1;
					else
						arr[j] = 0;
				}

			}
		}

		//스위치 상태 출력: 한줄에 20개씩 (이 문구 안봐서 계속 틀림)
		for(int i=1; i<=n; i++) { //21, 41, 61,,번째마다 개행.
			if(i!=1 && i%20==1) {
				System.out.println();
			}
			System.out.print(arr[i]+" ");
			
		}
	
		

	}
}