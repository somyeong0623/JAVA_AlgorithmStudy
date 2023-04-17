package week31.boj16724;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;


public class Jul {
	static char[][] graph;
	static int[][] visited;
	static int count=1,a,b;
	static HashMap<Character, int[]> direct;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		graph = new char[a][b];
		visited = new int[a][b];
//		for (int i = 0; i < a; i++) {
//			Arrays.fill(visited[i], -1);
//		}
		direct = new HashMap<>();
		direct.put('R', new int[] {0,1});
		direct.put('U', new int[] {-1,0});
		direct.put('D', new int[] {1,0});
		direct.put('L', new int[] {0,-1});
		for (int i = 0; i < a; i++) {
			String line = br.readLine();
			for (int j = 0; j < b; j++) {
				graph[i][j] = line.charAt(j);
			}
		}
		for (int i = 0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				if(visited[i][j] != 0)continue;
				dfs(i,j);
			}
		}
		System.out.println(count-1);
		for (int i = 0; i < a; i++) {
			System.out.println(Arrays.toString(visited[i]));
		}
	}
	private static void dfs(int x, int y) {
		int[] d = direct.get(graph[x][y]);
//		System.out.println(graph[x][y]+" "+x+" "+y+" : "+ Arrays.toString(d));
		
		int nx = x + d[0];
		int ny = y + d[1];
		if(visited[nx][ny] != 0) {
			if(visited[nx][ny] == count) count++;
			visited[x][y] = visited[nx][ny];
			return ;
		}
		visited[x][y] = count;
		dfs(nx,ny);
		visited[x][y] = visited[nx][ny];

	}

}

//3 9
//DLLLRRRRD
//DRLUDRURD
//RRRURRULL
//[1, 1, 1, 1, 2, 2, 2, 2, 2]
//[1, 3, 3, 1, 2, 2, 2, 2, 2]
//[1, 1, 1, 1, 2, 2, 2, 2, 2]


//[1, 1, 1, 1, 2, 2, 2, 2, 2]
//[1, 3, 3, 1, 4, 2, 2, 2, 2]
//[1, 1, 1, 1, 4, 2, 2, 2, 2]
