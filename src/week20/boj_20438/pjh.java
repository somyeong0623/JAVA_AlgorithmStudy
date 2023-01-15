import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] sleep_people;
	static int[] state;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());// 학생수
		int K = Integer.parseInt(st.nextToken());// 조는 사람 수
		int Q = Integer.parseInt(st.nextToken());// 출석체크 보낼 사람 수
		int M = Integer.parseInt(st.nextToken());// 주어질 구간 수

		state = new int[N + 3];
		sleep_people = new boolean[N+3];
		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < K; i++) {
			sleep_people[Integer.parseInt(st.nextToken())] = true;
		}

		st = new StringTokenizer(bf.readLine());
		for (int i = 0; i < Q; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(sleep_people[tmp]) {
				continue;
			}
			
			for (int j = 1; j * tmp < N + 3; j++) {
				if(!sleep_people[j*tmp]) {
					state[j * tmp] = 1;
				}
			}

		}
		
		for(int i = 0 ; i < state.length-1;i++) {
			state[i+1] += state[i];
		}
		for(int k = 0 ; k < M;k++) {
			st = new StringTokenizer(bf.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int answer = (end-start+1)-(state[end]-state[start-1]);
//			for (int i = start; i <= end; i++) {
//				answer -= state[i];
//			}
			System.out.println(answer);
		}
		
	}

}
