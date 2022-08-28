package week04.boj_14502;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jinsu {

	static int N, M;
	static int[][] map;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}	//입력 완료
		
		StandWall(0);
		System.out.println(max);
	}

	private static void bfs() {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int[][] graph = new int[N][M];
		Queue<Point> q = new LinkedList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				graph[i][j] = map[i][j];
			}
		}
		
		for(int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (graph[i][j] == 2) {
					q.offer(new Point(i, j));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			for(int k=0; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M){
					if (graph[nx][ny] == 0) {
						graph[nx][ny] = 2;
						q.add(new Point(nx, ny));
					}
				}
			}
		}
		
		int sum = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(graph[i][j] == 0) {
					sum++;
				}
			}
		}
		max = Math.max(max, sum);
	}

	private static void StandWall(int cnt) {
		if (cnt == 3) {
			bfs();
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					map[i][j] = 1;
					StandWall(cnt+1);
					map[i][j] = 0;
				}
			}
		}
	}

}
