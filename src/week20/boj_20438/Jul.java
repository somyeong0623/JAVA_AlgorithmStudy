package week20.boj_20438;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Jul {
	static int n,k,q,m,s,e;
	static PriorityQueue<int[]> msg,sleep;
	static int[] acc,sp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		sleep = new PriorityQueue<>((o1,o2)->o1[0]*o1[1]-o2[0]*o2[1]);
		msg = new PriorityQueue<>((o1,o2)->o1[0]*o1[1]-o2[0]*o2[1]);
		acc = new int[n+3];
		sp = new int[n+3];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			sleep.add(new int[] {Integer.parseInt(st.nextToken()),1});
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < q; i++) {
			msg.add(new int[] {Integer.parseInt(st.nextToken()),1});
		}
		while (!sleep.isEmpty()) {
			int[] temp = sleep.poll();
			sp[temp[0]] = -1;
		}
		while (!msg.isEmpty()) {
			int[] temp = msg.poll();
//			System.out.println("temp : "+Arrays.toString(temp));
			if(sp[temp[0]]==-1)continue;
			if(temp[0]*(temp[1]) > n+2) continue;
			else {
				if(sp[temp[0]*temp[1]] == 0)acc[temp[0]*temp[1]] = 1;
				if(temp[0]*(temp[1]+1) >= n+3) continue;
				else msg.add(new int[] {temp[0],temp[1]+1});
			}
		}
		for (int i = 4; i < n+3; i++) {
			acc[i] += acc[i-1];
		}
//		System.out.println(acc.length);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			System.out.println(e-s+1-(acc[e]-acc[s-1]));
		}
		
	}

}
