package week11.programmers_GettingItem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Jul {

	public static void main(String[] args) {
	}
	static int[][] graph;
	public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
	    int answer = 0;
	    graph = new int[103][103];
	    for (int[] is : rectangle) {
	    	for (int i = is[0]*2; i < is[2]*2+1; i++) {
				for (int j = is[1]*2; j < is[3]*2+1; j++) {
					graph[i][j] = 1;
				}
			}
		}
	    ArrayList<int[]> zero = new ArrayList<>();
	    for (int i = 1; i < 101; i++) {
			for (int j = 1; j < 101; j++) {
				if(graph[i][j] == 1 && graph[i+1][j+1] == 1 && graph[i-1][j+1] == 1 &&graph[i+1][j-1] == 1 && graph[i-1][j-1] == 1 && graph[i][j-1] == 1 && graph[i][j+1] == 1 && graph[i-1][j] == 1 && graph[i+1][j] == 1) zero.add(new int[] {i,j});
			}
		}
	    for (int[] is : zero) {
			graph[is[0]][is[1]] = 0;
		}
	    for (int[] is : graph) {
	    	System.out.println(Arrays.toString(is));
	    }
	    boolean[][] visited= new boolean[102][102];
	    Queue<int[]> queue = new ArrayDeque<int[]>();
	    queue.add(new int[] {characterX*2,characterY*2,0});
	    visited[characterX*2][characterY*2] = true;
	    int[] dx = {0,1,0,-1};
	    int[] dy = {1,0,-1,0};
	    while(!queue.isEmpty()) {
	    	int[] temp = queue.poll();
	    	if(temp[0]/2 == itemX && temp[1]/2 == itemY) {
	    		answer = temp[2];
	    		break;
	    	}
	    	for (int i = 0; i < 4; i++) {
				int nx = temp[0]+dx[i];
				int ny = temp[1]+dy[i];
				int nnx = temp[0]+ dx[i]*2;
				int nny = temp[1]+dy[i]*2;
				if(graph[nx][ny] == 1 && graph[nnx][nny] == 1&& !visited[nx][ny]&& !visited[nnx][nny]) {
					visited[nx][ny] = true;
					visited[nnx][nny] = true;
					queue.add(new int[] {nnx,nny,temp[2]+1});
				}
			}
	    }
	    return answer;
	}
}
