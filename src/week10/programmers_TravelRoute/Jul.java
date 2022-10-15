package week10.programmers_TravelRoute;

import java.util.Arrays;
import java.util.Comparator;

public class Jul {
	public static void main(String[] args) {
//		System.out.println(solution(new String[][] {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}}));
//		System.out.println(solution(new String[][] {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}}));
		System.out.println(solution(new String[][] 	{{"ICN", "B"}, {"B", "ICN"}, {"ICN", "A"}, {"A", "D"}, {"D", "A"}}));
	}
	static boolean flag = false;
	public static String[] solution(String[][] tickets) {
		String[] answer = new String[tickets.length+1];
		boolean[] visited= new boolean[tickets.length];
		int index = 0;
		Arrays.sort(tickets,new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				// TODO Auto-generated method stub
				return o1[1].compareTo(o2[1]);
			}
		});
		for (String[] strings : tickets) {
			System.out.println(Arrays.toString(strings));
		}
		String depart = "ICN";
//		visited[index] = true;
		answer[index++] = depart;
		dfs(depart,1,answer,tickets,visited,tickets.length+1);
//		for (int j = 0; j < answer.length; j++) {
//
//			for (int i = 0; i < tickets.length; i++) {
//				if(visited[i]) continue;
//				if(tickets[i][0].equals(depart)) {
//					visited[i] = true;
//					depart = tickets[i][1];
//					answer[index++] = depart;
//					continue;
//				}
//			}
//		}
		System.out.println(Arrays.toString(answer));
		return answer;
	}
	private static void dfs(String depart, int count, String[] answer, String[][] tickets, boolean[] visited, int r) {
		System.out.println(depart+" !! "+count+" "+Arrays.toString(answer)+" "+Arrays.toString(visited));
		if(count == r) {
			System.out.println("도착!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			flag = true;
			return ;
		}
		for (int i = 0; i < tickets.length; i++) {
			if(visited[i]) continue;
			if(tickets[i][0].equals(depart)) {
				visited[i] = true;
				answer[count] = tickets[i][1];
				dfs(tickets[i][1],count+1,answer,tickets,visited,r);
				System.out.println(tickets[i][1]+" 실패 "+i);
				if(flag) return;
				visited[i] = false;
				answer[count] = null;
				System.out.println(depart+" "+count+" "+Arrays.toString(answer)+" "+Arrays.toString(visited));
			}
		}
		return ;
	}
}
