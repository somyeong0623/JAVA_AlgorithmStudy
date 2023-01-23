package week21.boj_5549;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jul {
	static class Map{
		int jungle,ocean,ice;
		public Map(int jungle, int ocean, int ice) {
			super();
			this.jungle = jungle;
			this.ocean = ocean;
			this.ice = ice;
		}
		@Override
		public String toString() {
			return "Map [jungle=" + jungle + ", ocean=" + ocean + ", ice=" + ice + "]";
		}
	}
	static int n,m,k;
	static char[][] graph;
	static Map[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		graph = new char[n+1][m+1];
		map = new Map[n+1][m+1];
		for (int i = 0; i < n+1; i++) {
			for (int j = 0; j < m+1; j++) {
				map[i][j] = new Map(0, 0, 0);
			}
		}
		for (int i = 1; i < n+1; i++) {
//			st = new StringTokenizer(br.readLine());
			String line = br.readLine();
			for (int j = 1; j < m+1; j++) {
				graph[i][j] = line.charAt(j-1);
//				map[i][j] = new Map(0, 0, 0);
				if(graph[i][j]=='J')map[i][j].jungle++;
				else if(graph[i][j] == 'O')map[i][j].ocean++;
				else if(graph[i][j] == 'I')map[i][j].ice++;
				if(j!=1) {
					map[i][j].ice += map[i][j-1].ice;
					map[i][j].jungle+= map[i][j-1].jungle;
					map[i][j].ocean+= map[i][j-1].ocean;
				}
			}
			for (int j = 1; j < m+1; j++) {
				if(i!=1) {
					map[i][j].ice += map[i-1][j].ice;
					map[i][j].ocean += map[i-1][j].ocean;
					map[i][j].jungle += map[i-1][j].jungle;
				}
			}
			
//			System.out.println(Arrays.toString(map[i]));
		}
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			sb.append(map[c][d].jungle -map[a-1][d].jungle -map[c][b-1].jungle +map[a-1][b-1].jungle);
			sb.append(" ");
			sb.append(map[c][d].ocean -map[a-1][d].ocean-map[c][b-1].ocean+ map[a-1][b-1].ocean);
			sb.append(" ");
			sb.append(map[c][d].ice-map[a-1][d].ice-map[c][b-1].ice+ map[a-1][b-1].ice);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
