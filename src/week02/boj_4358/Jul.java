package week02.boj_4358;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

// 생태학
public class Jul {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Map<String, Integer> sp = new HashMap<String, Integer>();
		String spice = null;
		int total = 0;
		while (true) {
			spice  = sc.nextLine();				
			
			if(spice.length() ==0) {
				break;
			}
			
			total++;
			if (sp.containsKey(spice)) {
				sp.put(spice, sp.get(spice)+1);
			}else {
				sp.put(spice, 1);
			}
		}
		String[] sortSpice = new String[sp.keySet().size()];
		int index = 0;
		for (String string : sp.keySet()) {
			sortSpice[index++] = string;
		}
		Arrays.sort(sortSpice);
		for (String string : sortSpice) {
			System.out.printf("%s %.4f%n",string,100*(1.0)*sp.get(string)/total);
		}
	}

}
