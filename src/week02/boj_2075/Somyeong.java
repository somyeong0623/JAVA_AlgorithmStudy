package week02.boj_2075;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//2075. N번째 큰 수 
public class Somyeong {
	static int n;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq =new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i=0; i<n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int x= Integer.parseInt(st.nextToken());
				pq.add(x);
			}
		}
		for(int i=0; i<n; i++) {
			if(i==n-1)
				answer=pq.poll();
			else
				pq.remove();
		}
		System.out.println(answer);
	}
}
