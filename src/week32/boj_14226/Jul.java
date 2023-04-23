package weel32.boj_14226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class Jul {

	static boolean[][] visited = new boolean[2002][2002];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int a = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> queue = new PriorityQueue<int[]>((o1,o2)->o2[0]==o1[0]?o1[2]-o2[2]:o1[0]-o2[0]);
		queue.add(new int[] {1,0,0});
		visited[0][0] = true;
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			System.out.println(Arrays.toString(temp));
			if(temp[0] == a) {
				System.out.println(temp[2]);
				break;
			}
			//				System.out.println(visited[temp[0]][temp[1]]);
			if(temp[0]+temp[1] < 1002 && !visited[temp[0]+temp[1]][temp[1]]) {
				visited[temp[0]+temp[1]][temp[1]] = true;
				queue.add(new int[] {temp[0]+temp[1],temp[1],temp[2]+1});
			}
			if(!visited[temp[0]][temp[0]]) {
				visited[temp[0]][temp[0]] = true;
				queue.add(new int[] {temp[0],temp[0],temp[2]+1});
			}

			if(temp[0]-1 >0 && !visited[temp[0]-1][temp[1]]) {
				visited[temp[0]-1][temp[1]] = true;
				queue.add(new int[] {temp[0]-1,temp[1],temp[2]+1});
			}


		}
	}

}
