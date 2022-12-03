package week13;

import java.util.*;

class Sell {
	String name;
	int money;

	public Sell(String name, int money) {
		this.name = name;
		this.money = money;
	}
}

class Solution {
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

		int n = enroll.length;
		int[] answer = new int[n]; 
		HashMap<String, String> map = new HashMap<String, String>(); // (참여자, 추천자) 매핑하여 저장 
		HashMap<String, Integer> money = new HashMap<String, Integer>(); // (참여자, 참여자의 이익금 총합) 
		ArrayList<Sell> sells = new ArrayList<Sell>(); // 처음엔 판매정보(누가 얼마나팔았는지)를 Map으로 했으나 seller에는 이름이 중복될수 있으므로 리스트에 담음 

		for (int i = 0; i < n; i++) { // money map 0원으로 초기화 
			money.put(enroll[i], 0);
		}

		for (int i = 0; i < n; i++) { // (판매자,추천자) 정보 저장 
			map.put(enroll[i], referral[i]);
		}

		for (int i = 0; i < seller.length; i++) { // 이익발생 리스트 
			sells.add(new Sell(seller[i], amount[i] * 100));
		}

		String curName = "";
		int curAmount = 0;

		for (int i = 0; i < sells.size(); i++) { // 모든 이익에 대하여 아래과정 처리 

			Sell cur = sells.get(i);
			curName = cur.name;
			curAmount = cur.money;
			while (true) {

				if (curAmount * 0.1 < 1) { // 현재 금액의 10%를 원단위로 절사할수 없으면 현재금액 반영하고 반복문 종료 
					money.put(curName, money.get(curName) + curAmount);
					break;
				}

				int temp = money.get(curName) + curAmount - (int) (curAmount * 0.1); // 현재 사람의 추천자에게 넘어가는 금액 
				// 이때 1원단위로 절사하는것이므로 10%와 90%를 각각 정수단위로 나누면 안됨.
				// 문제 그대로 10%를 추천자를향해 넘겨준 후 (현재사람) = (전체금액) - (넘겨준금액) 으로 계산해야함. 
				
				money.put(curName, temp); //  현재 사람의 총이익 갱신 
				curAmount /= 10; // 추천자쪽으로 넘어갈 이익 
				curName = map.get(curName); // 추천자로 업데이트 
				
				if (curName.equals("-")) { // 다음텀에 살펴봐야할 참여자가 center이면 종료 
					break;
				}

			}

		}

		for (int i = 0; i < n; i++) {
			answer[i] = money.get(enroll[i]);
		}

		return answer;
	}
}