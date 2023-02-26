package week25.boj_17780;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Jul {
	static class Point{
		int x,y;
		Horse horse;
		Point(int x , int y ,Horse horse){
			this.x = x;
			this.y = y;
			this.horse = horse;
		}
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", horse=" + horse + "]";
		}

	}
	static class Horse{
		int num,dir;
		Horse(int num,int dir){
			this.num = num;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Horse [num=" + num + ", dir=" + dir + "]";
		}
		
	}
	static int n,k;
	static Deque<Horse>[][] horse;
	static int[][] graph;
	static Point[] pos;
	static int[] dx = {0,0,0,-1,1};
	static int[] dy = {0,1,-1,0,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.valueOf(st.nextToken());
		k = Integer.valueOf(st.nextToken());
		horse = new ArrayDeque[n+1][n+1];
		graph = new int[n+1][n+1];
		pos = new Point[k+1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < n; j++) {
				horse[i+1][j+1] = new ArrayDeque<>();
				graph[i+1][j+1] = Integer.valueOf(st.nextToken());
			}
		}
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			int c = Integer.valueOf(st.nextToken());
			horse[a][b].add(new Horse(i+1, c));
			pos[i+1] = new Point(a, b, new Horse(i+1, c));
		}
		int count = 0;
		while (count<=10) {
			if(isDone())break;
			move();
			count++;
			
		}
		System.out.println(count==1001?-1:count);
	}
	private static void move() {

		for (int i = 1; i < pos.length; i++) {
			if(pos[i] == null||pos[i].horse.num != i)continue;
			Point temp = pos[i];
			int d = temp.horse.dir;
			int nx = temp.x+dx[d];
			int ny = temp.y+dy[d];
			if(!isRange(nx,ny)||isBlue(nx,ny)) {
				Horse h = horse[temp.x][temp.y].pollFirst();
				horse[temp.x][temp.y].addFirst(new Horse(h.num, reverse(h.dir)));
				d = reverse(d);
				nx = temp.x+dx[d];
				ny = temp.y+dy[d];
			}
			if(!isRange(nx, ny)||isBlue(nx, ny)) {
				
			}else if(isRed(nx,ny)) {
				moveToRed(temp.x,temp.y,nx,ny);
			}else if(isWhite(nx,ny)) {
				moveToWhite(temp.x,temp.y,nx,ny);
			}
		}
		
	}
	private static void moveToWhite(int x, int y, int nx, int ny) {
		while (!horse[x][y].isEmpty()) {
			if(!horse[nx][ny].isEmpty())pos[horse[x][y].peekFirst().num] = new Point(nx, ny, horse[nx][ny].peekFirst());
			else pos[horse[x][y].peekFirst().num] = new Point(nx, ny, horse[x][y].peekFirst());
			horse[nx][ny].addLast(horse[x][y].pollFirst());
		}
		
	}
	private static void moveToRed(int x, int y, int nx, int ny) {
		while (!horse[x][y].isEmpty()) {
			if(!horse[nx][ny].isEmpty())pos[horse[x][y].peekLast().num] = new Point(nx, ny, horse[nx][ny].peekFirst());
			else pos[horse[x][y].peekLast().num] = new Point(nx, ny, horse[x][y].peekLast());
			horse[nx][ny].addLast(horse[x][y].pollLast());
		}
	}
	private static boolean isWhite(int nx, int ny) {
		if(graph[nx][ny] == 0)return true;
		return false;
	}
	private static boolean isRed(int nx, int ny) {
		if(graph[nx][ny] == 1 )return true;
		return false;
	}
	private static int reverse(int d) {
		if(d == 1 || d == 2)return d ==1?2:1;
		if(d == 3 || d == 4)return d ==3?4:3;
		return 0;
	}
	private static boolean isRange(int nx, int ny) {
		if(nx>0&&nx<n+1&&ny>0&&ny<n+1)return true;
		return false;
	}
	private static boolean isBlue(int nx, int ny) {
		if(graph[nx][ny] == 2)return true;
		return false;
	}
	private static boolean isDone() {
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				if(horse[i][j].size()>=4)return true;
			}
		}
		return false;
	}
	
}
