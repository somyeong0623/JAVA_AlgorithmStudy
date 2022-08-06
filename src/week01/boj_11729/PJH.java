package week01.boj_11729;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PJH {
	static int total;
	static StringBuilder sb;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int input = Integer.parseInt(br.readLine());
		hanoi(input, 1,2,3);
		System.out.println(total);
		System.out.println(sb);
		
	}
	
	static void hanoi(int N,int start,int other ,int end) {
		if(N==1) {
			sb.append(start + " "+ end+"\n");
			total++;
		}
		else {
			hanoi(N-1,start,end,other);//마지막 하나를 제외하고 나머지를 end가 아닌 다른 곳으로 옮겨야 한다.
			sb.append(start + " "+ end+"\n"); //이전 하나를 다른 곳에 하나 올린다.
			total++;
			hanoi(N-1,other,start,end); // 다 옮겼으면 잠시 나둔 other에 있는 것들을 end로 옮겨야 한다.
		}
	} 
	
	
}
