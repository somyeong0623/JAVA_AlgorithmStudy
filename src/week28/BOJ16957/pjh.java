import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	static StringTokenizer st;
	static int N,M;
	static int[] dr = {-1,0,1,0,-1,1,1,-1};
	static int[] dc = {0,1,0,-1,1,1,-1,-1};
	
	static int[] table;
	static int[] group;
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] cnt_table = new int[N][M];
		table = new int[N*M];
		group = new int[N*M];
		for(int i =0 ; i < group.length;i++) {
			group[i] = i;
		}
		for(int i = 0 ; i < N ;i++) {
			st = new StringTokenizer(bf.readLine());
			
			int[] tmp = new int[M];
			cnt_table[i] = tmp;
			for(int j = 0 ; j < M ;j++) {
				table[M*i+j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int k = 0 ; k < table.length ; k++) {
			int r = k/M;
			int c = k%M;
			
			int min_d_index = k;
			
			for(int i = 0 ; i < 8 ; i++) {
				int tr = r+dr[i];
				int tc = c+dc[i];
				int target = tr*M+tc;
				if(checkAble(tr,tc) && table[min_d_index] > table[target]) {
					min_d_index = target;
				}
			}
			union(min_d_index,k);
		}
		table = new int[N*M];
		for(int i = 0 ; i < table.length;i++) {
			table[group[find(group[i])]] += 1;
		}
		
		for(int i = 0 ; i < N;i++) {
			for(int j = 0 ; j < M ;j++) {
				System.out.print(table[i*M+j]+" ");
			}
			System.out.println();
		}

	}
	public static int find(int a) {
		if(group[a] == a) return a;
		return group[a] = find(group[a]);
	}
	public static void union(int a,int b) {
		a = find(a);
		b = find(b);
		if(table[a]>table[b]) group[a] = b;
		else group[b] = a;
	}
	static boolean checkAble(int r,int c) {
		if(r>=0 && c>=0 && r<N && c<M) {
			return true;
		}
		return false;
	}


}

/*
5 4
35 41 77 71 
73 88 62 45 
25 8 18 89 
24 31 15 99 
74 10 43 85 

//오답 : 마지막 for문 안넣을 때
3 0 0 0 
0 0 0 0 
0 13 1 0 
0 0 0 0 
0 3 0 0 

//정답
3 0 0 0 
0 0 0 0 
0 14 0 0 
0 0 0 0 
0 3 0 0 



*/