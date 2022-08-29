package week04.boj_14502;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PJH {
	static int[][] table;
	static ArrayList<Point1> B_list;
	static ArrayList<Point1> N_list;
	static Point1[] target;
	static int answer;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		B_list = new ArrayList<>();
		N_list = new ArrayList<>();
		table = new int[N][M];
		target = new Point1[3];
		answer = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());

				if (table[i][j] == 2) {
					B_list.add(new Point1(i, j));
				} else if (table[i][j] == 0) {
					N_list.add(new Point1(i, j));
				}
			}
		}
		
		perm(0,0);
		System.out.println(answer);
	}

	static void perm(int cnt, int flag) {
		if (cnt == 3) {
			//System.out.println(Arrays.toString(target));

			for(Point1 tmp:target) {
				table[tmp.r][tmp.c] = 1;
			}
			
			check_map();
			
			for(Point1 tmp:target) {
				table[tmp.r][tmp.c] = 0;
			}
			
			return;
		}

		for(int i = 0 ; i < N_list.size();i++) {
			if((flag & 1<<i)!=0) {
				continue;
			}
			target[cnt] = N_list.get(i);
			perm(cnt+1,flag|1<<i);
		}

	}

	static void check_map() {
		int[][] tmp_map = new int[table.length][table[0].length];
		int cnt = 0;
		int[] rotate_r = {-1,0,1,0}; 
		int[] rotate_c = {0,1,0,-1}; 
		
		for(int i = 0; i <tmp_map.length;i++) {
			for(int j = 0 ; j < tmp_map[0].length;j++) {
				tmp_map[i][j] = table[i][j];
				if(tmp_map[i][j]== 0) {
					cnt++;
				}
			}
		}
		
		Queue<Point1> q = new LinkedList<>();
		for(Point1 tmp : B_list) {
			q.add(tmp);
		}
		
		while(!q.isEmpty()) {
			Point1 tmp_p = q.poll();
			for(int i = 0 ; i < 4;i++) {
				int new_r = tmp_p.r + rotate_r[i];
				int new_c = tmp_p.c + rotate_c[i];
				if(new_r<0||new_r>=tmp_map.length||new_c<0||new_c>=tmp_map[0].length) {
					continue;
				}
				if(tmp_map[new_r][new_c]==0) {
					q.add(new Point1(new_r,new_c));
					tmp_map[new_r][new_c] = 2;
					cnt--;
				}
			}
		}
		answer = Math.max(answer, cnt);
		
	}
	
	
}

class Point1 {
	int r;
	int c;

	public Point1(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Point1 [r=" + r + ", c=" + c + "]";
	}
	
}
