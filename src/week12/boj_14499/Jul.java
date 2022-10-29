package week12.boj_14499;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jul {
	static int r,c,x,y,d,up,down,left,right,front,back;
	static int[][] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		r= Integer.parseInt(st.nextToken());
		c= Integer.parseInt(st.nextToken());
		x= Integer.parseInt(st.nextToken());
		y= Integer.parseInt(st.nextToken());
		d= Integer.parseInt(st.nextToken());
		graph = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < c; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		down = graph[x][y];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < d; i++) {
			int command = Integer.parseInt(st.nextToken());
			if(command == 1) {
				if(y+1>=c)continue;
				y++;
				int temp = right;
				right = up;
				up = left;
				left = down;
				down = temp;
				if(graph[x][y] != 0) {
					down = graph[x][y];
					graph[x][y] = 0;
				}else if(graph[x][y] == 0) {
					graph[x][y] = down; 
				}
			}else if(command == 2) {
				if(y-1 <0)continue;
				y--;
				int temp = left;
				left = up;
				up = right;
				right = down;
				down = temp;
				if(graph[x][y] != 0) {
					down = graph[x][y];
					graph[x][y] = 0;
				}else if(graph[x][y] == 0) {
					graph[x][y] = down; 
				}
			}else if(command == 3) {
				if(x-1<0) continue;
				int temp = back;
				x--;
				back = up;
				up = front;
				front = down;
				down = temp;
				if(graph[x][y] != 0) {
					down = graph[x][y];
					graph[x][y] = 0;
				}else if(graph[x][y] == 0) {
					graph[x][y] = down; 
				}
			}else if(command == 4) {
				if(x+1>=r)continue;
				int temp = front;
				x++;
				front = up;
				up = back;
				back = down;
				down = temp;
				if(graph[x][y] != 0) {
					down = graph[x][y];
					graph[x][y] = 0;
				}else if(graph[x][y] == 0) {
					graph[x][y] = down; 
				}
			}
//			System.out.println("방향 : "+command+" x :"+x+" y : "+y);
//			System.out.println(" "+back+" ");
//			System.out.println(left+""+up+""+right);
//			System.out.println(" "+front+" ");
//			System.out.println(" "+down+" ");
//			System.out.println("---------------------");
//			for (int[] js : graph) {
//				System.out.println(Arrays.toString(js));
//			}
			System.out.println(up);
		}
	}

}
