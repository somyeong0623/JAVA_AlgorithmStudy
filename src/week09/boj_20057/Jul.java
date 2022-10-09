package week09.boj_20057;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jul {
	static int n,up=1,left=1,down=2,right=2,result;
	static int[][] graph;
	static int x,y;
	static boolean[][] visited;
	static int[] dx = {-1,-2,1,2,-1,1,-1,1,0,0,0,0};
	static int[] dy = {0,0,0,0,1,1,-1,-1,-2,-1,1,2};
	static int[][] rate = {
			{7,2,7,2,1,1,10,10,5,-1,-1,-1},
			{-1,-1,-1,5,1,10,1,10,2,7,7,2},
			{7,2,7,2,10,10,1,1,-1,-1,-1,5},
			{-1,5,-1,-1,10,1,10,1,2,7,7,2}
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		graph = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		x = n/2;
		y = n/2;
		boolean flag =false;
		while (x>-1&&y>-1) {
			int index = y;
			for (int i = index-1; i > index-1-up; i--) {
//				System.out.println("-------------------------------");
//				for (int q = 0; q < n; q++) {
//					System.out.println(Arrays.toString(graph[q]));
//				}
//				System.out.println("------------"+result+"-----------------");
				if(x==0 && y == 0) {
					flag = true;
					break;
				}
				y--;
				tonado(x,y,0);
			}
			up+=2;
			if(flag) break;
			// left
			index = x;
			for (int i = index+1; i < index+1+left; i++) {
//				System.out.println("-------------------------------");
//				for (int k = 0; k < n; k++) {
//					System.out.println(Arrays.toString(graph[k]));
//				}
//				System.out.println("------------"+result+"-----------------");
				x++;
				tonado(x,y,1);

			}
			left+=2;
			// down
			index = y;
			for (int i = index+1; i < index+1+down; i++) {
//				System.out.println("-------------------------------");
//				for (int k = 0; k < n; k++) {
//					System.out.println(Arrays.toString(graph[k]));
//				}
//				System.out.println("------------"+result+"-----------------");
				y++;
				tonado(x,y,2);

			}
			down+=2;
			// right
			index = x;
			for (int i = index-1; i > index-1-right; i--) {
//				System.out.println("-------------------------------");
//				for (int k = 0; k < n; k++) {
//					System.out.println(Arrays.toString(graph[k]));
//				}
//				System.out.println("------------"+result+"-----------------");
				x--;
				tonado(x,y,3);

			}
			right+=2;
		}
//		System.out.println(x+" "+y+" 원점 도착");
		System.out.println(result);
	}
	private static void tonado(int x2, int y2, int d) {
//		System.out.println(x2+ " "+ y2);
		int alpha = 0;
		int val = graph[x2][y2];
		for (int i = 0; i < 12; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(rate[d][i]<0)continue;
			int remain = val * rate[d][i] / 100;
//			System.out.println(i +" "+remain);
			if(nx<0||ny<0||nx>=n||ny>=n) {
				result += remain;
				graph[x2][y2] -=remain;
			}else {
				alpha += remain;
				graph[nx][ny] += remain;
			}
		}
//		System.out.println("alpha : "+alpha);
		if(d == 0) {
			if(y2-1<0) result += graph[x2][y2] - alpha;
			else graph[x2][y2-1] += graph[x2][y2] - alpha;
		}else if(d == 1) {
			if(x2+1>=n) result += graph[x2][y2] - alpha;
			else graph[x2+1][y2] += graph[x2][y2] - alpha;
		}else if(d == 2) {
			if(y2+1>=n) result += graph[x2][y2] - alpha;
			else graph[x2][y2+1] += graph[x2][y2] - alpha;
		}else if(d == 3) {
			if(x2-1<0) result += graph[x2][y2] - alpha;
			else graph[x2-1][y2] += graph[x2][y2] - alpha;
		}
		graph[x2][y2] = 0;
	}

}
