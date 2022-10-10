package week09.swea_1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jinsu_dfs {

	static int[] voucher;
	static int[] year;
	static boolean[] check;
	static int min = Integer.MAX_VALUE;	//답
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());	//tc
		
		for(int tc=1; tc<= T; tc++) {
			
			voucher = new int[4];	//4가지 이용권
			year = new int[13];		//1월~12월
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				voucher[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=12; i++) {
				year[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(1, 0);
			min = Math.min(min, voucher[3]);
		
			System.out.println("#"+tc+" "+min);
		}
		
		
	}

	private static void dfs(int idx, int sum) {
		
		//가지치기
		if(min < sum) {
			return;
		}
		
		if(idx > 12) {
			min = Math.min(min, sum);
			return;
		}
		
		
		if(year[idx] == 0) {
			dfs(idx+1, sum);
			return;
		}
		
		
		//1일 이용권
		dfs(idx+1, sum+year[idx]*voucher[0]);
		
		//한달 이용권
		dfs(idx+1, sum+voucher[1]);
		
		//3달 이용권
		dfs(idx+1, sum+voucher[2]);
		
	}

}
