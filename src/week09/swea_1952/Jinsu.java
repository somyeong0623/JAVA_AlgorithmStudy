package week09.swea_1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jinsu {

	static int[] voucher;
	static int[] year;
	static int[] d;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());	//tc
		
		for(int tc=1; tc<= T; tc++) {
			
			voucher = new int[4];	//4가지 이용권
			year = new int[13];		//1월~12월
			d = new int[13];
			
			Arrays.fill(d, 0);
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				voucher[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=12; i++) {
				year[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1; i<=12; i++) {
				
				//1일 이용권, 1달 이용권
				d[i] = Math.min(d[i-1]+ year[i]*voucher[0], d[i-1]+voucher[1]);
				
				
				//3달 이용권
				if(i>=3) {
					d[i] = Math.min(d[i], d[i-3]+voucher[2]);
				}
			}
			
			System.out.println("#"+tc+" "+Math.min(d[12], voucher[3]));
		}
		
		
	}

}
