package week02.boj_1620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Jinsu {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashMap<String, Integer> poketmon_str = new HashMap<>();
		HashMap<Integer, String> poketmon_int = new HashMap<>();
		
		for (int i=1; i<=N; i++) {
			String name = br.readLine();
			poketmon_str.put(name, i);
			poketmon_int.put(i, name);
		}
		
		for (int i=0; i<M; i++) {
			String s = br.readLine();
			if (isNum(s)) {	//숫자일 때
				sb.append(poketmon_int.get(Integer.parseInt(s)));
				sb.append("\n");
			}
			else { //포켓몬 이름일 때
				sb.append(poketmon_str.get(s));
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	private static boolean isNum(String str) {
		for(int i=0; i<str.length(); i++) {
			if(!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	

}
