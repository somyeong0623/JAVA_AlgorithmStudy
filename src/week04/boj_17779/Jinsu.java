package week04.boj_17779;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Jinsu {

	static int N;
	static int[][] board;
	static int min = Integer.MAX_VALUE;
	static int AreaPeople = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		board = new int[N+1][N+1];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				AreaPeople += board[i][j];
			}
		} // 입력 완료
		
		for(int i=0; i< N; i++) {
			for(int j=0; j<N; j++) {
				for(int dist1 = 1; dist1<N; dist1++) {
					for(int dist2 = 1; dist2<N; dist2++) {
						
						if(i+dist1+dist2 >= N) continue;
						if(j-dist1 < 0 || j+dist2 >= N) continue;
						
						search(i, j, dist1, dist2);
					}
				}
			}
		}
		
		System.out.println(min);
	}
	
	private static void search(int x, int y, int d1, int d2) {
		boolean[][] area = new boolean[N][N];
		
		//1) 경계선
		for(int i=0; i <= d1; i++) {
			area[x+i][y-i] = true;
			area[x+d2+i][y+d2-i] = true;
		}
		
		for(int i=0; i <= d2; i++) {
			area[x+i][y+i] = true;
			area[x+d1+i][y-d1+i] = true;
		}
		
		int[] PeopleNum = new int[5];
		
		// 2) 1번 구역
		for(int i=0; i < x+d1; i++) {
			for(int j=0; j<= y; j++) {
				if(area[i][j]) break;	//경계선인지 판별
				PeopleNum[0] += board[i][j];
			}
		}
		
		
		// 3) 2번 구역
		for(int i=0; i<= x+d2; i++) {
			for(int j = N-1; j > y; j--) {
				if(area[i][j]) break;	//경계선인지 판별
				PeopleNum[1] += board[i][j];
			}
		}
		
		
		// 4) 3번 구역
		for(int i = x+d1 ; i < N; i++) {
			for(int j= 0; j < y - d1 + d2; j++) {
				if(area[i][j]) break;
				PeopleNum[2] += board[i][j];
			}
		}
		
		// 5) 4번 구역
		for(int i= x+d2+1; i < N ; i++) {
			for(int j=N-1; j>= y-d1+d2; j--) {
				if(area[i][j]) break;
				PeopleNum[3] += board[i][j];
			}
		}
		
		//6) 5번 구역
		PeopleNum[4] = AreaPeople;
		for(int i=0; i<4; i++) {
			PeopleNum[4] -= PeopleNum[i];
		}
		
		
		Arrays.sort(PeopleNum);
		
		min = Math.min(min, PeopleNum[4]-PeopleNum[0]);
		
	}

}
