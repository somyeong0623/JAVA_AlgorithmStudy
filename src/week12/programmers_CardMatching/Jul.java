package week12.programmers_CardMatching;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;

public class Jul {

	public static void main(String[] args) {
		System.out.println(solution(new int[][] {{1, 0, 0, 3}, {2, 0, 0, 0}, {0, 0, 0, 2}, {3, 0, 1, 0}}, 1, 0));
		//		System.out.println(solution(new int[][] {{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}}, 0, 1));
	}
	static int[] input,cardOrder;
	static boolean[] visited;
	static int q,R,C,result;
	static int[][] graph;
	public static int solution(int[][] board, int r, int c) {
		int answer = 0;
		graph = board;
		R=r;
		C=c;
		input = new int[8];
		cardOrder = new int[8];
		visited = new boolean[8];
		boolean[] list = new boolean[9];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(board[i][j]==0||list[board[i][j]])continue;
				input[q++] = board[i][j];
				list[board[i][j]] = true;
			}
		}
		System.out.println(Arrays.toString(input));
		System.out.println(q);
		permu(0);
		return answer;
	}
	private static void permu(int count) {
//		System.out.println(Arrays.toString(cardOrder)+" "+count);
		if(count == q) {
			System.out.println(Arrays.toString(cardOrder));
			result = Math.max(result, makeAnswer());
			return;
		}
		for (int i = 0; i < q; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			cardOrder[count] = input[i];
			permu(count+1); 
			visited[i] = false;
		}
		
	}
	private static int makeAnswer() {
		return 0;
		
	}
	
}
