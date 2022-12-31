//해결하는데 걸린 시간 50분 16초

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] build_finish;
	static boolean[] pq_in_building;
	static int[] time;
	static ArrayList[] pre_list;
	
	static PriorityQueue<Build> pq;
	
	static int answer;
	static int N;
	static int cnt;
	static int[] answer_list;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(bf.readLine());
		build_finish = new boolean[N];
		pq_in_building = new boolean[N];
		time = new int[N];
		pre_list = new ArrayList[N];
		answer = 0;
		cnt = 0;
		answer_list = new int[N];
		pq = new PriorityQueue<>((e1, e2) -> {
			return e1.time - e2.time;
		});

		for (int i = 0; i < N; i++) {
			pre_list[i] = new ArrayList<Integer>();
			st = new StringTokenizer(bf.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			while (st.hasMoreTokens()) {
				pre_list[i].add(Integer.parseInt(st.nextToken()) - 1);
			}
			pre_list[i].remove(pre_list[i].size() - 1);
		}
		while(cnt<N) {
			searchBuild();
			building();
		}
		for(int i = 0 ; i < N ; i ++) {
			System.out.println(answer_list[i]);
		}
	}

	static void searchBuild() { //지을건물 찾기 
		for (int i = 0; i < N; i++) {
			if(!build_finish[i]) {//완성되지 않은 건물
				boolean check = true;
				for(int j = 0 ; j < pre_list[i].size(); j++) {
					if(!build_finish[(int) pre_list[i].get(j)]) {
						check = false;
						break;
					}
				}
				if(check &&!pq_in_building[i]) {
					pq.add(new Build(i,time[i]));
					pq_in_building[i] = true;
				}
			}
		}
	}
	static void building() {//건물 지었다고 판단 제일 앞에 건물 기준으로 검색
		Queue tmp_q = new LinkedList<Build>();
		Build target = pq.poll();
		while(!pq.isEmpty()) {
			Build tmp_build = pq.poll();
			tmp_build.time -= target.time;
			tmp_q.add(tmp_build);
		}
		while(!tmp_q.isEmpty()) {
			pq.add((Build) tmp_q.poll());
		}
		answer += target.time;
		build_finish[target.index] = true;
		pq_in_building[target.index] = false;
		cnt+=1;
		answer_list[target.index] = answer;
	}

	static class Build {
		int index;
		int time;

		public Build(int index, int time) {
			this.index = index;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Build [index=" + index + ", time=" + time + "]";
		}
		
	}

}