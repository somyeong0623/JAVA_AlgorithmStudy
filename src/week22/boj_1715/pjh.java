import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] sleep_people;
	static int[] state;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2) -> {
			return e1 - e2;
		});
		int N = Integer.parseInt(bf.readLine());
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(bf.readLine()));
		}

		int answer = 0;
		for (int i = 0; i < N - 1; i++) {
			int num1 = pq.poll();
			int num2 = pq.poll();
			int compare_cnt = num1+num2;
			
			answer += compare_cnt;
			pq.add(compare_cnt);
		}
		System.out.println(answer);
	}

}