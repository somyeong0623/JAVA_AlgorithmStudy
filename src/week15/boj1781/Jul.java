package week15.boj1781;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Jul {
	static class problem{
		int deadline,ramen;
		public problem(int deadline, int ramen) {
			this.deadline = deadline;
			this.ramen = ramen;
		}
		@Override
		public String toString() {
			return "problem [deadline=" + deadline + ", ramen=" + ramen + "]";
		}
	
	}
	static int n;
	static long result;
	static PriorityQueue<Integer> prob;
	static problem[] pro;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		pro = new problem[n];
		prob = new PriorityQueue<>();
		for(int i = 0; i < n;i++) {
			st = new StringTokenizer(br.readLine());
			pro[i] = new problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(pro,new Comparator<problem>() {

			@Override
			public int compare(problem o1, problem o2) {
				// TODO Auto-generated method stub
				return o1.deadline!=o2.deadline?o1.deadline-o2.deadline:o2.ramen-o1.ramen;
			}
		});
		for (problem problem : pro) {
			int s = prob.size();
			if(s < problem.deadline) {
				prob.add(problem.ramen);
			}else if(s == problem.deadline) {
				int ramencnt = prob.peek();
				if(ramencnt < problem.ramen) {
					prob.poll();
					prob.add(problem.ramen);
				}
			}
		}
		while (!prob.isEmpty()) {
			result+=prob.poll();
		}
		System.out.println(result);
	}

}
