package week13.programmers_TruckCrossingTheBridge;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Jul {
	static int s,wait,limit;
	static ArrayList<Integer> arrival;
	static Queue<Integer> onroad;
	public static void main(String[] args) {
		System.out.println(solution(2, 10, new int[] {7,4,5,6}));
	}
	public static int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		s = truck_weights.length;

		arrival = new ArrayList<>();
		onroad = new ArrayDeque<>();
		for (int i = 0; i < bridge_length; i++) {
			onroad.add(0);
		}
		while (arrival.size() != s) {
			int q = onroad.poll();
			limit -= q;
			if(wait<truck_weights.length && limit+truck_weights[wait] <= weight) {
				limit += truck_weights[wait];
				onroad.add(truck_weights[wait++]);
			}else {
				onroad.add(0);
			}
			if(q != 0) {
				arrival.add(q);
			}
			answer++;
//			System.out.println(answer);
		}
		return answer;
	}
}
