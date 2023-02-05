package week23.programmers_delivery;

import java.util.Arrays;

public class Jul {

	public static void main(String[] args) {
//		System.out.println(solution(4, 5, new int[] {1, 0, 3, 1, 2}, new int[] {0, 3, 0, 4, 0}));
//		System.out.println(solution(2, 7, new int[] {1, 0, 2, 0, 1, 0, 2}, new int[] {0, 2, 0, 1, 0, 2, 0}));
		System.out.println(solution(2, 7, new int[] {1, 0, 2, 0, 1, 0, 2}, new int[] {0, 0, 0, 0, 0, 0, 0}));
	}
	static int go,back,result;
	public static long solution(int cap, int n, int[] deliveries, int[] pickups) {
		go = 0;
		back = 0;
		long answer = 0;
		go = lastbox(deliveries);
		back = lastbox(pickups);
		while(check(deliveries,pickups)) {
			int index= Math.max(go, back);
			System.out.println("deliv : "+Arrays.toString(deliveries));
			System.out.println("pic : "+Arrays.toString(pickups));
			System.out.println("배달 가야할 집 위치 : "+go);
			System.out.println("수거 가야할 집 위치 : "+back);
			System.out.println("index : "+index);
			answer+=index+1;

			int aval = cap;

			for (int i = go; i >-1; i--) {
				if(aval == 0&&deliveries[i] > 0)break;
				else if(aval == 0 && deliveries[i] == 0) {
					go--;
					continue;
				}
				if(deliveries[i]<=aval) {
					aval+= -deliveries[i];
					deliveries[i]=0;
					go--;
				}else {
					deliveries[i] += -aval;
					aval = 0;
					break;
				}
			}
			answer+=index+1;
			aval = cap;
			for (int i = back; i >-1; i--) {
				if(aval == 0&&pickups[i]>0)break;
				else if(aval == 0 && pickups[i] == 0) {
					back--;
					continue;
				}
				if(pickups[i]<=aval) {
					aval+= -pickups[i];
					pickups[i]=0;
					back--;
				}else {
					pickups[i] += -aval;
					aval = 0;
					break;
				}
			}
			System.out.println("다음 배달 가야할 집 위치 : "+go);
			System.out.println("다음 수거 가야할 집 위치 : "+back);
			System.out.println("answer : "+answer);
			System.out.println("=================================");
			//        	}
		}
		return answer;
	}
	private static int lastbox(int[] house) {
		for (int i = house.length-1; i >-1; i--) {
			if(house[i]>0)return i;
		}
		return 0;
	}
	private static boolean check(int[] deliveries, int[] pickups) {
		for (int i = 0; i < pickups.length; i++) {
			if(deliveries[i] > 0 || pickups[i] > 0) return true;
		}
		return false;
	}
}
