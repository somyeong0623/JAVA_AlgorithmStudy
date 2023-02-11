package week24.boj_14725;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Jul {
	static class Node{
		private Map<String,Node> childNodes = new TreeMap<>();

		public Map<String,Node> getChildNodes(){
			return childNodes;
		}
		
		@Override
		public String toString() {
			return "Node [childNodes=" + childNodes + "]";
		}
		
	}
	static class Trie{
		Node rootNode;
		Trie(){
			rootNode = new Node();
		}

		
	}
	static int n;
	static String temp;
	static Trie root;
//	static HashMap<String,ArrayList<Node>> root;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		root = new Trie();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int c = Integer.valueOf(st.nextToken());
			Node d = root.rootNode;
			for (int j = 0; j < c; j++) {
				String n = st.nextToken();
				d.getChildNodes().computeIfAbsent(n, a -> new Node());
				d = d.getChildNodes().get(n);
			}
		}
//		System.out.println(root.rootNode.getChildNodes().get("A"));
		print(root.rootNode,0);
	}
	private static void print(Node root,int count) {
		for (String node : root.getChildNodes().keySet()){
			for (int i = 0; i < count; i++) {
				System.out.print("--");
			}
			System.out.println(node);
			print(root.getChildNodes().get(node),count+1);
		}
		
	}

}
