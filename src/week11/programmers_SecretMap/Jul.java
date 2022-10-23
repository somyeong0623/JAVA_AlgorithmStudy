package week11.programmers_SecretMap;

import java.util.Arrays;

public class Jul {

	public static void main(String[] args) {
		System.out.println(solution(5, new int[] {9, 20, 28, 18, 11}, new int[] {30, 1, 21, 17, 28}));
		System.out.println(solution(6, new int[] {46, 33, 33, 22, 31, 50}, new int[] {27, 56, 19, 14, 14, 10}));
//		System.out.println(9&(1<<3));
	}
    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i = 0; i < answer.length; i++) {
			String result = "";
			for (int j = n-1; j > -1; j--) {
				if((arr1[i]&(1<<j))>0 || (arr2[i]&(1<<j))>0) {
					result+="#";
				}else {
					result+=" ";
				}
			}
			answer[i] = result;
		}
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}
