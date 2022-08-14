package week02.boj_5397;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
// 키로거

public class Jul {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			Deque<Character> a = new LinkedList<>();
			Deque<Character> b = new LinkedList<>();
			
			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == '<') {
					if (!a.isEmpty()) {
						b.addFirst(a.pollLast());;
					}
				}else if (str.charAt(j) == '>') {
					if (!b.isEmpty()) {
						a.addLast(b.pollFirst());
					}
					
				}else if (str.charAt(j) == '-') {
					if (!a.isEmpty()) {
						a.pollLast();
					}
				}else {
					a.addLast(str.charAt(j));
				}

			}

			int range = a.size();
			for (int j = 0; j < range; j++) {
				sb.append(a.pollFirst());
			}
			range = b.size();
			for (int j = 0; j < range; j++) {
				sb.append(b.pollFirst());
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
