package week05.boj_19535;
// ㄷㄷㄷㅈ
import java.util.*;
import java.io.*;
public class Jul {

	static int n;
	static long dCount,gCount;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> ggraph;
	static int[][] line;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		ggraph = new ArrayList<>();
		for (int i = 0; i < n+1; i++) {
			ggraph.add(new ArrayList<>());
		}
		line = new int[n-1][2];
		for(int i = 0; i <n-1;i++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			ggraph.get(from).add(to);
			ggraph.get(to).add(from);
			line[i][0]= from;
			line[i][1]= to;
		}
//		System.out.println(ggraph);
		for (int[] temp : line) {
			int f = temp[0];
			int t = temp[1];
			int left = ggraph.get(f).size()-1;
			int right = ggraph.get(t).size()-1;
//			System.out.println(left+" "+right);
			dCount += (long)left*right;

			
		}

		for (int i = 1; i < n+1; i++) {
			int t = ggraph.get(i).size();
			if(t>=3) {
				// 곱할꺼
				long d= 1;
				int index= t;
				// 나눌꺼
				long md = 1;
				int mindex = 1;
				for (int j = 0; j < 3; j++) {
					d*= (long) index--;
					md *=(long) mindex++;
				}
				
				gCount +=  d/md;
			}
		}
//		System.out.println(dCount);
//		System.out.println(gCount);
		if(dCount>gCount*3) {
			System.out.println("D");
		}else if(dCount<gCount*3) {
			System.out.println("G");
		}else if(dCount==gCount*3) {
			System.out.println("DUDUDUNGA");
		}
		
	}


}
