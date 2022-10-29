package week12.boj_12904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//12904. A와 B
/*
 * 
 * 1. 문자열의 뒤에 A를 추가한다 -> (반대버전) 문자열 맨뒤 A를 제거한다. 
 * 2. 문자열을 뒤집고 뒤에 B를 추가한다 -> (반대버전) 문자열 맨뒤의 B를 제거하고 뒤집는다. 
 * 
 * 
 * 
 */
public class Somyeong {
	static String s,t;
	static int answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s=br.readLine();
		t=br.readLine();
		dfs(t);
		System.out.println(answer);
		
	}
	
	//t에서 s로 가는 방식 (DFS) 
	public static void dfs(String t) {
//		System.out.println("t: "+t);
		if(s.length()==t.length()) { // 문자열 t의 길이가 문자열 s의 길이와 같아지면 같은문자인지 확인하고 리턴 
			if(s.equals(t)) answer=1;
			return;
		}
		
		if(t.charAt(t.length()-1)=='A') // 문자열 t의 맨뒤가 A이면 제거 
			dfs(t.substring(0,t.length()-1));
		if(t.charAt(t.length()-1)=='B') { // 문자열 t의 맨뒤가 B이면 제거하고 뒤집기 
			String newT=t.substring(0,t.length()-1);
			StringBuffer sb = new StringBuffer(newT); // String에는 바로 뒤집는 함수가 없어서 StirngBuffer의 함수를 이용하여 뒤집은 뒤 String으로 반환 
			String reverseNewT=sb.reverse().toString(); 
			dfs(reverseNewT);
		}
	}
}
