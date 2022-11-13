package week13.boj_1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//1062. 가르침 
public class Somyeong {
	static int N,K;
	static int answer;
	static String[] arr;
	static Set<Character> set;
	static boolean selected[];
	
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		arr = new String[N];
		
		for(int i=0; i<N; i++) {
			arr[i]=br.readLine();
		}
		
		if(K<5) { // K가 5보다 작으면 읽을 수 있는 단어 없음 
			answer=0;
			System.out.println(answer);
			return;
		}
		
		selected = new boolean[26];
		// a,n,t,i,c
		// 단어에 항상 들어가는 5개의 단어는 무조건 포함해야 하므로 미리 방문처리 
		selected['a'-'a']=true;
		selected['n'-'a']=true;
		selected['t'-'a']=true;
		selected['i'-'a']=true;
		selected['c'-'a']=true;
		
		comb(0,0);	
		System.out.println(answer);
		
	
	}
	static public void comb(int cnt, int start) { // 
		if(cnt==K-5) { // K-5개 선택이 완료되면 리턴 
//			for(int i=0; i<26; i++) {
//				if(selected[i])
//					System.out.print(i+" ");
//			}
//			System.out.println();
			calculate(); // 현재 선택된 조합으로 계산하는 함수 
			return;
		}
		
		for(int i=start; i<26; i++) { // 조합 구하는 for문 
			if(!selected[i]) {
				selected[i]=true;
				comb(cnt+1,i+1);
				selected[i]=false;
			}
		}
	}
	static public void calculate() { //만들어진 조합으로 N개의 단어중 몇개 읽을 수 있는지 계산 
		int count=N;
		for(int i=0; i<N; i++) {
			String cur = arr[i];
			for(int j=4; j<cur.length()-4; j++) {
				if(selected[cur.charAt(j)-'a']==false) {
					count--;
					break;
				}
			}
			
		}
		answer=Math.max(count, answer); //최댓값 갱신 
//		System.out.println("count: "+count);
	}
}
