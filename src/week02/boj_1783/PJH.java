package week02.boj_1783;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PJH {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N, M ;
		int answer = 1;
		st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if(N == 2) {
			M--; //기존에 1개 제외하고 
			M /= 2; //나머지 가로칸을 2로 나누기
			answer += M<=4?M:3;
		}else if(N > 2){
			if(M<7) { // 7이상이 4번이상 움이기 최소조건
				answer = M<=4?M:4;
			}else { //7이상
				answer = 5; //4번 움직였다 치고 
				M -= 7; //7칸 사용해야됨
				answer += M; // 남은 것만큼 한칸씩 이동했다 치면 나온다.
			}
		}
		System.out.println(answer);
	}
}