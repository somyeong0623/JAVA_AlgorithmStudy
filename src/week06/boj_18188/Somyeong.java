package week06.boj_18188;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

//18188. 다오의 데이트	
public class Somyeong {
	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int H, W;
	static char arr[][];
	static String way[][];
	static char dir[][];
	static int n;
	static int r, c; // 다오의 위치
	static int z_r, z_c; // 디즈니의 위치
	static HashMap<Character, Point> map = new HashMap<Character, Point>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		arr = new char[H + 1][W + 1];
		way = new String[H + 1][W + 1];
		map.put('W', new Point(-1, 0));
		map.put('A', new Point(0, -1));
		map.put('S', new Point(1, 0));
		map.put('D', new Point(0, 1));

		String s = "";

		for (int i = 1; i <= H; i++) {
			s = br.readLine();
			for (int j = 1; j <= W; j++) {
				way[i][j] = "";
				arr[i][j] = s.charAt(j - 1);
				if (arr[i][j] == 'D') {
					r = i;
					c = j;
				}
				if (arr[i][j] == 'Z') {
					z_r = i;
					z_c = j;
				}
			}
		}

		n = Integer.parseInt(br.readLine());
		dir = new char[n][2];
		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			dir[i][0] = str[0].charAt(0);
			dir[i][1] = str[1].charAt(0);
		}
		
//		System.out.println("===dir===");
//		for(int i=0; i<n; i++) {
//			System.out.println(dir[i][0] + " "+dir[i][1]);
//		}
		// bfs
		Queue<Point> queue = new ArrayDeque<Point>();
		queue.offer(new Point(r, c));
		boolean meet = false;
		
		loop: while (!queue.isEmpty()) {
			Point cur = queue.poll();
			int idx=way[cur.r][cur.c].length();
			if(idx==n) // 이미 만날수없다는 사실은 확정났고, queue에 남아있는 point 처리해야하므로 break로 끝내
				break;
//			System.out.println("idx: "+idx);

			for (int i = 0; i < 2; i++) {
				char direction = dir[idx][i];

//				System.out.println("direction: " + direction);
				int nr = cur.r + map.get(direction).r;
				int nc = cur.c + map.get(direction).c;

				if (nr >= 1 && nc >= 1 && nr <= H && nc <= W && arr[nr][nc] != '@') {
					way[nr][nc] = way[cur.r][cur.c] + direction;
					queue.offer(new Point(nr, nc));
					
					if (arr[nr][nc] == 'Z') {
						meet = true; // 만날수 있음
						break loop;
					}
					
				}

			}
		}

//		for (int i = 1; i <= H; i++) {
//			for (int j = 1; j <= W; j++) {
//				System.out.print(way[i][j] + " ");
//			}
//			System.out.println();
//		}

		if (meet == false) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
			System.out.println(way[z_r][z_c]);
		}

	}

}
