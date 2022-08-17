package week02.boj_1966;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PJH {// 1966
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(bf.readLine());
			int answer = 0;
			Queue<Integer> queue = new LinkedList<>();

			for (int i = 0; i < N; i++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			
			while (true) {
				int tmp = queue.poll();
				M--;
				if(queue.size()==0) {
					answer++;
					break;
				}
				if(tmp>=Collections.max(queue)) { //버려도 된다.
					answer++;
					if(M==-1) {// 정답.
						break;
					}
				} else { //버릴 수 없으므로 다시 적재한다.
					queue.add(tmp);
					if(M==-1) {
						M = queue.size()-1;
					}
				}

			}
			System.out.println(answer);

		}

	}
}