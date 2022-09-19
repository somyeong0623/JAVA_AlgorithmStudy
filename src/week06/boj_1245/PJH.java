package week06.boj_1245;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PJH{
	public static int[] rotate_r = {-1,-1,0,1,1,1,0,-1};
	public static int[] rotate_c = {0,1,1,1,0,-1,-1,-1};
	public static int N,M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		st = new StringTokenizer(bf.readLine());
		int[][] table;
		boolean[][] check;
		int answer = 0;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		table = new int[N][M];
		check = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!check[i][j]) {
					//System.out.println("탐색:"+i+","+j);
					Queue<Point> q = new LinkedList<>();
					q.add(new Point(i,j));
					check[i][j]= true;
					boolean check_able= true;
					while(!q.isEmpty()) {
						
						Point tmp = q.poll();
						for(int rotate_i = 0 ; rotate_i < 8;rotate_i++) {
							int tmp_r = rotate_r[rotate_i]+tmp.r;
							int tmp_c = rotate_c[rotate_i]+tmp.c;
							if(check(tmp_r,tmp_c)) { //유효범위
								//System.out.println("확인:"+tmp_r+","+tmp_c+"=>"+table[tmp_r][tmp_c]+" vs "+ table[i][j]);
								if(table[tmp_r][tmp_c]==table[i][j]) { //기준이랑 높이 같고 인접했었다면
									if(!check[tmp_r][tmp_c]) {
										check[tmp_r][tmp_c] = true;
										q.add(new Point(tmp_r,tmp_c));
									}
									
								}else if(table[tmp_r][tmp_c]>table[i][j]&&check_able) {
									//System.out.println("이놈들안됨");
									check_able = false;
									
								}

							}
						}
					}
					
					
					if(check_able) {
						//System.out.println(i+","+j);
						answer++;
					}
					
				}
			}
		}
		
		

		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(table[i]));
//		}
		System.out.println(answer);
		
	}
	public static boolean check(int r,int c) {
		if(r>=0&&r<N&&c>=0&&c<M) {
			return true;
		}
		return false;
	}
	
	
}

class Point{
	int r;
	int c;
	public Point(int r,int c) {
		this.r = r;
		this.c = c;
	}
}
