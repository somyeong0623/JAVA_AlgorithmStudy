package week01.boj_2447;
import java.util.Scanner;
//2447. 별찍기 - 10  (모르겠어서 구글링 참고) 

public class Somyeong {
	
	static int n;
	static char[][] star;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		 n= sc.nextInt();
		 star = new char[n][n];
		 
		 recur(0,0,n,false); // r,c,size,isBlank
		 
		 for(int i=0; i<n; i++) {
			 for(int j=0; j<n; j++) {
				sb.append(star[i][j]);
			 }
			 sb.append("\n");
		 }
		 System.out.println(sb);
		
	}
	
	private static void recur(int r, int c, int n, boolean isBlank) {
		if(isBlank) { //이분이 if(n==1) 종료조건보다 먼저 와야하는것 주의 
			for(int i=r; i<r+n; i++) {
				for(int j=c; j<c+n; j++) {
					star[i][j]=' ';
				}
			}
			
			return;
		}
		
		if(n==1) { //종료조건 
			star[r][c]='*';
			return;
		}
		
		
		
		int step=n/3;
		int cnt=0;
		
		for(int i=r; i<r+n; i+=step) {
			for(int j=c; j<c+n;j+=step) {
				cnt++;
				if(cnt==5) { //공백 출력해야할 경우 
					recur(i,j,step,true);
				}else {
					recur(i,j,step,false);
				}
			}
		}
		
		
		
	}
}
