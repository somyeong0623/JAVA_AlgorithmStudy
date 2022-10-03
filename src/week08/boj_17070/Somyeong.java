package week08.boj_17070;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//17070. 파이프 옮기기 1 
public class Somyeong{
	static int d[][][]; //방향 별 dp 배열 
	static int dp[][];
	static int arr[][]; //주어진 배열 
	static int n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		arr=new int[n+1][n+1];
		d=new int[n+1][n+1][3];
		dp=new int[n+1][n+1];
		
		for(int i=1; i<=n; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		d[1][2][0]=1;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(j==1 || j==2 ) continue; //열이 1이나 2이면 패스 
				
				if(arr[i][j]==1) continue; // 벽이면 패스  
					
				
				d[i][j][0]=d[i][j-1][0]+d[i][j-1][2]; // (i,j)지점에 가로 방향으로 올 수 있는 경우 
				d[i][j][1]=d[i-1][j][1]+d[i-1][j][2]; // (i,j)지점에 세로 방향으로 올 수 있는 경우 
				
				if(arr[i-1][j]==0 && arr[i][j-1]==0) // (i,j)지점에 대각선 방향으로 올 수 있는 경우 (경로에 벽이 있는 경우를 고려해야한다. )
					d[i][j][2]=d[i-1][j-1][0]+d[i-1][j-1][1]+d[i-1][j-1][2];
			}
		}
		
		System.out.println(d[n][n][0]+d[n][n][1]+d[n][n][2]); // (n,n)지점에 세개의 방향으로 놓을 수 있는 경우의 합 
		 
	
	}
}