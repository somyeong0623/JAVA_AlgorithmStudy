package week02.boj_1783;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//1783. 병든 나이트  (몰라서 구글링함) 
//https://lipcoder.tistory.com/94

public class Somyeong {
	static int n,m;
	static int r,c;
	static int answer=0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
	
		if(n==1)
			answer=1;
		else if(n==2)
			answer=Math.min(4,(m+1)/2);
		else if(m<7)
			answer=Math.min(4,m);
		else
			answer=m-7+5;
		
		System.out.println(answer);
		
		
	}

}
