package week08.programmers_best_album;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Jul {
	static class Pair implements Comparable<Pair>{
		int plays,index;

		public Pair(int plays, int index) {
			super();
			this.plays = plays;
			this.index = index;
		}

		@Override
		public int compareTo(Pair o) {
			return o.plays!=this.plays?o.plays-this.plays:this.index-o.index;
		}
	}
	static class Temp implements Comparable<Temp>{
		String genre;
		int total;
		public Temp(String genre, int total) {
			super();
			this.genre = genre;
			this.total = total;
		}
		@Override
		public int compareTo(Temp o) {
			// TODO Auto-generated method stub
			return o.total-this.total;
		}
	}
	static HashMap<String, PriorityQueue<Pair>> list;
	static HashMap<String, Integer> albem;
	static ArrayList<Integer> result;
	public static void main(String[] args) {
		System.out.println(solution(new String[] {"classic", "pop", "classic", "classic", "pop"}, new int[] {500, 600, 150, 800, 2500}));

	}
	public static int[] solution(String[] genres, int[] plays) {
        int n = genres.length;
        int[] answer = {};
        result = new ArrayList<>();
        list = new HashMap<>();
        albem = new HashMap<>();
        for (int i = 0; i < n; i++) {
        	albem.put(genres[i], albem.getOrDefault(genres[i], 0)+plays[i]);
			list.compute(genres[i], (k,v)-> v==null?new PriorityQueue<>() : v).add(new Pair(plays[i], i));
		}
        PriorityQueue<Temp> t = new PriorityQueue<>();
        for (Entry<String, Integer> temp : albem.entrySet()) {
			t.add(new Temp(temp.getKey(), temp.getValue()));
		}
        while (!t.isEmpty()) {
			Temp temp = t.poll();
			if(list.get(temp.genre).isEmpty())continue;
			Pair pair = list.get(temp.genre).poll();
			result.add(pair.index);
			if(list.get(temp.genre).isEmpty())continue;
			pair = list.get(temp.genre).poll();
			result.add(pair.index);
		}
        answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
			answer[i] = result.get(i);
		}
        System.out.println(Arrays.toString(answer));
        return answer;
    }
}
