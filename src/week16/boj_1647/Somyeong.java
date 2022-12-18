package week16.boj_1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Somyeong {
	static class Info implements Comparable<Info>{
		int x,y,cost;
		public Info(int x, int y ,int cost) {
			this.x=x;
			this.y=y;
			this.cost=cost;
		}
		public int compareTo(Info o) { // cost기준 오름차순 정렬 
			return this.cost-o.cost; 
		}
	}
	static int N,M;
	static int parent[];
	static int answer,cnt;


	public static void main() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		parent = new int[N];
		ArrayList<Info> edges = new ArrayList<Info>();
		ArrayList<Integer> costs = new ArrayList<Integer>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y= Integer.parseInt(st.nextToken());
			int cost= Integer.parseInt(st.nextToken());
			
			edges.add(new Info(x,y,cost));
		}
		
		for(int i=1; i<=N; i++) {
			parent[i]=i;
		}
		cnt=1;
		
		Collections.sort(edges);
		for(int i=0; i<M; i++) {
			Info cur = edges.get(i);
			int x=cur.x;
			int y=cur.y;
			if(getParent(x)==getParent(y))
				continue;
			union(x,y);
			answer+=cur.cost;
			cnt++;
		}
		
		System.out.println("cnt: "+cnt);
		
		
	}
	static public int getParent(int x) {
		if(parent[x]==x)
			return x;
		
		return parent[x]=getParent(parent[x]);
	}
	
	static public void union(int x,int y) {
		x = getParent(x);
		y= getParent(y);
		
		parent[y]=x;
		
	}
}
