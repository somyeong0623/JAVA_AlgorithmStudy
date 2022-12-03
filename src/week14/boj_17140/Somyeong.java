package week14.boj_17140;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

//17140. 이차원 배열과 연산 

public class Somyeong {
	static class Info implements Comparable<Info> {
		int num, cnt; // 숫자, 등장 횟수

		public Info(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Info [num=" + num + ", cnt=" + cnt + "]";
		}

		// 등장 횟수 기준 오름차순, 숫자 기준 오름차순 
		public int compareTo(Info o) {
			if (this.cnt == o.cnt)
				return this.num - o.num;
			return this.cnt - o.cnt;
		}

	}

	static int target_r, target_c, k; // 입력 받는 세 변수
	static int r, c; // 행의 갯수 열의 갯수
	static int arr[][];
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		target_r = Integer.parseInt(st.nextToken()) - 1;
		target_c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());

		arr = new int[3][3];

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//초기값 
		r = 3; c = 3;
		while (true) {
			if (answer > 100) {
				answer = -1;
				break;
			}

			// (target_r,target_c) 좌표값이 k이면 반복문 종료 
			// target_r, target_c의 범위 체크 안해주면 IndexOutOfBoundsException 남 
			if (target_r < r && target_c < c && arr[target_r][target_c] == k)
				break;

			answer++;
			if (r >= c) // R연산 하는 경우 
				operationR();
			else // C연산 하는 경우 
				operationC();
		}

		System.out.println(answer);

	}

	public static void operationR() { // R 연산
		// r은 고정, c만 변화 가능
		
		HashMap<Integer, Integer>[] map = new HashMap[r]; // 한 행에서 각 숫자가 몇개있는지 map에 저장 
		ArrayList<Info> infos[] = new ArrayList[r];// 한 행에 대한 map의 정보를 리스트배열에 옮겨서 문제에 주어진 조건대로 정렬 
		int maxC = 0;
		for (int i = 0; i < r; i++) {
			if (i >= 100)
				break;
			map[i] = new HashMap<Integer, Integer>();
			infos[i] = new ArrayList<Info>();

			for (int j = 0; j < c; j++) {
				if (j >= 100)
					break;
				int cur = arr[i][j];
				if (cur == 0)
					continue;

				if (map[i].containsKey(cur))
					map[i].put(cur, map[i].get(cur) + 1);
				else
					map[i].put(cur, 1);

			}
			 // 새로운 열의 길이는 map에 담긴 key의 종류 *2가 된다. 
			maxC = Math.max(maxC, map[i].size());
			for (Integer x : map[i].keySet()) { // 한 행의 정보를 Info형의 list로 옮기기 
				infos[i].add(new Info(x, map[i].get(x)));
			}
		}
		c = maxC * 2;
		arr = new int[r][c]; // 새로 만들어진 배열의 크기대로 할당 
		for (int i = 0; i < r; i++) {
			Collections.sort(infos[i]); //주어진 정렬 기준에 따라서 정렬 
			int index = 0;
			//정렬한 결과를 토대로 한 행씩 새로 채워넣기 
			for (int j = 0; j < infos[i].size(); j++) { 
				arr[i][index++] = infos[i].get(j).num;
				arr[i][index++] = infos[i].get(j).cnt;
			}
		}

	}

	// opeationR와 수행과정은 전부 같고, 열에 대해서 정렬을 수행한다는 것만 달라짐 (그러므로 풀이 생략) 
	public static void operationC() { // C 연산
		// c는 고정, r만 변화 가능
		HashMap<Integer, Integer>[] map = new HashMap[c];
		ArrayList<Info> infos[] = new ArrayList[c];
		int maxR = 0;
		for (int i = 0; i < c; i++) {
			if (i >= 100)
				break;

			map[i] = new HashMap<Integer, Integer>();
			infos[i] = new ArrayList<Info>();

			for (int j = 0; j < r; j++) {
				if (j >= 100)
					break;
				int cur = arr[j][i];
				if (cur == 0)
					continue;

				if (map[i].containsKey(cur))
					map[i].put(cur, map[i].get(cur) + 1);
				else
					map[i].put(cur, 1);

			}
			maxR = Math.max(maxR, map[i].size());
			for (Integer x : map[i].keySet()) {
				infos[i].add(new Info(x, map[i].get(x)));
			}
		}
		r = maxR * 2;
		arr = new int[r][c];
		for (int i = 0; i < c; i++) {
			Collections.sort(infos[i]);
			int index = 0;
			for (int j = 0; j < infos[i].size(); j++) {
				arr[index++][i] = infos[i].get(j).num;
				arr[index++][i] = infos[i].get(j).cnt;
			}
		}


	}

}
