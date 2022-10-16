package week10.programmers_HowToLineUp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jul {

	public static void main(String[] args) {
		System.out.println(solution(3, 5));
		System.out.println(0%1);
	}
	public static int[] solution(int n, long k) {
		int[] answer = {};
		answer = new int[n];
		int N = n;
		long total = 1;
		List<Integer> num = new ArrayList<>();
		for (int i = 1; i < n+1; i++) {
			total *= i;
			num.add(i);
		}
		int start = 0;
		k--;
		while (n>0) {
			total /= n;
			answer[start] = num.remove((int) (k/total));
			n--;
			start++;
			k = (k%total);
		}
		System.out.println(Arrays.toString(answer));
		return answer;
	}
}


