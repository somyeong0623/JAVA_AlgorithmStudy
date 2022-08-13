package week02.boj_5397;

import java.util.Scanner;
import java.util.Stack;


public class Jinsu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<n; i++) {
			String[] input;
			input = sc.next().split("");
			
			Stack<String> left = new Stack<>();
			Stack<String> right = new Stack<>();
			
			for (String s : input) {
				switch (s) {
				case "<":
					if(!left.isEmpty()) right.push(left.pop());
					break;
					
				case ">":
					if(!right.isEmpty()) left.push(right.pop());
					break;
					
				case "-":
					if(!left.isEmpty()) left.pop();
					break;
					
				default:
					left.push(s);
					break;
				}
			}
			while (!left.isEmpty()) {
				right.push(left.pop());	
			}
			while(!right.isEmpty()) {
				sb.append(right.pop());
			}
			sb.append("\n");
		}
		System.out.println(sb);
		/* 틀렸습니다.
		for (String string : left) {
			sb.append(string);
		}
		sb.append("\n");
	}
	System.out.println(sb);
	
	*/
		/* 런타임 에러 (IndexOutOfBounds)
		int n = sc.nextInt();
		
		for(int i=0; i<n; i++) {
			String password = sc.next();
			char[] array = password.toCharArray();
			ArrayList<Character> s = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			
			int index=0;
			
			for (int j=0; j<array.length; j++) {
				switch (array[j]) {
				case '<':
					if (index==0) continue;
					else index--;
					break;
				case '>':
					if (index==s.size()) continue;
					else index++;
					break;
				case '-':
					if (s.isEmpty()) continue;
					else s.remove(s.size()-1);
					break;
					
				default:
					s.add(index, array[j]);
					index++;
					break;
				}
			}
			for(int j=0; j<s.size(); j++) {
				sb.append(s.get(j));
			}
			System.out.println(sb);
		} */
	}

}