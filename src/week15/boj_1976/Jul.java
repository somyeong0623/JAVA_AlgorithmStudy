package week15.boj_1976;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Jul{
	static int n,m;
	static int[] parent,route;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		route = new int[m];
//		System.out.println("parent =>"+Arrays.toString(parent));
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
//					System.out.println(i+"와"+j+" 합치기");
					union(i,j);
//					System.out.println("parent =>"+Arrays.toString(parent));
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}
//		System.out.println("parent =>"+Arrays.toString(parent));
//		System.out.println("route =>"+Arrays.toString(route));
		Set<Integer> result = new HashSet<>();
		for (int i : route) {
			result.add(find(i-1));
		}
//		System.out.println("set =>"+result);
		if(result.size() == 1)System.out.println("YES");
		else System.out.println("NO");
	}
	private static void union(int i, int j) {
		i = find(i);
		j = find(j);
		if(i==j) return;
		if(i<j) parent[j] = i;
		else parent[i] = j;
	}
	private static int find(int j) {
		if(j == parent[j]) return j;
		return parent[j] = find(parent[j]);
	}
}
