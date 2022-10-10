import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1561 {
	static long n;
	static int m;
	static int gp[];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer s = new StringTokenizer(in.readLine(), " ");

		n = Long.parseLong(s.nextToken());
		m = Integer.parseInt(s.nextToken());
		gp = new int[m];

		s = new StringTokenizer(in.readLine(), " ");
		for (int j = 0; j < m; j++) {
			gp[j] = Integer.parseInt(s.nextToken());
		}

		if (n <= m)
			System.out.println(n);
		else {
			long z = binarysearch();
			// z는 구해온 초임
			//System.out.println(z);
			// 현재 타임에 들어갈수 있느 사람수

			long sum = m;
			long sum1 = n;
			ArrayList<Integer> e = new ArrayList<>();
			for (int i = 0; i < m; i++) {
				sum += (z - 1) / gp[i];

			}

			

			// 빈공간을 탐색하여 구멍 체크

			for (int i = 0; i < m; i++) {
				if (z % gp[i] == 0) {
					sum++;
				}

				if (sum == n) {
					System.out.println(i + 1);
					break;
				}
			}
		}

	}

	private static long binarysearch() {
		// TODO Auto-generated method stub

		long left = 1;
		long right = 30*n;

		while (left <= right) {

			long mid = (left + right) / 2;

			// 사람수를 체크 해야 하네

			long sum = m;
			for (int i = 0; i < m; i++) {
				sum += (mid) / gp[i];

			}

			if (sum < n) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}

		}

		return left;
	}
}