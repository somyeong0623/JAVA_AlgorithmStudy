	import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Info {
	int r;
	int c;

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public Info(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}

}

//15686. 치킨배달. 깃에올리기  (수업시간에 한 코드로 이해함. )
public class Somyeong{

	static int n, m; // 2차원 배열 길이, 치킨집 최대 갯수
	static int[][] arr;// 도시 정보
	// 0: 빈칸,1: 집, 2: 치킨집

	static ArrayList<Info> home; // 집 좌표 담을 리스트
	static ArrayList<Info> chicken;// 치킨집 좌표 담을 리스트
	static int[] select; // 고른 치킨집의 인덱스를 담아둠
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][n];
		home = new ArrayList<>();
		chicken = new ArrayList<>();
		select = new int[m];
		

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					home.add(new Info(i, j));
				} else if (arr[i][j] == 2) {
					chicken.add(new Info(i, j));
				}
			}
		}
		
		
		comb(0, 0);
		System.out.println(answer);

	}
	
	

	private static void comb(int cnt, int start) {
		if (cnt == m) {
			int temp = 0;
			int min_chicken = Integer.MAX_VALUE; // 각 집에서 가장 가까운 치킨집

			for (int i = 0; i < home.size(); i++) {
				min_chicken=Integer.MAX_VALUE;
				for (int j = 0; j < m; j++) {
					min_chicken = Math.min(min_chicken, Math.abs(home.get(i).r - chicken.get(select[j]).r)
							+ Math.abs(home.get(i).c - chicken.get(select[j]).c));
				}
				temp+=min_chicken;
			}
			answer = Math.min(answer, temp);
			return;

		}
		for (int i = start; i < chicken.size(); i++) {

			select[cnt] = i;
			comb(cnt + 1, i + 1);
		}

	}

}