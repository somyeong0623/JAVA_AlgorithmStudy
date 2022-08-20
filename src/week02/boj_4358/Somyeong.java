package week02.boj_4358;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

//4358. 생태학 
public class Somyeong {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> hashmap = new HashMap<>();
		
		int count=0;
		String line;
		while(true) {
			line=br.readLine();
			if(line==null || line.length()==0)
				break;
			count++;
			if(hashmap.containsKey(line)) { //있는경우 
				hashmap.put(line,hashmap.get(line)+1);
			}else {
				hashmap.put(line, 1);
			}
			
		}
		
		Object[] mapkey= hashmap.keySet().toArray();
		Arrays.sort(mapkey);
		
		for(Object key : mapkey) {
			System.out.print(key+" ");
			System.out.println(String.format("%.4f", (double)hashmap.get(key)/(double)count*100));

		}
	}
}
