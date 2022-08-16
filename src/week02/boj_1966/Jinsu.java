package week02.boj_1966;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Jinsu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();	//testcase
		
		for(int tc=0; tc<T; tc++) {
			int n = sc.nextInt();	//문서 갯수
			int m = sc.nextInt();	//해당 인덱스의 문서가 몇 번째로 출력될지에 대한 변수
			
			Queue<Integer> index = new LinkedList<>();
			Queue<Integer> important = new LinkedList<>();
			
			for(int i=0; i<n; i++) {
				index.offer(i);
				important.offer(sc.nextInt());
			}
			
			int cnt = 0;
			Loop1:
			while (!important.isEmpty()) {
				for (int i=0; i<important.size(); i++) {
					if (Collections.max(important) != important.peek()) {	//중요도 검사
						important.add(important.poll());
						index.add(index.poll());
					}
					else {
						if (index.peek() == m) {	//문서 중요도 순으로 빼다가 그 문서의 인덱스가 같은지 판 
							cnt++;
							break Loop1;			//이중 반복문 탈출
						}
						important.poll();
						index.poll();
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
	}

}
