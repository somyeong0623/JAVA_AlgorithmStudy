import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
package week09.swea_1952;
public class swea_1952 {

	static int month[];
	static int plan[];
	static StringBuffer buffer;
	static int result = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		buffer = new StringBuffer();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine());
		for (int i = 1; i <= t; i++) {
			StringTokenizer s = new StringTokenizer(in.readLine(), " ");
			month = new int[12];
			plan = new int[4];
			result = Integer.MAX_VALUE;
			for (int j = 0; j < 4; j++) {
				plan[j] = Integer.parseInt(s.nextToken());

			}
			s = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < 12; j++) {
				
				month[j] = Integer.parseInt(s.nextToken());

			}
			//System.out.println(Arrays.toString(month));
			//System.out.println(Arrays.toString(plan));
			dfs(0, 0);
			result=Math.min(result, plan[3]);
			buffer.append("#" + i + " " + result + "\n");

		}
		System.out.println(buffer);

	}

	private static void dfs(int start, int money) {
		// TODO Auto-generated method stub

		if (start >= 12) {
			result = Math.min(money, result);
			return;
		}

		if (money > result) {
			return;
		}

		// 1일,1달짜리 를 비교를 하고
		int oneday = plan[0] * month[start];
		int onemonth = plan[1];
		int check = Math.min(oneday, onemonth);
		dfs(start + 1, check + money);
		// 이제는 3달짜리로 계산을 해서 넘겨줌
		int threemonth = plan[2];
		dfs(start + 3, money + threemonth);

	}

}
