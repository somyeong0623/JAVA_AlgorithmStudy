package weel32.boj_13701;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Jul {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashSet<Integer> hs = new HashSet<>();
		BitSet b = new BitSet();
		while (st.hasMoreTokens()) {
			int n = Integer.parseInt(st.nextToken());
//			if(!hs.contains(n)) {
//				sb.append(n+" ");
//				hs.add(n);
//			}
			if(!b.get(n)) {
				sb.append(n+" ");
				b.set(n);
			}
		}
		System.out.println(sb);
	}

}
