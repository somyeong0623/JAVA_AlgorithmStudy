package week16.boj_9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jul {
	static String before,bomb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		before = br.readLine();
		bomb = br.readLine();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < before.length(); i++) {
			char temp = before.charAt(i);
			sb.append(temp);
			if(sb.length() >= bomb.length()) {
				boolean boom = true;
				for (int j = 0; j < bomb.length(); j++) {
					char chr1 = sb.charAt(sb.length()-bomb.length() + j);
					if(chr1 != bomb.charAt(j)) {
						boom = false;
						break;
					}
				}
				if(boom) {
					sb.delete(sb.length()-bomb.length(), sb.length());
				}
			}
		}

		if(sb.length() > 0)System.out.print(sb.toString());
		else System.out.println("FRULA");

	}

}
