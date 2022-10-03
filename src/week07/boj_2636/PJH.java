package week07.boj_2636;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class PJH {

	static int[][] table;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[] next_r = { -1, 0, 1, 0 };
		int[] next_c = { 0, 1, 0, -1 };

		
		st = new StringTokenizer(bf.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		
		table = new int[R][C];
		for(int i = 0 ; i < R ; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0 ; j < C ; j ++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		table[0][0] = 2;
		Queue<Node> q = new LinkedList<>();
		Queue<Node> tmp_q = new LinkedList<>();
		q.add(new Node(0,0));
		
		
		int time = 0;
		int cheese = 0;
		while(!q.isEmpty() || !tmp_q.isEmpty()) {
			
			while(!q.isEmpty()) {
				Node tmp = q.poll();
				for(int i = 0 ; i < 4;i++) {
					int cr = next_r[i] + tmp.r;
					int cc = next_c[i] + tmp.c;
					
					if(isIn(cr,cc,R,C)) { //범위안이고
						if(table[cr][cc] == 0) {
							q.add(new Node(cr,cc));
							table[cr][cc] = 2;
						}else if(table[cr][cc] == 1) {
							tmp_q.add(new Node(cr,cc));
							table[cr][cc] = 3;
						}
						
					}
				}
			}
			if(tmp_q.size()!=0) {
				cheese = tmp_q.size();
			}else {
				break;
			}
			
//			System.out.println(tmp_q.size());
//			for(int i = 0 ; i < R ; i++) {
//				for(int j = 0 ; j < C ;j++) {
//					System.out.print(table[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			while(!tmp_q.isEmpty()) {
				Node tmp = tmp_q.poll();
				table[tmp.r][tmp.c]=2;
				q.add(tmp);
			}
			
			time++;
			
		}
		sb.append(time+"\n").append(cheese);
		System.out.println(sb);
		
		

	}
	
	static boolean isIn(int r, int c, int R ,int C) {
		if(r>=0 && r<R && c>=0 && c<C) {
			return true;
		}
		return false;
	}
	
	
	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}

}
