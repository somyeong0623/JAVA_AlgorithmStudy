package week13.programmers_SheepNwolf;

import java.util.ArrayList;

public class Jul {

	public static void main(String[] args) {
		System.out.println(solution(new int[] 
				{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
				new int[][]{
				 {0, 1}, {1, 2}, {1, 4}, {0, 8},
				 {8, 7}, {9, 10}, {9, 11}, {4, 3},
				 {6, 5}, {4, 6}, {8, 9}
				}));
		System.out.println(solution(new int[]
				{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}
				, new int[][] {
			{0, 1}, {0, 2}, {1, 3}, {1, 4},
			{2, 5}, {2, 6}, {3, 7}, {4, 8},
			{6, 9}, {9, 10}} 
						));
	}
	static int m;
	static int[][] Edges;
	static int[] Info;
	public static int solution(int[] info, int[][] edges) {
        int answer = 0;
        Edges = edges;
        Info = info;
        ArrayList<Integer> next = new ArrayList<>();
        next.add(0);
        dfs(0,0,0,next,info);
        answer = m;
        return answer;
    }
	private static void dfs(int ver, int sheep, int wolf, ArrayList<Integer> next, int[] info) {
		if(info[ver] == 0) sheep++;
		else wolf++;
		m = Math.max(m, sheep);
		if(sheep<=wolf) return;
		ArrayList<Integer> re_next = new ArrayList<>();
		for (Integer integer : next) {
			if(integer != ver)re_next.add(integer);
		}
		for (int[] is : Edges) {
			if(is[0] == ver)re_next.add(is[1]);
		}
		for (Integer n : re_next) {
			dfs(n, sheep, wolf, re_next, info);
		}
	}
}
