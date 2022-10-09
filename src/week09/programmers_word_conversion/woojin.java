import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
package week09.swea_1952;
import java.util.LinkedList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Queue;
class Solution {
    
   static class go {

		String s1;
		int count;

		public go(String s1, int count) {
			this.s1 = s1;
			this.count = count;
		}

	}

	private static int bfs(String begin, String target, String[] words) {

		int answer = 0;

		Queue<go> q = new LinkedList<>();
		q.add(new go(begin, 0));
		while (!q.isEmpty()) {
			go a = q.poll();
			
			if(a.s1.equals(target))
			{
				answer=a.count;
				return answer;
			}
			
			
			
			for(int i1=words.length-1;i1>=0;i1--)
			{
				String a1=words[i1];
				// 한글자만 다르면 넣고 지움

				if (a1.length() == begin.length()) {
					char s1[] = a.s1.toCharArray();
					char s2[] = a1.toCharArray();
					int count = 0;
					for (int i = 0; i < s1.length; i++) {
						if (s1[i] != s2[i]) {
							count++;
							if(count>1)
								break;
						}
			
					}
					if(count==1) {
						q.add(new go(a1,a.count+1));
						words[i1]="";
					}

				}

			}

		}

		return answer;
	}
    
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        answer=bfs(begin,target,words);
        
        
        
        return answer;
    }
}