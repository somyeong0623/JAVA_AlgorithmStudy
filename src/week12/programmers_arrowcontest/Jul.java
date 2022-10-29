package week12.programmers_arrowcontest;
import java.util.Arrays;

public class Jul {

	public static void main(String[] args) {
//		System.out.println(solution(2, new int[] {2, 1, 1}));
//		System.out.println(solution(5, new int[] {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0}));
//		System.out.println(solution(1, new int[] {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
//		System.out.println(solution(9, new int[] {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1}));
//		System.out.println(solution(10, new int[] {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3}));
		for (int i =10; i > -1; i--) {
			System.out.println(i);
		}
	}
	static boolean flag;
	static int[] answer;
	static int N,w;
	static int[] input,result;
	public static int[] solution(int n, int[] info) {
		N = n;
		answer = new int[info.length];
		result = new int[info.length];
		input = info;
		makeCase(0,0);
		System.out.println("정답:");
		System.out.println(Arrays.toString(result));
		System.out.println(w);
		if(flag)return result;
		else return new int[] {-1};
	}
	private static void makeCase(int index, int count) {
		if(count == N) {
			System.out.println(Arrays.toString(answer));
			versace();
			return;
		}
		for (int k = index; k < answer.length; k++) {
			answer[k]++;
			makeCase(k, count+1);
			answer[k]--;
		}
		
		
	}
	private static void versace() {
		int peach=0,lion=0;
		for (int i = 0; i <= 10; i++) {
			if(input[i]<answer[i])lion+=(10-i);
			else if(input[i]>=answer[i]) {
				if(input[i]==0&&answer[i]==0)continue;
				peach+=(10-i);
			}
		}
		int r = lion-peach;
		if(r<0)return;
		if(r>w) {
//			System.out.println("결과나옴");
//			System.out.println(Arrays.toString(answer));
//			System.out.println(peach+" "+lion+" "+r);
			w = r;
			flag = true;
			for (int i = 0; i < answer.length; i++) {
				result[i] = answer[i];
			}
		}else if(r == w) {
			boolean ischange = false;
			for (int i =10; i > -1; i--) {
				if(result[i]<answer[i]) {
					ischange = true;
					break;
				}else if(result[i]>answer[i]) {
					ischange = false;
					break;
				}
			}
			if(ischange) {
				for (int i = 0; i < answer.length; i++) {
					result[i] = answer[i];
				}
			}
		}
	}	
}
