package week27.programmers_expressionable_bi_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Jul {
	static boolean flag;
	public static int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int index = 0;
        for (long l : numbers) {
        	flag = true;
        	System.out.println(l);
            if(l == 1) {
            	answer[index++] = 1;
            	continue;
            }

        	ArrayList<Integer> queue = new ArrayList<>();
			long a = l;
			while (true) {
				if(a%2==1)queue.add(1);
				else queue.add(0);
				a /= 2;
				if(a == 1 || a == 0) {
					if(a%2==1) {
						queue.add(1);
						break;
					}
					else {
						queue.add(0);
						break;
					}
				}
			}
			System.out.println(queue);
			int height = 1;
			int count = 0;
			while (true) {
				if(height*2 >= queue.size()+1) {
					height *= 2;
					count++;
					break;
				}else {
					height *= 2;
					count++;
				}
			}
			System.out.println("count : "+count);
			System.out.println("height : "+height);
			for (int i = queue.size(); i < height-1; i++) {
				queue.add(0);
			}
			System.out.println(queue);
			certifi(sqr(2,count-1),(height-1)/2,queue);
			answer[index++] = flag?1:0;
		}
        System.out.println("result => "+Arrays.toString(answer));
        return answer;
    }

	private static int sqr(int i, int j) {
		int result = 1;
		for (int j2 = 0; j2 < j; j2++) {
			result*=2;
		}
		return result;
	}

	private static int certifi(int cnt, int i, ArrayList<Integer> queue) {
		if(cnt == 1)return queue.get(i);
		int a = certifi(cnt/2, i-cnt/2, queue);
		int b = certifi(cnt/2, i+cnt/2, queue);
		if(queue.get(i)==0) {
			if(a==1||b==1) {
				flag = false;
				return 0;
			}else if(a==0&&b==0){
                return 0;
            }
		}return 1;
	}

	public static void main(String[] args) {
		System.out.println(solution(new long[]{26752,40}));
//		System.out.println(solution(new long[]{63, 111, 95}));
	}

}
