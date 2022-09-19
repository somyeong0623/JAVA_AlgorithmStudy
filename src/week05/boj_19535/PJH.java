package week05.boj_19535;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class PJH{//19535
	static Node[] node;
	static long look_d;
	static long look_g;
	static long[][] memo;
	static ArrayList<Edge> edge;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(bf.readLine());
		look_d = 0;
		look_g = 0;
		
		
		node = new Node[N];
		edge = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			node[i] = new Node();
		}
		
		for(int i = 0; i < N-1 ; i++) {
			st = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			node[s].next.add(d);
			node[d].next.add(s);
			
			edge.add(new Edge(s,d));
		}		
		
		int max_edge = 0;
		
		for(int i = 0 ; i < N ; i++) {
//			node[i].visit = true;
//			DFS(i,1);
//			node[i].visit = false;
			max_edge = Math.max(node[i].next.size(), max_edge);
		}
		
		for(Edge tmp:edge) {
			int s_len = node[tmp.s].next.size();
			int d_len = node[tmp.d].next.size();
			if(s_len>1 && d_len>1) {
				look_d += (s_len-1)*(d_len-1);
			}
		}
		
		memo = new long[max_edge+1][4];

		
		for(int i = 0 ; i < N ; i++) {
			if(node[i].next.size()>=3) {
				look_g += combi(node[i].next.size(),3);
			}
		}
		
		
		//System.out.println(look_d + " " + look_g);
		
		//look_d /= 2;
		look_g *= 3;
		if(look_d==look_g) {
			System.out.println("DUDUDUNGA");
		}else if(look_d>look_g) {
			System.out.println("D");
		}else {
			System.out.println("G");
		}
		
	}
	public static long combi(int n, int c) {
		//System.out.println(n + ","+c);
		if(n==c||c==0) {
			return 1;
		} else if(memo[n][c]!=0) {
			return memo[n][c];
		} else {
			memo[n][c] = combi(n-1,c-1)+combi(n-1,c);
			return memo[n][c];
		}
	}
	
	
//	public static void DFS(int next,int depth) {
//		if(depth==4) {
//			look_d++;
//			return ; 
//		}
//		
//		for(int tmp : node[next].next) {
//			if(!node[tmp].visit) {
//				node[tmp].visit = true;
//				DFS(tmp,depth+1);
//				node[tmp].visit = false;
//			}
//		}
//		
//	}
	
}
class Edge{
	int s;
	int d;
	public Edge(int s,int d) {
		this.s = s;
		this.d = d;
	}
}
class Node{
	ArrayList<Integer> next;
	//boolean visit;
	public Node() {
		next = new ArrayList<>();
		//visit = false;
	}
	
}