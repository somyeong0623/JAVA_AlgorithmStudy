package week02.boj_4358;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 생태학
public class Jul {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		Map<String, Integer> sp = new HashMap<String, Integer>();
		String spice;
		int total = 0;
		while (sc.hasNextLine()) {
			spice  = sc.nextLine();
			if(spice.length() == 0 || spice == null||spice.charAt(0) == ' ') {
				break;
			}
			total++;
			if (sp.containsKey(spice)) {
				sp.put(spice, sp.get(spice)+1);
			}else {
				sp.put(spice, 1);
			}
			
		}
		sc.close();
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
