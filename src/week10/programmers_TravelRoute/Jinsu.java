package week10.programmers_TravelRoute;

import java.util.ArrayList;
import java.util.Collections;

public class Jinsu {
	  ArrayList<String> routes = new ArrayList<>();
	    String route = "";
	    public void dfs(String[][] tickets, String start, boolean[] so, int d) {
	        route += start + " ";
	        if (d == tickets.length) {
	            routes.add(route);
	            return;
	        }
	        for (int i = 0; i < tickets.length; i++) {
	            if (!so[i] && tickets[i][0].equals(start)) {
	                so[i] = true;
	                dfs(tickets, tickets[i][1], so, d + 1);
	                route = route.substring(0, route.length() - 4);
	                so[i] = false;	//백트래킹
	            }
	        }
	    }
	    public String[] solution(String[][] tickets) {
	        boolean[] soldout = new boolean[tickets.length];
	        dfs(tickets, "ICN", soldout, 0);	//인천공항부터 DFS
	        Collections.sort(routes);
	        route = routes.get(0);
	        String[] answer = route.split(" ");
	        return answer;
	    }
}
