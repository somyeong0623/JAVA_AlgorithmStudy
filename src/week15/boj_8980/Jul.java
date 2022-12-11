package week15.boj_8980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jul {
	static class pakage{
		int from,to,boxes;
		public pakage(int from, int to, int boxes) {
			this.from = from;
			this.to = to;
			this.boxes  = boxes;
		}
		@Override
		public String toString() {
			return "pakage [from=" + from + ", to=" + to + ", boxes=" + boxes + "]";

		}
	}
	static int n,c,m,result;
	static ArrayList<pakage> pkg;
	static int[] remain;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		pkg = new ArrayList<>();
		remain = new int[n+1];
		Arrays.fill(remain, c);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			pkg.add(new pakage(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(pkg,new Comparator<pakage>() {

			@Override
			public int compare(pakage o1, pakage o2) {
				// TODO Auto-generated method stub
				return o1.to-o2.to;
			}
		});
//		System.out.println(pkg);
		for (int i =0; i < m; i++) {
			int temp = c;
			for (int j = pkg.get(i).from; j < pkg.get(i).to; j++) {
				temp = Math.min(temp, remain[j]);
			}
			temp = Math.min(temp, pkg.get(i).boxes);
			for (int j = pkg.get(i).from; j < pkg.get(i).to; j++) {
				remain[j] -= temp;
			}
			result += temp;
//			System.out.println(Arrays.toString(remain));
		}
		System.out.printf("%d", result);
	}
}