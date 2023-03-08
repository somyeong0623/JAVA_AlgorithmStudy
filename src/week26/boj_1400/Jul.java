package week26.boj_1400;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jul {
	static boolean[][] rainbow;
	static char[][] graph;
	static int n,m;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=  new StringTokenizer(br.readLine().trim());
			n = Integer.valueOf(st.nextToken());
			m = Integer.valueOf(st.nextToken());
			if(n == 0 && m == 0) {
//				br.readLine().trim();
				break;
			}
//			System.out.println(n+" "+m);
			graph = new char[n][m];
			rainbow = new boolean[10][];
			int[] a = new int[2];
			int rainbowCount = -1;
			for (int i = 0; i < n; i++) {
				String t = br.readLine().trim();
				for (int j = 0; j < m; j++) {
					graph[i][j] = t.charAt(j);
					if(graph[i][j]-'0'>=0&&graph[i][j]-'0'<=10)rainbowCount = Math.max(graph[i][j]-'0', rainbowCount);
					if(graph[i][j]=='A') {
						a[0]=i;
						a[1]=j;
					}
				}
			}
			if(rainbowCount == -1) {
//				System.out.println("입력없음");
				search(a);
				br.readLine();
				continue;
			}else {
				for (int i = 0; i < rainbowCount+1; i++) {
					st = new StringTokenizer(br.readLine().trim());
					int b = Integer.valueOf(st.nextToken());
					char c = st.nextToken().charAt(0);
					int d = Integer.valueOf(st.nextToken());
					int e = Integer.valueOf(st.nextToken());
//					System.out.println(b+" "+c+" "+d+" "+e);
					rainbow[b] = new boolean[e+d];
					if(c == '-') {
						for (int j = d; j < e+d; j++) {
							rainbow[b][j] = true;
						}
					}else {
						for (int j = 0; j < e; j++) {
							rainbow[b][j] = true;
						}
					}
//					System.out.println(Arrays.toString(rainbow[b])+" "+rainbow[b].length);
				}
				search(a);
				br.readLine().trim();
				
			}
		}
	}
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,1,0,-1};
	private static void search(int[] a) {
//		System.out.println(Arrays.toString(a));
		int[][] visited = new int[n][m];
		PriorityQueue<int[]> pqueue = new PriorityQueue<>((o1,o2)->o1[2]-o2[2]);
		pqueue.add(new int[] {a[0],a[1],0});
		while (!pqueue.isEmpty()) {
			int[] temp = pqueue.poll();
//			System.out.println(Arrays.toString(temp));
			if(visited[temp[0]][temp[1]]!=0)continue;
			if(graph[temp[0]][temp[1]] == 'B') {
				System.out.println(temp[2]);
				return;
			}
			visited[temp[0]][temp[1]] = temp[2];
			for (int i = 0; i < 4; i++) {
				int nx = temp[0]+dx[i];
				int ny = temp[1]+dy[i];
//				System.out.println(nx+" "+ny);
				if(nx<0||ny<0||nx>=n||ny>=m||graph[nx][ny] == '.'||visited[nx][ny]>0||graph[nx][ny] =='A')continue;
				if(graph[nx][ny] == '#'||graph[nx][ny] == 'B') {
					pqueue.add(new int[] {nx,ny,temp[2]+1});
				}else if((dx[i] == 0 && !rainbow[graph[nx][ny]-'0'][(temp[2])%rainbow[graph[nx][ny]-'0'].length]) ||
						dx[i] != 0 && rainbow[graph[nx][ny]-'0'][(temp[2])%rainbow[graph[nx][ny]-'0'].length]) {
//					System.out.println(Arrays.toString(rainbow[graph[nx][ny]-'0']));
					pqueue.add(new int[] {nx,ny,temp[2]+1});
				}else {
					pqueue.add(new int[] {temp[0],temp[1],temp[2]+1});
					visited[temp[0]][temp[1]] = 0;
				}
			}
		}
		System.out.println("impossible");
		return ;
	}
}


