package week05.boj_14503;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

 
public class PJH {
	static int[][] table;
	static int answer;
	
	static int[] r_rotate = { 0, 1, 0, -1 };// 0 북이 디폴트로 기준으로해서 서쪽부터 탐색 서,남,동,북
	static int[] c_rotate = { -1, 0, 1, 0 };
	static int r;
	static int c;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		answer = 0;

		int N, M;
		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());// 세로
		M = Integer.parseInt(st.nextToken());// 가로

		int look;
		st = new StringTokenizer(bf.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		look = Integer.parseInt(st.nextToken()); // 0북 1동 2남 3서

		table = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 0; j < M; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			look = move(look);
			if(look==-1) {
				break;
			}
			
//			System.out.println(look);
//			for(int i =0 ; i < N ;i++) {
//				System.out.println(Arrays.toString(table[i]));
//			}
			
		}
		System.out.println(answer);
	}

	static int move(int look) {
		if(table[r][c]==0) {
			answer++;
			table[r][c] = 9;
		}
		boolean check_back = true;
		int original_look = look;
		look += 4;
		look = 8 - look;
		look %= 4;
		
		for(int i = 0 ; i < 4; i++) {
			int idx = ( look + i )%4;
			if(table[r + r_rotate[idx]][c + c_rotate[idx]]==0) {//이동가능한 위치 발견
				r += r_rotate[idx];
				c += c_rotate[idx];
				return (original_look-1+4-i)%4;
			}
			if(i==1 && table[r + r_rotate[idx]][c + c_rotate[idx]]==1) { //혹시라도 후진할 수 있으니 체크 벽이아니면 후진가능
				check_back = false; //후진불가
			}
		}
		//여기까지 왔다는건 4방향 모두 청소x 
		if(check_back) {//후진가능
			int idx = ( look + 1 )%4;
			
			r += r_rotate[idx];
			c += c_rotate[idx];
			return original_look;
		}else { //후진불가능 -1
			return -1;
		}
	}

}