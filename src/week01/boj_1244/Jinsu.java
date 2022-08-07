package week01.boj_1244;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Jinsu {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s = Integer.parseInt(br.readLine()); 	// 스위치 개수
		int[] sw = new int[s];	// 스위치 상태 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i =0; i<s; i++) {
			sw[i] = Integer.parseInt(st.nextToken());
		}
		
		int student = Integer.parseInt(br.readLine()); // 학생 수
		for (int i =0; i<student; i++) {
			st = new StringTokenizer(br.readLine());
			int gender = Integer.parseInt(st.nextToken()); 
			int SwNum = Integer.parseInt(st.nextToken());	// 성별과 스위치 번호
			
			if (gender == 1) {	//성별이 남자일 때
				for (int j=0; j<s; j++) {
					if ((j+1) % SwNum == 0) {
						sw[j] = Math.abs(sw[j]-1);	
					}
				}
			}//if 문 끝
			
			
			else {						// 성별이 여자일 때
				sw[SwNum-1] = Math.abs(sw[SwNum-1]-1);
				int tmp = 1;			// 여자 성별일 때 인덱스 증감
				
				while (true) {
					if (SwNum-tmp-1 >= 0 && SwNum+tmp-1 < s && sw[SwNum-tmp-1] == sw[SwNum+tmp-1]) {
						sw[SwNum-tmp-1] = Math.abs(sw[SwNum-tmp-1]-1);
						sw[SwNum+tmp-1] = Math.abs(sw[SwNum+tmp-1]-1);
						tmp++;
					}
					else break;
				}
				
				
			}
			
			
		}
		for(int j =0; j<s; j++) {
			System.out.print(sw[j]+" ");
			if ((j+1)%20 == 0) {
				System.out.println();
			}
		}	
	}

}
