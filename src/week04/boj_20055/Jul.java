package week04.boj_20055;
// 컨베이어 벨트 위의 로봇
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Jul {
	static int n,k,result;
	static int[] belt,robot;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		belt = new int[2*n];
		robot = new int[n];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < 2*n; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		
		while (!stopable()) {
			int temp = belt[belt.length-1];
			for (int i = belt.length-1; i >0 ; i--) {
				belt[i] = belt[i-1];
			}
			belt[0] = temp;
			temp = robot[robot.length-1];
			for (int i = robot.length-1; i > 0; i--) {
				robot[i] = robot[i-1];
			}
			robot[0] = 0;
			robot[n-1] = 0;
			for (int i = n-1; i > 0; i--) {
				if(robot[i-1] == 1 && robot[i] == 0 && belt[i] >=1) {
					robot[i] = 1;
					robot[i-1] = 0;
					belt[i] -= 1;
				}
			}
			robot[n-1] = 0;
			
			if(belt[0] != 0) {
				robot[0] = 1;
				belt[0] -= 1;
			}
			result += 1;
		}
		System.out.println(result);
	}
	private static boolean stopable() {
		int zeroCnt = 0;
		for (int i = 0; i < belt.length; i++) {
			if(belt[i] == 0) zeroCnt++;
		}
		if(zeroCnt >=k) return true;
		return false;
	}

}
