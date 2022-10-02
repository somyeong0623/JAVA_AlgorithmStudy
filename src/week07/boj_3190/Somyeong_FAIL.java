package week07.boj_3190;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//3190. 뱀 
public class Somyeong_FAIL {

	static class Info {
		int second;
		char d;

		public Info(int second, char d) {
			this.second = second;
			this.d = d;
		}

	}

	static int N, K, L, answer;
	static int arr[][];
	static int head_r, head_c, tail_r, tail_c; // 뱀의 머리와 꼬리의 좌표
	static int dir; // 뱀이 움직이는 방향

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr[r][c] = 1;
		}
		L = Integer.parseInt(br.readLine());
		Queue<Info> queue = new ArrayDeque<Info>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);

			queue.offer(new Info(s, d));
		}

		head_r = 1;
		head_c = 1;
		tail_r = 1;
		tail_c = 1;
		arr[head_r][head_c] = 2; // 뱀은 2, 사과는 1로 표시
		dir = 0;

		// 뱀이 움직이는 방향 : dir
		// 0: 동, 1: 남, 2:서, 3북
		// D 이면 dir=(dir+1)%4
		// L 이면 dir=(dir+4-1)%4
		loop: while (true) {
			System.out.println("head_r: "+head_r+", head_c:"+head_c);
			System.out.println("====answer: "+answer+"=====");
			for(int i=1; i<=10 ; i++) {
				for(int j=1; j<=10; j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
			
			switch (dir) {
			case 0:
				if (head_c + 1 > N)
					break loop;
				head_c++;
				if (arr[head_r][head_c] == 2)
					break loop; // 자기자신의 몸과 부딪힘
				if(arr[head_r][head_c]==1) arr[head_r][head_c]=0;
				else if (arr[head_r][head_c] == 0) {
					arr[tail_r][tail_c] = 0;
					tail_c++;
				}
				arr[head_r][head_c]=2;
				break;
			case 1:
				if (head_r + 1 > N)
					break loop;
				head_r++;
				if (arr[head_r][head_c] == 2)
					break loop; // 자기자신의 몸과 부딪힘
				if(arr[head_r][head_c]==1) arr[head_r][head_c]=0;
				else if (arr[head_r][head_c] == 0) {
					arr[tail_r][tail_c] = 0;
					tail_r++;
				}
				arr[head_r][head_c]=2;
				break;
			case 2:
				if (head_c - 1 <= 0)
					break loop;
				head_c--;
				if (arr[head_r][head_c] == 2)
					break loop; // 자기자신의 몸과 부딪힘
				if(arr[head_r][head_c]==1) arr[head_r][head_c]=0;
				else if (arr[head_r][head_c] == 0) {
					arr[tail_r][tail_c] = 0;
					tail_c--;
				}
				arr[head_r][head_c]=2;
				break;

			case 3:
				if (head_r - 1 <= 0)
					break loop;
				head_r--;
				if (arr[head_r][head_c] == 2)
					break loop; // 자기자신의 몸과 부딪힘
				if(arr[head_r][head_c]==1) arr[head_r][head_c]=0;
				else if (arr[head_r][head_c] == 0) {
					arr[tail_r][tail_c] = 0;
					tail_r--;

				}
				arr[head_r][head_c]=2;
				break;
			}
			answer++;
			if (!queue.isEmpty()) {
				if (queue.peek().second == answer) {
					if (queue.peek().d == 'D')
						dir = (dir + 1) % 4;
					else if (queue.peek().d == 'L')
						dir = (dir + 4 - 1) % 4;
					queue.poll();
				}
			}

		}

		System.out.println(answer);

	}
}
