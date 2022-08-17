package week02.boj_4358;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;


public class PJH{//4358
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		HashMap<String,Integer> map = new HashMap<>();
		int total = 0; 
		String input = bf.readLine();
		while( input != null && input.length() > 0 ) {
			map.put(input,map.getOrDefault(input,0)+1); // 해당 나무 카운트
			total++; //전체 수 증가
			input = bf.readLine();
		}
		Object[] keys = map.keySet().toArray();
		Arrays.sort(keys);
		for(Object key : keys) {
			key = (String)key;
			System.out.println(key+ " "+(String.format("%.4f", (double)map.get(key)*100/total)));
		}
		
	}
}