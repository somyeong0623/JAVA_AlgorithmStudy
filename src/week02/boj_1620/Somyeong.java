package week02.boj_1620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//1620.나는야 포켓몬 마스터 이다솜 
/*
 * <Integer, String>형을 저장하는 hashmap 하나와 <String,Integer>형을 저장하는 hashmap하나에 각각
 * 포켓몬의 (번호, 이름) (이름,번호) 정보들을 저장한다.
 * 문제가 숫자로 주어지면 -> 첫번째 hashmap
 * 문제가 문자열로 주어지면 -> 두번째 hashmap을 통해 정답을 찾는다. 
 * 
 */
public class Somyeong {
	
	static int n,m;
	static String arr[];
	public static void main(String[] args) throws IOException{
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		arr=new String[n+1];
		HashMap<Integer, String> map1= new HashMap<>();//숫자 - 이름 
		HashMap<String,Integer> map2= new HashMap<>();//이름 - 숫
		
		for(int i=1; i<=n; i++) {
			String s= br.readLine();
			map1.put(i,s);
			map2.put(s, i);
		}
		
		//문제에 대한 답 찾기 
		for(int i=0; i<m; i++) {
			String problem=br.readLine();
			if(isNumeric(problem)) {
				int num=Integer.parseInt(problem);
				System.out.println(map1.get(num));
			}else {
				System.out.println(map2.get(problem));
			}
		}
		
	}
	private static boolean isNumeric(String s) { //숫자판별하는 함수 
		try {
			Double.parseDouble(s);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}

}
