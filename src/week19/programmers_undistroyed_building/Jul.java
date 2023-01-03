package week19.programmers_undistroyed_building;

import java.util.Arrays;

public class Jul {

	public static void main(String[] args) {
		System.out.println(solution(new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}}, new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}}));
		System.out.println(solution(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},new int[][] {{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}}));

	}
	public static int solution(int[][] board, int[][] skill) {
		int answer = 0;
		int[][] skill_building = new int[board.length+1][board[0].length+1];
		for (int i = 0; i < skill.length; i++) {
			int[] temp = skill[i];
			int type = temp[0];
			int x = temp[1];
			int y = temp[2];
			int x2 = temp[3];
			int y2 = temp[4];
			int degree = temp[5];
			int attack = -1;
			int hill = 1;
			int t = 0;
			if(type == 1) t = attack;
			else t = hill;
			skill_building[x][y] += t*degree;
			skill_building[x][y2+1] += -t*degree;
			skill_building[x2+1][y] += -t*degree;
			skill_building[x2+1][y2+1] += t*degree;
//			1, 0, 0, 3, 4, 4
//			System.out.println(Arrays.toString(temp));
		}
		for (int[] is : skill_building) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println("================================");
		for (int i = 0; i < skill_building.length; i++) {
			for (int j = 1; j < skill_building[i].length; j++) {
				skill_building[i][j] += skill_building[i][j-1];
			}
		}
		for (int[] is : skill_building) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println("================================");
		for (int i = 0; i < skill_building[0].length; i++) {
			for (int j = 1; j < skill_building.length; j++) {
				skill_building[j][i] += skill_building[j-1][i];
			}
		}
		for (int[] is : skill_building) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println("================================");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] += skill_building[i][j];
				if(board[i][j]>0) answer++;
			}
		}
		for (int[] is : board) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println("================================");
		return answer;
	}

}
