package week08.boj_1261;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jinsu {

	static class Point{
		int x, y, cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		
	}
	static int N, M;
	static int[][] map;
	static int min = Integer.MAX_VALUE;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//열
		M = Integer.parseInt(st.nextToken());	//행
		
		map = new int[M][N];
		for(int i=0; i<M; i++) {
			String s = br.readLine();
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(s.substring(j, j+1));
			}
		}	//입력완료
		
		min = bfs(0, 0);
		
		System.out.println(min);
	}

	private static int bfs(int r, int c) {
		PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				return o1.cnt-o2.cnt;
			}
			
		});
		boolean[][] visited = new boolean[M][N];
		pq.add(new Point(r, c, 0));
		visited[r][c] = true;
		
		while(!pq.isEmpty()) {
			Point cur = pq.poll();
			
			if(cur.x == M-1 && cur.y == N-1) {
				return cur.cnt;
			}
			for(int k=0; k<4; k++) {
				int nx = cur.x+dx[k];
				int ny = cur.y+dy[k];
				
				if(nx>=0 && ny >= 0 && nx < M && ny < N) {
					if(!visited[nx][ny]) {
						if(map[nx][ny] == 1) {
							pq.add(new Point(nx, ny, cur.cnt+1));
						}
						else {
							pq.add(new Point(nx, ny, cur.cnt));
						}
						visited[nx][ny] = true;
					}
					
				}
				
			}
			
		}
		return min;
	}

}
