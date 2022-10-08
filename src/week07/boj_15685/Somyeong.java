package week07.boj_15685;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

//15686. 드래곤 커브 
public class Somyeong {
	static int n, r, c, d, g,answer;
	static int arr[][];
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[101][101];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());

			arr[r][c] = 1; // 시작점 체크 
			list = new ArrayList<>();  // 각 드래곤커브마다 전역으로 새로운 리스트 생성 
			
			//0세대 드래곤 커브 셋팅 
			switch (d) { // 시작점에서의 방향에 대한 다음꼭짓점 체크해주고 나서 func함수 시작 
			case 0:
				c++;
				break;
			case 1:
				r--;
				break;
			case 2:
				c--;
				break;
			case 3:
				r++;
				break;
			}
			arr[r][c] = 1; 
			list.add(d); // 리스트에 방향 추가 

			func(0, r, c);
			
		}
		for(int j=0; j<100; j++) { // 인접한 4개의 좌표가 1이면 answer 1 증가 
			for(int k=0; k<100; k++) {
				if(arr[j][k]==1 && arr[j+1][k]==1 && arr[j][k+1]==1 && arr[j+1][k+1]==1) {
					answer++;
				}
			}
		}
		System.out.println(answer);

	}

	public static void func(int index, int r, int c) { //현재의 세대,행좌표, 열좌표 

		//각 세대마다의 선분의 갯수는 1,2,4,8,16 ,,, 이렇게 2의 제곱이다 
		if (index == g) { //세대만큼 살펴봤으면 리턴 
			return;
		}

		int cnt = (int) Math.pow(2, index); //각 세대의 선분 갯수 
		
		// 다음세대의 방향 리스트는 이전리스트의 역순에다가 각각  (dir+1)%4를 add한 결과이다. 
		/* 예를 들어
		 * 1세대 : 1 2
		 * 2세대 : 1 2 3 2 (1세대의 2->3, 1->2를 리스트 뒤에 추가) 
		 * 3세대 : 1 2 3 2 3 0 3 2 (2세대의 2->3, 3->0, 2->3, 1->2 를 리스트 뒤에 추가)
		 * 
		 */
		
		for (int i = cnt - 1; i >= 0; i--) {
			int dir = list.get(i);
			dir = (dir + 1) % 4;

			switch (dir) {
			case 0:
				c++;
				break;
			case 1:
				r--;
				break;
			case 2:
				c--;
				break;
			case 3:
				r++;
				break;
			}
			arr[r][c] = 1;
			list.add(dir);
		}

		func(index + 1, r, c); // 다음세대 살펴보기 

	}

}
