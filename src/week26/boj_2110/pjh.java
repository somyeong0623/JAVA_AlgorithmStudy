import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int N,C;
	static int[] data;
	static int min_data,max_data;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		data = new int[N];
		for(int i = 0 ; i < N ; i ++) {
			data[i] = Integer.parseInt(bf.readLine());
		}
		
		Arrays.sort(data);
		
		int last = (data[N-1] - data[0])/(C-1);
		int first = 0;
		int mid = 0;
		
		while((last-first)>1) {
			mid = (last+first)/2;
			boolean result = check(mid);
			if(result) { //차이를 늘려야 한다.
				//System.out.println("len 증가 " + mid);
				first = mid;
			}else {
				//System.out.println("len 감소 " + mid);
				last = mid;
			}
		}
		//System.out.println("검증시작" + first + " " +last);
		if(check(last)) { 
			System.out.println(last);
		}else {
			System.out.println(first);
		}
	}
	
	static boolean check(int len) {
		int count = 1; //제일 처음시작
		int index = 0;
		for(int i = 1;i<data.length;i++) {
			if((data[i]-data[index]) >= len){
				index = i;
				count++;
			}
			if((C-count)>=(data.length-i)) { // 남은 와이파이 > 뒤에 남은 집 수
				return false; //차이를 줄인다.
			}
		}
//		if(C>count) {
//			return false;
//		}
		return true; //차이를 늘린다.
	}


	
}
