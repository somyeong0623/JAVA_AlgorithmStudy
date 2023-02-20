import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer>[] tree;
	static boolean[] visit;
	static int[] early;
	static int[] nomal;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(bf.readLine());
		tree= new ArrayList[N];
		visit = new boolean[N];
		early = new int[N];
		nomal = new int[N];
		for(int i = 0; i < N ; i++ ) {
			tree[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < N-1 ; i++ ) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			tree[a].add(b);
			tree[b].add(a);
		}
		
		//System.out.println(Arrays.toString(tree));
		dfs(0);
		System.out.println(Math.min(early[0], nomal[0]));
	}
	static void dfs(int target) {
		visit[target] = true;
		early[target] = 1;
		nomal[target] = 0;
		
		for(int i = 0 ; i < tree[target].size() ; i++) {
			int next = tree[target].get(i);
			if(visit[next]) {
				continue;
			}
			dfs(next);
			nomal[target] += early[next];
			early[target] += Math.min(early[next], nomal[next]);
		}
	}
	
}
