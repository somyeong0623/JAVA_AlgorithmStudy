package week07.boj_3190;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PJH {

	static int[][] table;
	static int[][] move;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(bf.readLine());
		int K = Integer.parseInt(bf.readLine());
		
		int[] next_r = {-1,0,1,0};
		int[] next_c = {0,1,0,-1};
		int idx = 1;
		int move_idx = 0;
		table = new int[N + 2][N + 2];
		
		
		//테두리 벽치기
		for (int i = 0; i < N + 2; i++) {
			table[0][i] = 3;
			table[i][0] = 3;
			table[N + 1][i] = 3;
			table[i][N + 1] = 3;
		}

		//사과위치에 1 놓기
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(bf.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			table[r][c] = 1;
		}
		
		//move입력받기
		int L = Integer.parseInt(bf.readLine());
		move = new int[L+1][2];
		
		for(int i = 0 ; i < L ; i++) {
			st = new StringTokenizer(bf.readLine());
			move[i][0] = Integer.parseInt(st.nextToken());
			move[i][1] = st.nextToken().charAt(0);
		}
		move[L][0] = 20000;
		move[L][1] = 0;
		
		
		Deque<Node> dq = new ArrayDeque<>();
		dq.add(new Node(1,1));
		table[1][1]=2;
		int time = 0;
		while(true) {
			//머리 놓기
			time++;
			Node head = dq.getFirst();
			int cr = head.r+next_r[idx];
			int cc = head.c+next_c[idx];
			
			if(table[cr][cc]==3||table[cr][cc]==2) {//벽 또는 몸통
				break;
			} else if(table[cr][cc]==0) {//사과만나지 않음
				Node tmp = dq.pollLast();
				table[tmp.r][tmp.c] = 0;
			}
			dq.addFirst(new Node(cr,cc));//머리놓기
			table[cr][cc] = 2;
			
			if(time==move[move_idx][0]) {
				if(move[move_idx][1]=='L') {
					idx = (3+idx)%4;
				}else {
					idx = (5+idx)%4;
				}
				move_idx++;
			}
			
			
//			System.out.println(dq+","+time);
//			for (int i = 0; i < N + 2; i++) {
//				for (int j = 0; j < N + 2; j++) {
//					System.out.print(table[i][j] + " ");
//				}
//				System.out.println();
//			}
		}
		System.out.println(time);

	}
	
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "[r=" + r + ", c=" + c + "]";
		}
		
	}

}


