import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb;
	static Node root;
	static StringTokenizer st;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		

		root = new Node();
		int N = Integer.parseInt(bf.readLine());

		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(bf.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			Node tmp = root;
			for(int j = 0 ; j < cnt ; j++) {
				String target = st.nextToken();
				if(!tmp.child.containsKey(target)) {
					tmp.child.put(target, new Node());
				}
				tmp = tmp.child.get(target);
			}
		}
		ArrayList<String> key_list = new ArrayList<>(root.child.keySet());
		Collections.sort(key_list,(str1,str2)->{return str1.compareTo(str2);});
		
		for(String key: key_list) {
			PRINT(root.child.get(key),key,0);
		}
		System.out.println(sb);
	}

	static void PRINT(Node target,String parent,int cnt) {
		for(int i = 0 ; i < cnt ; i++) {
			sb.append("--");
		}
		sb.append(parent).append("\n");
		
		ArrayList<String> key_list = new ArrayList<>(target.child.keySet());
		Collections.sort(key_list,(str1,str2)->{return str1.compareTo(str2);});
		
		for(String key: key_list) {
			PRINT(target.child.get(key),key,cnt+1);
		}
	}
	
	static class Node{
		HashMap<String,Node> child;
		Node(){
			child = new HashMap<String,Node>();
		}
		@Override
		public String toString() {
			return "Node [child=" + child + "]";
		}
		
	}
	
}
