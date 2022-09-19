package week06.boj_18188;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class PJH {
	public static int[] rotate_r = {-1,0,1,0}; 
	public static int[] rotate_c = {0,1,0,-1}; 
	public static char[] rotate_char = {'W','D','S','A'};
	public static char[][] map;
	public static Stack<Integer> stack;
	public static int N;
	public static int r;
	public static int c;
	public static ArrayList<Integer> move;
	
	public static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		move = new ArrayList<>(); 
		
		r=0;
		c=0;
		
		st = new StringTokenizer(bf.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		stack = new Stack<>();
		for (int i = 0; i < H; i++) {
			char[] tmp = bf.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				map[i][j] = tmp[j];
				if(tmp[j]=='D') {
					r = i;
					c = j;
					map[i][j] = '.';
				}
			}
		}
		N = Integer.parseInt(bf.readLine());
		
		for(int i = 0 ; i < N;i++) {
			st = new StringTokenizer(bf.readLine());
			char B = st.nextToken().toCharArray()[0];
			char C = st.nextToken().toCharArray()[0];
			move.add(change(B));
			move.add(change(C));
		}
		
		boolean answer = false; //false면 no
		if(DFS(0)) {
			System.out.println("YES");
			System.out.println(sb);
		}else {
			System.out.println("NO");
		}
	}
	public static boolean check(int r,int c) {
		if(r>=0&&c>=0&&r<map.length&&c<map[0].length) {
			return true;
		}
		
		
		return false;
	}
	
	
	public static boolean DFS(int depth) {
		//System.out.println(stack + (r +","+c));
		if(depth==N) {//

			if(map[r][c]=='Z') {
				Stack<Integer> tmp_stack = new Stack<>();
				while(!stack.isEmpty()) {
					tmp_stack.add(stack.pop());
				}
				while(!tmp_stack.isEmpty()) {
					int a = tmp_stack.pop();
					sb.append(rotate_char[a]);
					stack.add(a);
				}
				return true;
			}
			return false;
		}else { //이동해야 한다.
			boolean tmp_return = false;
			int idx = depth*2;
			int tmp_r,tmp_c;
			tmp_r = r + rotate_r[move.get(idx)];
			tmp_c = c + rotate_c[move.get(idx)];
			//System.out.println(idx+":"+r +","+c+"->"+tmp_r+","+tmp_c);
			if(check(tmp_r,tmp_c)&&map[tmp_r][tmp_c]!='@') {
				if(map[tmp_r][tmp_c]=='.') {
					stack.add(move.get(idx));
					r = tmp_r;
					c = tmp_c;
					tmp_return = DFS(depth+1);
					r -= rotate_r[move.get(idx)];
					c -= rotate_c[move.get(idx)];
					stack.pop();
					
					//System.out.println("돌아옴"+r+","+c);
					
				}else {//발견
					stack.add(move.get(idx));
					Stack<Integer> tmp_stack = new Stack<>();
					while(!stack.isEmpty()) {
						tmp_stack.add(stack.pop());
					}
					while(!tmp_stack.isEmpty()) {
						int a = tmp_stack.pop();
						sb.append(rotate_char[a]);
						stack.add(a);
					}
					
					
					return true;
				}
			}
			if(!tmp_return) {
				//System.out.println("두번째시도");
				
				idx++;
				tmp_r = r + rotate_r[move.get(idx)];
				tmp_c = c + rotate_c[move.get(idx)];
				//System.out.println(idx+":"+r +","+c+"->"+tmp_r+","+tmp_c);
				if(check(tmp_r,tmp_c)&&map[tmp_r][tmp_c]!='@') {
					stack.add(move.get(idx));
					r = tmp_r;
					c = tmp_c;
					tmp_return = DFS(depth+1);
					r -= rotate_r[move.get(idx)];
					c -= rotate_c[move.get(idx)];
					stack.pop();
				}
			}
			
			return tmp_return;
		}
			
	}
	
	public static int change(char a) {
		if(a=='W') {
			return 0;
		}else if(a=='D'){
			return 1;
		}else if(a=='S'){
			return 2;
		}else {
			return 3;
		}
		
	}

}

