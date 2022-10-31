package week12.programmers_CardMatching;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int[] isvisited, numbers;
	static int[] last;
	static int size = 0;
	static int answer;
	static int nowx;
	static int nowy;

	static class go {
		int x;
		int y;
		int z;

		go(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public go(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

	}


	public static int solution(int[][] board, int r, int c) {
		answer = Integer.MAX_VALUE;
		isvisited = new int[7];
		numbers = new int[7];

		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] > 0) {
					if (numbers[board[i][j]] == 0) {
						numbers[board[i][j]] = 1;
						count++;
					}
				}
			}
		}
		last = new int[count];
		permutation(count, board, r, c, 0);

		return answer;

	}

	private static void permutation(int end, int[][] board, int r, int c, int cnt) {
		// TODO Auto-generated method stub
		if (cnt == end) {
			//System.out.println(Arrays.toString(last));
			// 이제 이상태로 게임을 진행 하면 되겠다
			game(board, r, c, last);
			return;
		}

		for (int i = 0; i < 7; i++) {
			if (isvisited[i] == 1)
				continue;
			if (numbers[i] == 1) {
				isvisited[i] = 1;
				last[cnt] = i;
				permutation(end, board, r, c, cnt + 1);
				isvisited[i] = 0;
			}

		}

	}

	private static void game(int[][] board, int r, int c, int[] last2) {
		int copy_board[][] = new int[4][4];
		int rere = 0;
		// 복사하는 과정
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copy_board[i][j] = board[i][j];
			}
		}
		nowx = r;
		nowy = c;
		// 이제 board에 왔고 last에따라서 찾고 찾는 과정을 통해 진행을 해보도록 해야겠다.
		for (int i = 0; i < last2.length; i++) {
			
			int last_now = last2[i];
			// 두번의 bfs를 통해서 가장 가까운거 찾고 그다음 찾도록 해야겠다
			for (int j = 0; j < 2; j++) {
				rere += bfs(copy_board, last_now);
			}

		}
		//System.out.println(rere);
		answer=Math.min(answer, rere);
	}

	private static int bfs(int[][] copy_board, int last_now) {
		// TODO Auto-generated method stub

		// 이제 주어진 last_now를 찾고자 해야함
		Queue<go> q = new LinkedList<>();
		q.add(new go(nowx, nowy, 0));
		int dx[] = { -1, 0, 1, 0 };
		int dy[] = { 0, 1, 0, -1 };
		int visit[][] = new int[4][4];
		int u = 0;
		visit[nowx][nowy] = 1;

		while (!q.isEmpty()) {
			go a = q.poll();
			if (copy_board[a.x][a.y]==last_now) {
				copy_board[a.x][a.y]=0;
				nowx = a.x;
				nowy = a.y;
				return a.z + 1;
			}
			// 상하좌우로 이동이 가능하며
			for (int i = 0; i < 4; i++) {
				int zx = a.x + dx[i];
				int zy = a.y + dy[i];
				// 해당 방향으로 한칸만 이동을 한상태이고
				int chch=0;
				if (zx >= 0 & zx < 4 && zy >= 0 && zy < 4) {
					if (visit[zx][zy] == 0) {
						q.add(new go(zx, zy, a.z + 1));
						visit[zx][zy] = 1;
						if(copy_board[zx][zy]>0)
							chch=-1;
					}
				}
			
					if (chch == 0 && zx >= 0 & zx < 4 && zy >= 0 && zy < 4) {
					chch = 1;
                        // 해당 방향으로 한번더 이동을 해야겠지
					if(copy_board[zx][zy]>0&&visit[zx][zy]==1)
						continue;
					
					zx = zx + dx[i];
					zy = zy + dy[i];
					while (zx >= 0 & zx < 4 && zy >= 0 && zy < 4) {
						if (copy_board[zx][zy] > 0&&visit[zx][zy]==0) {
							q.add(new go(zx, zy, a.z + 1));
							visit[zx][zy] = 1;
							chch = 2;
							break;
						}
						zx = zx + dx[i];
						zy = zy + dy[i];
					}
				}
				if (chch == 1) {
					zx = zx - dx[i];
					zy = zy - dy[i];
					if(visit[zx][zy]==0) {
					q.add(new go(zx, zy, a.z + 1));
					visit[zx][zy] = 1;
					}
				}

			}
			// 각 방향으로 끝까지 이동해서 찾아낼수도 있음

		}

		return 0;
	}

}