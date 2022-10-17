package week10.programmers_Immigration;

import java.util.Comparator;
import java.util.PriorityQueue;

public class test {
	static class Task {
		int start;
		int time;

		public Task(int start, int time) {
			this.start = start;
			this.time = time;
		}

	}

	public static void main(String[] args) {

		PriorityQueue<Task> pq = new PriorityQueue<Task>(new Comparator<Task>() {

			@Override
			public int compare(Task t1, Task t2) {
				return t1.time - t2.time;
			}
		});
		
		pq.add(new Task(1, 9));
		pq.add(new Task(0, 3));
		pq.add(new Task(2, 6));
		System.out.println("=== 두번째 변수 기준 오름차순 === ");
		while(!pq.isEmpty()) {
			Task t = pq.peek();
			System.out.println(t.start + " " + t.time);
			pq.poll();
			
		}

		PriorityQueue<Task> pq2 = new PriorityQueue<Task>(new Comparator<Task>() {

			@Override
			public int compare(Task t1, Task t2) {
				return t2.start - t1.start;
			}
		});

		pq2.add(new Task(1, 9));
		pq2.add(new Task(0, 3));
		pq2.add(new Task(2, 6));
		System.out.println("=== 첫번째 원소 기준 내림차순 === ");

		for (Task t : pq2) {
			System.out.println(t.start + " " + t.time);
		}
	}

}
