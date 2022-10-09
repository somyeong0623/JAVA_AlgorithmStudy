package week09.programmers_word_conversion;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;

public class Jul {
	static class Node{
		String str;
		int count;
		public Node(String str, int count) {
			super();
			this.str = str;
			this.count = count;
		}
		
	}
    public static int solution(String begin, String target, String[] words) {
        int answer = 0;
        answer = bfs(begin,target,0,words);
        return answer;
    }
	private static int bfs(String begin, String target, int count, String[] words) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(begin, 0));
		HashSet<String> visited = new HashSet<>();
		visited.add(begin);
		while (!queue.isEmpty()) {
			Node temp = queue.poll();
			if(temp.str.equals(target)) {
				return temp.count;
			}
			for (String string : words) {
				if(visited.contains(string))continue;
				int d = 0;
				for (int i = 0; i < string.length(); i++) {
					if(temp.str.charAt(i)!=string.charAt(i))d++;
				}
				if(d==1) {
					queue.add(new Node(string, temp.count+1));
					visited.add(string);
				}
			}
		}
		return 0;
	}
}
