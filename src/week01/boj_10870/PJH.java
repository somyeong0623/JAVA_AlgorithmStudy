package week01.boj_10870;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PJH {
	static int[] table;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());
		
		table = new int[input+2];//0넣어도 에러 안나오게
		table[0] = 0;
		table[1] = 1;
		for(int i = 2; i < table.length;i++) {
			table[i] = table[i-1]+table[i-2];
		}
		System.out.println(table[input]);
		
	}

}