package week06.boj_16234;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Jinsu {

	static int N, L, R, result;
	static int[][] area;
	static boolean[][] visited;
	static boolean flag=false;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//격자 크기
		L = Integer.parseInt(st.nextToken());	//인구차이 최소
		R = Integer.parseInt(st.nextToken());	//인구차이 최대
		
		area = new int[N][N];
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
			}
		}	//입력 완료
		
		while(true) {
			visited = new boolean[N][N];
			flag = false;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j])
						bfs(i, j);
				}
			}
			
			if (!flag)
				break;
			
			result++;
		}
		
		System.out.println(result);
	}


	private static void bfs(int r, int c) {
		Queue<Point> q = new ArrayDeque<>();
		ArrayList<Point> arr = new ArrayList<>();
		
		visited[r][c] = true;
		
		q.offer(new Point(r, c));
		arr.add(new Point(r, c)); //인구 수 바꿔주기 위한 좌표 저장
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			
			visited[x][y] = true;
			
			for(int k=0; k<4; k++) {
				int nx = x+dx[k];
				int ny = y+dy[k];
				
				
				if (nx >= 0 && ny>=0 && nx < N && ny < N &&
						Math.abs(area[x][y] - area[nx][ny]) >= L && Math.abs(area[x][y] - area[nx][ny]) <= R
						&& !visited[nx][ny]) {
					
					q.offer(new Point(nx, ny));
					arr.add(new Point(nx, ny));
					visited[nx][ny] = true;
					
				}
			}
		}
		
		if(arr.size() > 1)
			flag = true;
		
		int value = 0;
		for(int i=0; i < arr.size(); i++) {
			Point cu = arr.get(i);
			value += area[cu.x][cu.y];
		}
		
		int p = value/arr.size();
		
		for(int i=0; i<arr.size(); i++) {
			Point cur_p = arr.get(i);
			area[cur_p.x][cur_p.y] = p;
			
		}
		
	}

}
