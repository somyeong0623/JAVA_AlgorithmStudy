package week12.boj_12904;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jul {
	static String s,t;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s= br.readLine().trim();
		t = br.readLine().trim();
		makeString(s,t);
		System.out.println(flag?1:0);
	}
	private static void makeString(String ss, String ts) {
		if(ss.length() == ts.length()) {
			System.out.println(ss+" "+ts);
			if(ss.equals(ts))flag = true;
			return;
		}
		String temp = "";
		if(ts.charAt(ts.length()-1)=='A') {
			for (int i = 0; i < ts.length()-1; i++) {
				temp+=ts.charAt(i);
			}
		}else if(ts.charAt(ts.length()-1)=='B') {
			for (int i = ts.length()-2; i > -1; i--) {
				temp+=ts.charAt(i);
			}
		}
		makeString(ss, temp);
	}

}
