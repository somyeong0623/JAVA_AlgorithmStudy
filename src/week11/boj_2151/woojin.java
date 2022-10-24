package week11.boj_2151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static char graph[][];
	static int visit[][][];
	static int sx, sy;
	static int fx, fy;

	static class go implements Comparable<go> {
		int x, y, dir, now;// x,y,방향,문개수

		public go(int x, int y, int dir, int now) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.now = now;
		}

		@Override
		public int compareTo(go o) {
			// TODO Auto-generated method stub
			return this.now - o.now;
		}

	}

	static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		graph = new char[n][n];
		visit = new int[n][n][4];
		sx = -1;
		sy = -1;
		fx = -1;
		fy = -1;
		for (int i = 0; i < n; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine(), " ");
			char temp[] = s.nextToken().toCharArray();
			for (int j = 0; j < n; j++) {

				graph[i][j] = temp[j];
				if (temp[j] == '#') {
					if (sx == -1) {
						sx = i;//시작 거울 위치
						sy = j;
					} else if (sx != -1) {

						fx = i;//끝나는 거울 위치
						fy = j;
					}
				}

			}
		}

		bfs();

	}

	private static void bfs() {
		// TODO Auto-generated method stub

		PriorityQueue<go> q = new PriorityQueue<>();
		// 0:북 1:동 2:남 3:서
		int dx[] = { -1, 0, 1, 0 };
		int dy[] = { 0, 1, 0, -1 };
		for (int i = 0; i < 4; i++) {
			q.add(new go(sx, sy, i, 0));
		}
		visit[sx][sy][0] = 1;

		while (!q.isEmpty()) {
			go a = q.poll();
			visit[a.x][a.y][a.dir] = 1;
			
				int zx = dx[a.dir] + a.x;
				int zy = dy[a.dir] + a.y;
				if (0 <= zx && zx < n && 0 <= zy && zy < n && visit[zx][zy][a.dir] == 0 && graph[zx][zy] != '*') {// 해당 // 방향으로
					visit[zx][zy][a.dir] = 1;//방문한 방향 방문 처리
                    if (graph[zx][zy] == '.') {//거울 설치를 안한 경우
						q.add(new go(zx, zy, a.dir, a.now));
					} else if (graph[zx][zy] == '!') {
						// 거울을 설치할 수도 있고
						// 들어온 방향에 따라 설치를 해줘야 함

						if ((zx == a.x - 1 && a.y == zy) || (zx == a.x + 1 && a.y == zy))// 북 남 인경우엔 동서(1,3)로 이동
						{
							q.add(new go(zx, zy, 1, a.now + 1));
							q.add(new go(zx, zy, 3, a.now + 1));
						} else if ((zx == a.x && a.y == zy + 1) || (zx == a.x && a.y == zy - 1)) {// 동 서 인경우엔 남 북(0,2)
																									// 으로 이동
							q.add(new go(zx, zy, 0, a.now + 1));
							q.add(new go(zx, zy, 2, a.now + 1));
						}
						// 거울을 설치 안할수도 있지
						q.add(new go(zx, zy, a.dir, a.now));
						
					}
					if (graph[zx][zy] == '#') {
						if (zx == fx && zy == fy) {
							System.out.println(a.now);
							return;
						}
					}

				}

			

		}

	}
}
