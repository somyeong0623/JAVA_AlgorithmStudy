package week02.boj_4358;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Jinsu {	//이유는 모르겠지만 Scanner 쓰면 NosuchElement 런타임에러 뜸
	static int cnt=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> hm = new HashMap<>();
		
		String s = br.readLine();
		
		while (true) {
			
			hm.put(s, hm.getOrDefault(s, 0)+1);
			cnt++;
			
			s = br.readLine();
			if (s == null || s.length() == 0) {
				break;
			}
		}
		
		Object[] key = hm.keySet().toArray();
		Arrays.sort(key);
		
		for (Object object : key) {
			String str = (String) object;
			
			int count = hm.get(str);
			
			double result = (double)(count)/(double)cnt * 100;
			
			System.out.printf("%s %.4f", str, result);
			System.out.println();
		}
		
	
		
	}

}
