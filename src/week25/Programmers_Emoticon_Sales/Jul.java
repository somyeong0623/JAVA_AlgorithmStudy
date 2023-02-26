package week25.Programmers_Emoticon_Sales;

import java.util.Arrays;
public class Jul {

	public static void main(String[] args) {
		solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000});
		solution(new int[][] {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}}, new int[] {1300, 1500, 1600, 4900});

	}
	static int[] discount = {10,20,30,40};
	static int[] emotionDiscount,emo,answer;
	static int[][] user;
    public static int[] solution(int[][] users, int[] emoticons) {
        answer = new int[] {0,0};
        user = users;
        emo = emoticons;
        emotionDiscount = new int[emoticons.length];
//        com = new int[emoticons.length];
        hcombi(0,0,emoticons.length);
        System.out.println(Arrays.toString(answer));
        return answer;
    }
	private static void hcombi(int count, int index, int n) {
		if(count == n) {
//			System.out.println(Arrays.toString(emotionDiscount));
			int[] result = getCal(emotionDiscount);
			if(result[0]>answer[0])answer = result;
			else if(result[0] == answer[0] && result[1] > answer[1])answer[1] = result[1];
//			System.out.println(Arrays.toString(result));
			return ;
		}
		for (int i = 0; i < 4; i++) {
			emotionDiscount[count] = discount[i];
			hcombi(count+1, i, n);
		}
	}
	private static int[] getCal(int[] emotionDiscount2) {
		int account = 0;
		int money = 0;
		for (int[] i : user) {
//			System.out.println("user : " +Arrays.toString(i));
//			System.out.println("할인 률: " +Arrays.toString(emotionDiscount2));
			int tempmoney = 0;
			for (int j = 0; j < emotionDiscount2.length; j++) {
				if(i[0]<=emotionDiscount2[j]) {
//					System.out.println("sale : "+(100-emotionDiscount2[j])*(emo[j]%100));
//					System.out.println("(100-emotionDiscount2[j]) : "+(100-emotionDiscount2[j]));
//					System.out.println("(emo[j]%100) : "+(emo[j]/100));
					tempmoney += (100-emotionDiscount2[j])*(emo[j]/100);
				}
			}
//			System.out.println("tempmon : "+tempmoney);
			if(i[1]<=tempmoney) {
				account++;
			}else {
				money+=tempmoney;
			}
		}
		return new int[] {account,money};
	}
}
