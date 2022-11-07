package week13.boj_1062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	
	static int isvisited[];
	static int isselected[];
	static int answer;
	static int n;
	static List<Integer>[]li;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		int m;
		StringTokenizer s=new StringTokenizer(in.readLine()," ");
		n=Integer.parseInt(s.nextToken());
		m=Integer.parseInt(s.nextToken());
		isvisited=new int[26];
		li=new  ArrayList[n];
		for(int i=0;i<n;i++)
		{
			int[] tt=new int[26];
			li[i]=new ArrayList<>();
			char temp[]=in.readLine().toCharArray();
			for(int j=0;j<temp.length;j++)
			{
				int u=temp[j]-'a';
				if(tt[u]==0)
				{
					tt[u]=1;
					li[i].add(u);
				}
			}
		}
		
		answer=Integer.MIN_VALUE;
		if(m==26) {
			
			System.out.println(n);
		}
		else if(m<5)
		{
			System.out.println(0);
		}
		else
		{
			isselected=new int[m-5];
			todo();
			comb(0,0,m-5);
			System.out.println(answer);
		}
		
		
	}


	private static void comb(int start,int cnt, int end) {
		// TODO Auto-generated method stub		
		if(cnt==end)
		{
			check();
			todo();
			return;
		}
		for(int i=start;i<26;i++)
		{
			if(isvisited[i]==1)
				continue;
			isvisited[i]=1;
			isselected[cnt]=i;
			comb(i+1, cnt+1, end);
			isvisited[i]=0;			
		}	
	}


	private static void check() {
		// TODO Auto-generated method stub		
		int count=0;	
		int point=0;		
		for(int i=0;i<n;i++)
		{	
			List<Integer> next=li[i];
			point=0;
			for (Integer j : next) {
				if(isvisited[j]==0)
				{
					point=1;
					break;
				}
			}
			if(point==0)
			{
				count++;
			}			
		}		
		answer=Math.max(answer, count);	
	}
	private static void todo() {
		// TODO Auto-generated method stub
		isvisited[(int)('a'-'a')]=1;
		isvisited[(int)('c'-'a')]=1;
		isvisited[(int)('i'-'a')]=1;
		isvisited[(int)('n'-'a')]=1;
		isvisited[(int)('t'-'a')]=1;
		
	}

}