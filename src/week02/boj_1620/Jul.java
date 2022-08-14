package week02.boj_1620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// 나는야 포켓몬 마스터 이다솜
public class Jul {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Map<Integer, String> dogam = new HashMap<>();
		Map<String,Integer> sub = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			String input = br.readLine();
			dogam.put(i, input);
			sub.put(input, i);
		}
		for (int i = 0; i < m; i++) {
			String search = br.readLine();
			// true 숫자 | false 문자
			boolean flag = true;
			for (int j = 0; j < search.length(); j++) {
				if (!Character.isDigit(search.charAt(j))) {
					flag = false;
					break;
				}
			}
			if (flag) {
				System.out.println(dogam.get(Integer.parseInt(search)));
			}else {
				System.out.println(sub.get(search));
//				for (Integer dogamValue : dogam.keySet()) {
////					System.out.println(dogamValue);
//					if (dogam.get(dogamValue).equals(search)) {
//						System.out.println(dogamValue);
//						break;
//					}
//				}
			}
		}
	}

}
