package week05.boj_19238;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class PJH { 
	static int[][] table;
	static int r, c;
	static int N, M, limit;
	static ArrayList<User> user;
	static int[] rotate_r = { -1, 0, 0, 1 };
	static int[] rotate_c = { 0, -1, 1, 0 };
	static int user_id;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken()); // 테이블크기
		M = Integer.parseInt(st.nextToken()); // 승객수
		limit = Integer.parseInt(st.nextToken()); // 초기연료
		table = new int[N + 2][N + 2];
		user = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			for (int j = 1; j <= N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				table[i][j] = tmp;
			}
		}
		for (int i = 0; i < N + 2; i++) {
			table[0][i] = 1;
			table[N + 1][i] = 1;
			table[i][0] = 1;
			table[i][N + 1] = 1;
		}

		st = new StringTokenizer(bf.readLine());
		r = Integer.parseInt(st.nextToken());// 택시의 위치를 표시함.
		c = Integer.parseInt(st.nextToken());

		
		boolean target_check = false; // false라면 택시가 승객을 찾으러 다녀야 한다는 것이다. true라면 택시가 도착할 위치가 있다는 것이다.
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			user.add(new User(new Point(r1, c1), new Point(r2, c2)));
			if(r1==r&&c1==c) {
				user_id = i;
				target_check=true;
			}
		}

		
		// false라면 r,c는 택시 위치를 나타낸다.
		// true라면 r,c는 키값역할을 한다.
		// map에서 2는 발견시 멈춰야하는 것을 뜻한다.
		
		while (!user.isEmpty()) {
			int[][] map = new int[table.length][table.length];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					map[i][j] = table[i][j];
				}
			}
			if (!target_check) { // 택시가 승객을 찾으러 다녀야 한다면
				for (int i = 0; i < user.size(); i++) {
					map[user.get(i).src.r][user.get(i).src.c] = 2; // 승객들이 위치한 곳에 2를 표시
				}
			} else {
				for (int i = 0; i < user.size(); i++) {
					if (user.get(i).src.r == r && user.get(i).src.c == c) {
						map[user.get(i).dist.r][user.get(i).dist.c] = 2; // 택시에 탄 손님의 목적지에만 2를 표시한다.
						break;
					}
				}
			}
//			for(int i = 0 ; i <map.length;i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			
			// 아래는 2만 먼저 만나면 그만임
			PriorityQueue<Point> pq = new PriorityQueue<>((e1, e2) -> {
				if (e1.r == e2.r) {
					return e1.c - e2.c;
				} else {
					return e1.r - e2.r;
				}
			});
			int move_cnt = -1;
			int cnt = 0 ;
			pq.add(new Point(r, c));// 현재 택시 위치 큐안에 넣음
			if(map[r][c] == 0) {
				map[r][c] = 1;
			}
			while(!pq.isEmpty()) {
				PriorityQueue<Point> tmp_pq = new PriorityQueue<>((e1, e2) -> {
					if (e1.r == e2.r) {
						return e1.c - e2.c;
					} else {
						return e1.r - e2.r;
					}
				});
				int len = pq.size();
				boolean stop = false;
				for(int i = 0 ; i < len;i++) {
					Point tmp = pq.poll();
					if(map[tmp.r][tmp.c]>=2) {//타겟발견
						r = tmp.r;
						c = tmp.c;
						move_cnt = cnt;
						stop = true;
						break;
					}else { // 길에있음
						for(int j = 0 ; j < 4; j++) {
							if(map[tmp.r+rotate_r[j]][tmp.c+rotate_c[j]]!=1) {
								map[tmp.r+rotate_r[j]][tmp.c+rotate_c[j]]++;
								tmp_pq.add(new Point(tmp.r+rotate_r[j],tmp.c+rotate_c[j]));
							}
						}
					}
				}
				if(stop) {
					break;
				}
				cnt ++;
				pq = tmp_pq;
				
			}
			if(move_cnt==-1||limit<move_cnt) { //가능 방법없거나 연료보다 더 많이 움직여야 함
				limit = -1;
				break;
			}
			
			
			if(target_check) {//승객이 내림
				//System.out.println("remove:"+user.get(user_id));
				user.remove(user_id);
				target_check=!target_check;
				limit += move_cnt;
			}else {//승객을 찾았음
				for(int i = 0 ; i < user.size();i++) {
					if(user.get(i).src.r==r&&user.get(i).src.c==c) {
						user_id=i;
						break;
					}
				}
				target_check=!target_check;
				limit -=move_cnt;
			}
//			System.out.println(r + ","+c+" " + limit + " " + move_cnt);
//			System.out.println(user);
		}
		System.out.println(limit);

	}
}

class User {
	Point src;
	Point dist;

	public User(Point src, Point dist) {
		this.src = src;
		this.dist = dist;
	}

	@Override
	public String toString() {
		return "User [src=" + src + ", dist=" + dist + "]";
	}
	
}

class Point {
	int r;
	int c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Point [r=" + r + ", c=" + c + "]";
	}

}
