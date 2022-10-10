package week09.programmers_word_conversion;

import java.util.ArrayDeque;
import java.util.Queue;

public class Jinsu {
	
	 static class Word{
		 String str;
		 int cnt;
		 public Word(String str, int cnt) {
			this.str = str;
			this.cnt = cnt;
		 }
		 
	 }
	 
	 static boolean[] visited;
	 
	 public int solution(String begin, String target, String[] words) {
	        int answer = 0;
	        
	        visited = new boolean[words.length];
	        answer = bfs(begin, target, 0, words);
	        return answer;
	 }

	private int bfs(String begin, String target, int count, String[] words) {
		Queue<Word> q = new ArrayDeque<>();
		
		q.offer(new Word(begin, 0));
		
		
		while(!q.isEmpty()) {
			Word cur = q.poll();
			
			if(cur.str.equals(target)) {
				return cur.cnt;
			}
			
			for(int i=0; i<words.length; i++) {
				if(visited[i]) continue;
				
				int w = 0;
				for(int idx=0; idx<words[i].length(); idx++) {
					if(cur.str.charAt(idx) != words[i].charAt(idx)) w++;
				}
				
				if(w == 1) {
					visited[i] = true;
					q.add(new Word(words[i], cur.cnt+1));
				}
			}
			
		}
		
		return 0;
	}

}
