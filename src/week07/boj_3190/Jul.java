package week07.boj_3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Jul {
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	static int n,l,k,d,time;
	static int[][] graph;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int[] timeline;
	static char[] directline;
	static Deque<Point> snake;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim());
		l = Integer.parseInt(st.nextToken());
		graph = new int[n][n];
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine().trim());
			graph[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		st = new StringTokenizer(br.readLine().trim());
		k = Integer.parseInt(st.nextToken());
		timeline = new int[k];
		directline = new char[k];
		snake = new ArrayDeque<>();
		snake.add(new Point(0, 0));
		graph[0][0] = -1;
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine().trim());
			timeline[i] = Integer.parseInt(st.nextToken());
			directline[i] = st.nextToken().charAt(0);
		}
		int index = 0;
		time = 0;
		while(true) {
			Point temp = snake.peekLast();
			if (index < k && time == timeline[index]) {
				if(directline[index] == 'D') d++;
				else d--;
				if(d==4) d=0;
				if(d==-1) d=3;
				index++;
			}
			int nx = temp.x+dx[d];
			int ny = temp.y+dy[d];
			if(nx<0||ny<0||nx>=n||ny>=n||graph[nx][ny]==-1) break;
			if(graph[nx][ny]==1) {
			}else {
				Point t = snake.pollFirst();
				graph[t.x][t.y]= 0; 
			}
			graph[nx][ny] = -1;
			snake.addLast(new Point(nx, ny));
			time++;
		}
		System.out.println(time+1);
	}

}
