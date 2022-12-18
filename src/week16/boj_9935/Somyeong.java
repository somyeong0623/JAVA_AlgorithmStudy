package week16.boj_9935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

// 9935. 문자열 폭발 
public class Somyeong {
	static String s,bomb,answer="", temp;
	static int size;
	static ArrayList<Character> list = new ArrayList<Character>();
	static StringBuilder sb;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		s=br.readLine();
		sb=new StringBuilder(s);
//		for(int i=0; i<s.length(); i++) {
//			list.add(s.charAt(i));
//		}
//		
		bomb=br.readLine();
		size= bomb.length();
		
		while(true) {
//			System.out.println("list : "+list);
			if(sb.length()==0) {
				answer="FRULA";
				break;
			}
			if(isBomb()==false) {
				for(int i=0; i<sb.length(); i++) {
					answer+=sb.charAt(i);
				}
				break;
			}
		}
		
		System.out.println(answer);
		
	}
	
	static public boolean isBomb() {
		
		temp = "";
		for(int i=0; i<=sb.length()-size; i++){
			temp="";
			for(int j=i; j<i+size; j++) {
				temp+=sb.charAt(j);
			}
			if(temp.equals(bomb)) {
				int removeIndex=i;
//				for(int j=i; j<i+size; j++) {
//					s.replace(removeIndex, "");
//				}
//				s.replace(temp, "");
				sb= sb.delete(removeIndex,(removeIndex+size));
				return true;
			}
		}
		return false;
		
	}

}
