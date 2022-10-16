package week10.programmers_Immigration;

import java.util.*;
public class Jul {


	 public static long solution(int n, int[] times) {
	        long answer =Long.MAX_VALUE;
	        long left = 0;
	        Arrays.sort(times);
	        long right = (long) times[times.length-1] * (long)n;
	        long mid = 0;
	        int find = n-1;
	        if(n==1) return times[0];
	        while (left != right) {
				mid = (left+right+1)/2;
				long count = 0;
				for (int i : times) {
					count+= mid/i;
				}
				if(count>find)right = mid-1;
				else left = mid;
			}
	        long c = 0;
	        for (int i : times) {
				c+= left/i;
			}
	        long div = Long.MAX_VALUE;
	        for (int i : times) {
				div = Math.min(div, i-left%i);
			}
	        answer = left+div;
	        return answer;
	    }
}



