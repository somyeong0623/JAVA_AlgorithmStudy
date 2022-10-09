package week09.boj_11779;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class test {
	static class go implements Comparable<go> {

		int y, val;

		public go(int y, int val) {
			this.y = y;
			this.val = val;
		}

		@Override
		public int compareTo(go o) {
			// TODO Auto-generated method stub
			return this.val - o.val;
		}

	}

	static List<go>[] li;
	static int dist[];
	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		int m = Integer.parseInt(in.readLine());

		// 출발도시에서 도착 도시까지 가는데 드는 최소비용을 출력하라
		li = new ArrayList[n + 1];
		dist = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			li[i] = new ArrayList<>();
			dist[i] = n*100000+1;
		}
		for (int i = 0; i < m; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine(), " ");
			int a1, a2, a3;
			a1 = Integer.parseInt(s.nextToken());
			a2 = Integer.parseInt(s.nextToken());
			a3 = Integer.parseInt(s.nextToken());
			li[a1].add(new go(a2, a3));

		}
		StringTokenizer s = new StringTokenizer(in.readLine(), " ");
		int l1, l2;
		l1 = Integer.parseInt(s.nextToken());
		l2 = Integer.parseInt(s.nextToken());

		dijk(l1, l2);

	}

	private static void dijk(int start, int last) {
		// TODO Auto-generated method stub

		PriorityQueue<go> q = new PriorityQueue<>();

		dist[start] = 0;
		int route[] = new int[n + 1];
		route[start] = 0;
		q.add(new go(start, 0));
		while (!q.isEmpty()) {
			go a = q.poll();
			if (dist[a.y] < a.val)
				continue;

			if (a.y == last)
				break;
			for (go g : li[a.y]) {
				if (dist[g.y] > dist[a.y] + g.val) {
					dist[g.y] = dist[a.y] + g.val;
					q.add(new go(g.y, dist[g.y]));

					route[g.y] = a.y;
				}
			}
		}

		System.out.println(dist[last]);
		ArrayList<Integer> temp = new ArrayList<>();
		int check = last;
		temp.add(last);
		while (true) {

			int z = route[check];
			temp.add(z);
		
			check=z;
			if(check==start)
				break;
			
		}
		System.out.println(temp.size());
		for(int i=temp.size()-1;i>=0;i--)
		{
			System.out.print(temp.get(i)+" ");
		}
		System.out.println();

	}
}
