package week02.boj_15686;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Jinsu {
	static int N, M, result;
	static int[][] map;
	static ArrayList<Point> home, chicken;	//집의 좌표값과 치킨집의 좌표값 저장
	static boolean[] open;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][N];
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
				
				if (map[i][j] == 1) //집이면
					home.add(new Point(i, j));		
				
				else if(map[i][j] == 2)	//치킨집이면
					chicken.add(new Point(i, j));	
			}
		}
		
		result = Integer.MAX_VALUE;
		open = new boolean[chicken.size()];
		dfs(0, 0);
		System.out.println(result);
		
	}

	private static void dfs(int cnt, int start) {
		if (cnt == M) {
			int ans = 0;
			for (int i=0; i<home.size(); i++) {
				int tmp = Integer.MAX_VALUE;
				for (int j=0; j<chicken.size(); j++) {
					if(open[j]) {
						int distance = Math.abs(home.get(i).x- chicken.get(j).x) + Math.abs(home.get(i).y - chicken.get(j).y);
						tmp = Math.min(distance, tmp);	//최소거리 저장
					}
				}
				ans += tmp;	// 탐색완료 후 최소거리 저장
			}
			result = Math.min(ans, result); 
			return;
		}
		
		for (int i= start; i < chicken.size(); i++) {
			open[i] = true;
			dfs(cnt+1, i+1);
			open[i] = false;
		}
	}

}
