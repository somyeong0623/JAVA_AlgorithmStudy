package week08.boj_17070;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PJH {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int input = Integer.parseInt(bf.readLine());
		Info[] info = new Info[input+1];
		
		info[1] = new Info(1,1,1);
		for(int i = 2; i < info.length;i++) {
			long left = info[i-1].all;
			long all = info[i-1].all;
			long right = info[i-1].all;
			
			all += info[i-1].left + info[i-1].right;
			left += info[i-1].right;
			right += info[i-1].left;
			
			info[i]= new Info(left,all,right);
		}
		System.out.println(info[input].sum()%9901);
	}
	
	static class Info{
		long left; //왼쪽 색칠 x
		long all; //전부 색칠 x
		long right;//오른쪽 색칠 x
		
		public Info(long left,long all, long right) {
			this.left = left%9901;
			this.all = all%9901;
			this.right = right%9901;
		}
		public long sum() {
			return left+all+right;
		}
	}
	
}