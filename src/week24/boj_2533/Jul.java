package week24.boj_2533;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Jul {
	public static class TreeNode{
		int nodeName;
		TreeNode next;
		public TreeNode(int nodeName, TreeNode next) {
			this.nodeName = nodeName;
			this.next = next;
		}
		@Override
		public String toString() {
			return "TreeNode [nodeName=" + nodeName + ", next=" + next + "]";
		}
		
		
	}
	static int n;
	static TreeNode[] tree;
	static boolean[] visited;
	static int[] dp[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		tree = new TreeNode[n+1];
		visited = new boolean[n+1];
		dp = new int[n+1][2];
		for (int i = 0; i < n-1; i++) {
//			System.out.println(root.nodeName);
			st = new StringTokenizer(br.readLine().trim());
			int p = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			tree[p] = new TreeNode(l, tree[p]);
			tree[l] = new TreeNode(p, tree[l]);
		}
		dfs(1);
		System.out.println(tree[4].toString());
		for(TreeNode node = tree[1];node != null;node = node.next) {
			System.out.println(node.nodeName);
		}
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	private static void dfs(int i) {
		visited[i] = true;
		dp[i][0] = 0;
		dp[i][1] = 1;
		System.out.println(i+"번쨰");
		for (int j = 1; j < dp.length; j++) {
			System.out.println(Arrays.toString(dp[j]));
		}
		for(TreeNode node = tree[i]; node != null;node=node.next) {
			System.out.println(node.toString());
			if(visited[node.nodeName])continue;
			System.out.println("통과");
			dfs(node.nodeName);
			dp[i][0] += dp[node.nodeName][1];
			dp[i][1] += Math.min(dp[node.nodeName][0], dp[node.nodeName][1]);
			System.out.println("==결과==");
			for (int j = 1; j < dp.length; j++) {
				System.out.println(Arrays.toString(dp[j]));
			}
		}
	}

}
