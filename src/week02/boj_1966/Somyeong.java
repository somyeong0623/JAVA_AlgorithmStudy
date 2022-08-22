package week02.boj_1966;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Info{
	int index;
	int value;
	public Info(int index, int value) {
		super();
		this.index = index;
		this.value = value;
	}
	
}

//1966. 프린터 큐 
public class Somyeong {
	
	static int TC,m,n;
	static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		TC =Integer.parseInt(br.readLine());
		for(int t=0; t<TC; t++) {
			answer=0;  // 인쇄된 문서 갯수 
			Queue<Info> q= new LinkedList<>();// 문서의(인덱스,중요도)를 저장 
			List<Integer> list = new ArrayList<>(); //문서의 중요도를 저장 
			
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				int v= Integer.parseInt(st.nextToken());
				q.add(new Info(i,v));
				list.add(v);
			}
			Collections.sort(list); // 중요도를 저장한 리스트를 오름차순 정렬 
			int maxValue=list.get(list.size()-1); //리스트의 마지막 원소를 통해 가장 높은 중요도를 가져옴 .
			
			while(true) {
				Info cur = q.peek();
				if(cur.index==m && cur.value==maxValue) { // 찾으려는 문서가 인쇄되는 경우 (중요도가 가장 높아서)
					answer++; //인쇄된 문서갯수 증가 
					break;
				}
				else if(cur.value==maxValue) { //맨앞 문서의 중요도가 가장 높은경우 
					answer++; // 인쇄된 문서 갯수 증가 
					q.remove(); // 맨앞 문서 삭제 
					maxValue=list.get(list.size()-1-answer);
				}else { //현재 맨앞문서가 중요도가 가장높은것이 아닐경우 ->큐에서 삭제하고 뒤로 보내기
					q.remove();
					q.add(cur);
					
				}
			}
			System.out.println(answer);
		}
	}

}
