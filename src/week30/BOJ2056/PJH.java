import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Main {
	static StringTokenizer st;
	static int N;
	static Work[] work_list;
	static ArrayList<Integer>[] require_list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		PriorityQueue<Work> pq = new PriorityQueue<>((w1,w2)->{
			return w1.time-w2.time;
		});
		
		N = Integer.parseInt(bf.readLine());
		work_list = new Work[N];
		require_list = new ArrayList[N];
		
		
		
		for(int i = 0 ; i < N ;i++) {
			work_list[i] = new Work();
			require_list[i] = new ArrayList<>();
			
			String[] line = bf.readLine().split(" ");
			work_list[i].id = i;
			work_list[i].time = Integer.parseInt(line[0]);
			
			int cnt = Integer.parseInt(line[1]);
			work_list[i].pre_cnt = cnt;
			if(cnt==0) {
				pq.add(work_list[i]);
			}
			
			for(int j = 0 ; j < cnt ; j++) {
				int pre = Integer.parseInt(line[j+2])-1;
				require_list[pre].add(i);
			}
			
		}
//		for(int i = 0 ; i < N ;i++) {
//			System.out.println(work_list[i]+" "+ require_list[i]);
//		}
		
		int answer = 0;
		while(!pq.isEmpty()) {
			Work target = pq.poll();
			target.answer += target.time;
			answer = Math.max(answer, target.answer);
			for(int i = 0 ; i < require_list[target.id].size();i++) {
				Work next = work_list[require_list[target.id].get(i)];
				next.pre_cnt--;
				next.answer = Math.max(next.answer, target.answer);
				if(next.pre_cnt==0) {
					pq.add(next);
				}
			}
		}
//		System.out.println();
//		for(int i = 0 ; i < N ;i++) {
//			System.out.println(work_list[i]+" "+ require_list[i]);
//		}
		System.out.println(answer);
	}
	
	static class Work{
		int id;
		int time;
		int pre_cnt;
		int answer;
		@Override
		public String toString() {
			return "Work [id=" + id + ", time=" + time + ", pre_cnt=" + pre_cnt + ", answer=" + answer + "]";
		}
		
		
		
	}

}