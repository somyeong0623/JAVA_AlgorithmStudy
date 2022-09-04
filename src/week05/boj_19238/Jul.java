package week05.boj_19238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 스타트 택시
public class Jul {
	static class Point {
		int x, y, count;

		public Point(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
		public void tostring() {
			System.out.println("["+x+" "+y+" "+count+"]");
		}
	}
	static class Destination{
		int x,y,who;
		public Destination(int x,int y,int who) {
			this.x = x;
			this.y = y;
			this.who = who;
		}
		public String print() {
			return "["+x+" "+y+" "+who+"]";
		}
	}
	static Point taxi;
	static int n, m, gas,destiCount;
	static int[][] graph;
	static Queue<Point> queue;
	static PriorityQueue<int[]> pqueue;
	static ArrayList<Destination> destination;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		gas = Integer.parseInt(st.nextToken());
		graph = new int[n + 1][n + 1];
		destination = new ArrayList<>();
		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 1; j < n + 1; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < n+1; i++) {
			graph[i][0] = 1;
		}
		Arrays.fill(graph[0], 1);
		st = new StringTokenizer(br.readLine().trim());
		taxi = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
		for (int i = 2; i < m + 2; i++) {
			st = new StringTokenizer(br.readLine().trim());
			graph[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = i;
			destination.add(new Destination(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
		}
		// 손님 명수 만큼 반복
		for (int i = 0; i < m; i++) {

			pqueue = new PriorityQueue<>(
					(o1, o2) -> o1[0] == o2[0] ? (o1[1] == o2[1] ? o1[2] - o2[2] : o1[1] - o2[1]) : o1[0] - o2[0]);
			isCustomer();

			if(pqueue.isEmpty()) break;
			int[] pre = pqueue.poll();
			graph[pre[1]][pre[2]] = 0;
			if (pre[0] <= gas) {
				gas -= pre[0];
			} else {
				break;
			}
			taxi.x = pre[1];
			taxi.y = pre[2];
			Point dist = isDestination(pre[3]);
			if(dist.x == 0 && dist.y == 0&&dist.count == 0) {
				break;
			}
			if(dist.count <= gas) {
				gas+=dist.count;
			}else {
				break;
			}
			taxi.x = dist.x;
			taxi.y = dist.y;
			destiCount++;
		}
		if(check()&&destiCount == m) {
			System.out.println(gas);
		}else {
			System.out.println(-1);
		}
		
	}

	private static boolean check() {

		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if(graph[i][j] > 1) {
					return false;
				}
			}
		}
		return true;
		
	}

	private static Point isDestination(int target) {
		queue = new ArrayDeque<>();
		queue.offer(taxi);
		boolean[][] visited = new boolean[n + 1][n + 1];
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
		int[] dx = new int[] { 0, 1, 0, -1 };
		int[] dy = new int[] { 1, 0, -1, 0 };
		for (int i = 0; i < destination.size(); i++) {
			if(destination.get(i).x == taxi.x && destination.get(i).y == taxi.y && destination.get(i).who == target) {
				destination.remove(i);
				return new Point(taxi.x,taxi.y,0);
			}
		}
		while (!queue.isEmpty()) {
			Point temp = queue.poll();
			int x = temp.x;
			int y = temp.y;
			visited[x][y] = true;
			for (int i = 0; i < 4; i++) {
				int nx = x+dx[i];
				int ny = y + dy[i];
				if(nx<0||ny<0||nx>=n+1||ny>=n+1||visited[nx][ny]||graph[nx][ny] == 1) continue;
				for(int j = 0;j < destination.size();j++) {
					if(destination.get(j).x == nx && destination.get(j).y == ny && destination.get(j).who == target) {
						destination.remove(j);
						return new Point(nx, ny, temp.count+1);
					}
				}
				visited[nx][ny] = true;
				queue.offer(new Point(nx, ny, temp.count+1));
			}
		}
		return new Point(0, 0, 0) ;
	}

	private static void isCustomer() {
		queue = new ArrayDeque<Point>();
		queue.offer(taxi);
		boolean[][] visited = new boolean[n + 1][n + 1];
		for (int i = 0; i < visited.length; i++) {
			Arrays.fill(visited[i], false);
		}
		int[] dx = new int[] { 0, 1, 0, -1 };
		int[] dy = new int[] { 1, 0, -1, 0 };
		if(graph[taxi.x][taxi.y] > 1) {
			pqueue.offer(new int[] {0,taxi.x,taxi.y,graph[taxi.x][taxi.y]});
			return;
		}
		while (!queue.isEmpty()) {
			Point temp = queue.poll();
			int x = temp.x;
			int y = temp.y;
			visited[x][y] = true;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || nx >= n + 1 || ny < 0 || ny >= n + 1 || visited[nx][ny] || graph[nx][ny] == 1)
					continue;
				if (graph[nx][ny] > 1) {
					pqueue.offer(new int[] { temp.count + 1, nx, ny, graph[nx][ny] });
				}
				visited[nx][ny] = true;
				queue.offer(new Point(nx, ny, temp.count + 1));
			}
		}
	}

}
