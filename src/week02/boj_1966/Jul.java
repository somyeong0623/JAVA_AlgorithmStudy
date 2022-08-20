package week02.boj_1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jul {
	static int test_case;
	static Queue<int[]> queue;
	static ArrayList<Integer> arrlst;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		test_case = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= test_case; i++) {	
			st = new StringTokenizer(br.readLine().trim());
			int n = Integer.parseInt(st.nextToken());
			int index = Integer.parseInt(st.nextToken());
			queue = new ArrayDeque<>();
			arrlst= new ArrayList<>();
			int result = 1;
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				queue.offer(new int[] {num,j});
				arrlst.add(num);
			}
			while (!queue.isEmpty()) {
				int arrlstMax = Collections.max(arrlst);
				int[] temp = queue.poll();
				if (temp[0] == arrlstMax) {
					arrlst.remove(Integer.valueOf(arrlstMax));
					if (temp[1] == index) {
						sb.append(result+"\n");
						break;
					}
					result++;
				}else {
					queue.offer(temp);
				}
			}
		}
		System.out.println(sb);
	}

}
