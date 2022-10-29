package week12.boj_2470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//2470. 두 용액 
public class Somyeong {
	
	static int n;
	static int arr[];
	static int answer1, answer2;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr= new int[n];
		for(int i=0; i<n; i++ ) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int start=0;
		int end=n-1;
		int min=Integer.MAX_VALUE;
		int mid=0;
		while(start<end) {
			mid= Math.abs(arr[start]+arr[end]);
			
			if(mid<min) { //두수합의 절대값이 더 작을때마다 정답(answer1, answer2) 갱신 
				min=mid;
				answer1=arr[start];
				answer2=arr[end];
			}
			
			if(arr[start]+arr[end]>0) { // 합이 양수면 합을 줄이는 방향으로 
				end--;
			}else{ // 합이 음수면 합을 증가시키는 방향으로 
				start++; 
			}
		}

		System.out.println(answer1+" "+answer2);
		
		
	
	}
}
