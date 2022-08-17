package week02.boj_1620;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;


public class PJH {
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		HashMap<Integer,String> map_int = new HashMap<>();
		HashMap<String,Integer> map_str = new HashMap<>();
		
		
		String[] tmp = bf.readLine().split(" ");
		int N = Integer.parseInt(tmp[0]);
		int M = Integer.parseInt(tmp[1]);
		for(int i =1; i < N+1; i++) {
			String input = bf.readLine();
			map_int.put(i,input);
			map_str.put(input,i);
		}
		for(int i = 0; i < M ; i ++) {
			String input = bf.readLine();
			if(check(input)) {
				sb.append(map_int.get(Integer.parseInt(input))+"\n");
			}else {
				sb.append(map_str.get(input)+"\n");
			}
		}
		System.out.println(sb);
	}
	
	static boolean check(String a) {
		try {
			Double.parseDouble(a);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
}
