package week02.boj_15686;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 치킨 배달
public class Jul {
	static int n,chickenHouse,houseSize;
	static int[][] graph;
	static ArrayList<int[]> chickenPosition,cosme;
	static ArrayList<Integer> result,temp;
	static int[] chickenHouseList;
	static boolean[] HouseList;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		chickenHouse = Integer.parseInt(st.nextToken());
		graph = new int[n][n];
		result = new ArrayList<>();
		chickenPosition = new ArrayList<>();
		
		cosme = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 2) {
					int [] t = {i,j};
					chickenPosition.add(t);
				}else if (graph[i][j] == 1) {
					int [] t = {i,j};
					cosme.add(t);
				}
			}
		}
		houseSize = chickenPosition.size();
		chickenHouseList = new int[chickenHouse];
		com(0,0);
		Collections.sort(result);
		System.out.println(result.get(0));

	}
	
	static void com(int index,int count) {
		if (count == chickenHouse) {
			cal(chickenHouseList);
			return ;
		}

		for (int i = index; i < houseSize; i++) {
			chickenHouseList[count] = i;
			com(i+1,count+1);
		}
	}
	
	static void cal(int[] hl) {
		int sum = 0;
		for (int j = 0; j < cosme.size(); j++) {
			temp = new ArrayList<Integer>();
			int cx = cosme.get(j)[0];
			int cy = cosme.get(j)[1];
			for (int i = 0; i < hl.length; i++) {
				int x = chickenPosition.get(hl[i])[0];
				int y = chickenPosition.get(hl[i])[1];
				temp.add(Math.abs(x-cx)+Math.abs(y-cy));
			}
			Collections.sort(temp);
			sum += temp.get(0);
		}
		result.add(sum);
	}
}
