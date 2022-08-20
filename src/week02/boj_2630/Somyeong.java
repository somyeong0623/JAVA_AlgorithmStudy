package week02.boj_2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2630.색종이 만들기 
/*
 * 하얀색0, 파란색1
 * 하얀색,파란색 색종이갯수 출력 
 * 
 */
public class Somyeong {
	static int n;
	static int[][] arr;
	static int white, blue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb= new StringBuilder();
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n][n];
		for(int i=0; i<n; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		func(0,0,n);
		sb.append(white+"\n"+blue);
		System.out.println(sb);
	
	}
	private static void func(int r, int c, int len) { 	//(r,c): 시작좌표, len: 현재 확인하려는 정사각형 길이 
		
		int temp_white=0; //확인범위 내에서의 하얀색색종이 갯수 
		for(int i=r; i<r+len; i++) {
			for(int j=c; j<c+len; j++) {
				if(arr[i][j]==0)
					temp_white++;
			}
		}
		if(temp_white==len*len) {
			white++;
		}else if(temp_white==0) {
			blue++;
		}else {
			
			//현재 정사각형 범위내의 색종이 색깔이 전부 같은색이 아니라면
			//다음 네 정사각형 범위로 나눠짐 
			func(r,c,len/2);
			func(r,c+len/2, len/2);
			func(r+len/2, c, len/2);
			func(r+len/2, c+len/2, len/2);
		}
		
	}
}
