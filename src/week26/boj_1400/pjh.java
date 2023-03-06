import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static Map<Character, boolean[]> cross;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {

			st = new StringTokenizer(bf.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			if (R == 0 && C == 0) {
				break;
			}

			int[] start = new int[2];
			int[] end = new int[2];
			cross = new HashMap<>();

			char[][] table = new char[R][C];
			for (int i = 0; i < R; i++) {
				char[] input_data = bf.readLine().toCharArray();
				for (int j = 0; j < C; j++) {
					if (input_data[j] == 'A') {
						start[0] = i;
						start[1] = j;
					} else if (input_data[j] == 'B') {
						end[0] = i;
						end[1] = j;
					} else if (input_data[j] >= '0' && input_data[j] <= '9') {
						cross.put(input_data[j], null);
					}
					table[i][j] = input_data[j];
				}
			}

			for (int i = 0; i < cross.size(); i++) {
				st = new StringTokenizer(bf.readLine());
				char target = st.nextToken().charAt(0);
				char what = st.nextToken().charAt(0);
				int ew = Integer.parseInt(st.nextToken());// 동서 false
				int sn = Integer.parseInt(st.nextToken());// 남북 true

				boolean[] tmp = new boolean[(ew + sn)*2];
				if (what == '-') {// 동서먼저
					Arrays.fill(tmp, true);
					for (int j = 0; j < ew; j++) {
						tmp[j] = false;
						tmp[j+ew+sn] = false;
					}
				} else {
					for (int j = 0; j < sn; j++) {
						tmp[j] = true;
						tmp[j+ew+sn] = true;
					}
				}
				//System.out.println(Arrays.toString(tmp));
				cross.replace(target, tmp);
			}

			
			
			
			int[] dr = { -1, 0, 1, 0 };
			int[] dc = { 0, 1, 0, -1 };

			int[][] time_record = new int[R][C];
			for (int i = 0; i < R; i++) {
				Arrays.fill(time_record[i], Integer.MAX_VALUE);
			}

			PriorityQueue<State> pq = new PriorityQueue<>((s1, s2) -> {
				return s1.time - s2.time;
			});
			time_record[start[0]][start[1]] = 0;
			pq.add(new State(start[0], start[1], 0));

			int answer = 0;
			boolean stop = false;
			while (!pq.isEmpty()) {
				State tmp_state = pq.poll();
				for (int i = 0; i < 4; i++) {
					int tr = dr[i] + tmp_state.r;
					int tc = dc[i] + tmp_state.c;
					
					if(0 <= tr && tr <R && 0 <= tc && tc <C 
					   &&table[tr][tc]!='.' && time_record[tr][tc]>tmp_state.time) {
						
						State state_add = new State(tr,tc,tmp_state.time);
						//System.out.println("초기: "+state_add);
						if(table[tr][tc]=='#') {
							state_add.time+=1;
						}else if(table[tr][tc] =='B') {
							answer = state_add.time + 1;
							stop = true;
							break;
						}else {
							//System.out.println("교차로진입" + table[tr][tc] + " " +Arrays.toString(cross.get(table[tr][tc])) );
							int circle = cross.get(table[tr][tc]).length/2;
							int cnt = 0;
							for(int k = (tmp_state.time%circle) ; k < cross.get(table[tr][tc]).length;k++) {
								state_add.time += 1;
								if((dr[i]!=0)==cross.get(table[tr][tc])[k]) {
									break;
								}
							}
						}
						time_record[tr][tc] = state_add.time;
						//System.out.println("마무리: "+state_add);
						//System.out.println();
						pq.add(state_add);
					}
				}
				if(stop) {
					break;
				}
			}
			if(answer == 0) {
				System.out.println("impossible");
			}else {
				System.out.println(answer);
			}

			
			
			bf.readLine();
		}

	}

	static class State {
		int r;
		int c;
		int time;

		public State(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}

		@Override
		public String toString() {
			return "State [r=" + r + ", c=" + c + ", time=" + time + "]";
		}

	}

}
