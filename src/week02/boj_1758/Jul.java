package week02.boj_1758;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;


// 알바생 강호
public class Jul {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int tip = 0;
		Integer tips[] = new Integer[n];
		for (int i = 0; i < n; i++) {
			tips[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(tips,Collections.reverseOrder());
		for (int i = 0; i < tips.length; i++) {
			int money = tips[i] - i;
			if (money>=0) {
				tip += money;
			}
		}
		System.out.println(tip);
	}

}
