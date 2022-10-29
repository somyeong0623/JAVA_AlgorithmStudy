package week12.boj_14499;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PJH {
	static int[] dr = {-1,0,0,-1,1};//동 서 북 남
	static int[] dc = {-1,1,-1,0,0}; //인덱스 0은 허수
	static int[][] table;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int[] dice = {0,0,0,0,0,0};
		int N, M, x, y, K;
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		table = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(bf.readLine());
		for (int k = 0; k < K; k++) {
			int command = Integer.parseInt(st.nextToken());
			int nr = x+dr[command];
			int nc = y+dc[command];
			if(check(nr,nc)) {
				x = nr;
				y = nc;
				
				dice = move(command,dice);
				if(table[x][y]==0) {
					table[x][y] = dice[5];
				}else {
					dice[5] = table[x][y];
					table[x][y] = 0;
				}
				System.out.println(dice[2]);
			}
		}

	}
	static boolean check(int r,int c) {
		if(r>=0&&c>=0&&r<table.length&&c<table[0].length) {
			return true;
		}
		return false;
	}
	static int[] move(int command,int[] dice) {
		int[] new_dice = new int[6];
		if(command == 1) {//동
			new_dice[0] = dice[0];
			new_dice[1] = dice[5];
			new_dice[2] = dice[1];
			new_dice[3] = dice[2];
			new_dice[4] = dice[4];
			new_dice[5] = dice[3];
			
		}else if(command == 2) {//서
			new_dice[0] = dice[0];
			new_dice[1] = dice[2];
			new_dice[2] = dice[3];
			new_dice[3] = dice[5];
			new_dice[4] = dice[4];
			new_dice[5] = dice[1];
		}else if(command == 3) {//북
			new_dice[0] = dice[2];
			new_dice[1] = dice[1];
			new_dice[2] = dice[4];
			new_dice[3] = dice[3];
			new_dice[4] = dice[5];
			new_dice[5] = dice[0];
		}else {//남
			new_dice[0] = dice[5];
			new_dice[1] = dice[1];
			new_dice[2] = dice[0];
			new_dice[3] = dice[3];
			new_dice[4] = dice[2];
			new_dice[5] = dice[4];
		}
		return new_dice;
	}
	
}
