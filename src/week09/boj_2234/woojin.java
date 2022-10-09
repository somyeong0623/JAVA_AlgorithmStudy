package week09.boj_2234;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class test {
static int n, m;
	static int graph[][];
	static int visit[][];
	static int dx[] = { 0, -1, 0, 1 };
	static int dy[] = { -1, 0, 1, 0 };
	static class go {

		int x, y;

		public go(int x, int y) {
			this.x = x;
			this.y = y;

		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer s = new StringTokenizer(in.readLine(), " ");

		m = Integer.parseInt(s.nextToken());
		n = Integer.parseInt(s.nextToken());
		graph = new int[n][m];
		visit = new int[n][m];
		HashMap<Integer, Integer>map=new HashMap<>();
		for (int i = 0; i < n; i++) {
			s = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(s.nextToken());
			}

		}
		int count = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visit[i][j] == 0) {
					count++;
					int u=bfs(i, j,count);
					max = Math.max(max, u);
					map.put(count, u);

				}
			}

		}
		System.out.println(count);
		System.out.println(max);

		int las = Integer.MIN_VALUE;
		ArrayList<Integer> tt = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int nowz=visit[i][j];
				for (int k = 0; k <4; k ++) {
						int zx = dx[k] + i;
						int zy = dy[k] + j;
						if (0 <= zx && zx < n & 0 <= zy && zy < m) {
							if(visit[zx][zy]!=nowz)
							{
								int now=map.get(nowz);
								int now1=map.get(visit[zx][zy]);
								las = Math.max(now+now1, las);
							}
						}
			
				}

			}

		}
		System.out.println(las);

	}

	private static int bfs(int i, int j,int put) {
		// TODO Auto-generated method stub

		Queue<go> q = new LinkedList<>();
		visit[i][j] = put;
		
		q.add(new go(i, j));
		int count = 1;
		while (!q.isEmpty()) {

			go a = q.poll();
			int bit = 1;
			for (int k = 0; k < 4; k++) {
				if ((graph[a.x][a.y] & bit) == 0) {
					int zx = dx[k] + a.x;
					int zy = dy[k] + a.y;
					if (0 <= zx && zx < n & 0 <= zy && zy < m) {
						if (visit[zx][zy] == 0) {

							q.add(new go(zx, zy));
							visit[zx][zy] =put;
							count++;

						}

					}

				}
				bit <<= 1;

			}

		}
		return count;

	}

}
