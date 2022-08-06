package week01.boj_17478;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PJH { // Main으로 변경
	static String qustion = "\"재귀함수가 뭔가요?\"";
	static String[] answer = { "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
			"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.", "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"" };

	static String end = "라고 답변하였지.";

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = Integer.parseInt(br.readLine());

		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		solution(input, 0);
		
		
		System.out.println();
		System.out.println("**************교수님 코드****************");
		//////////////////////////////////////////////////////////
		
		
		recur(input,"");
		System.out.println(buffer);
		
		
		//////////////////////////////////////////////////////////
		
		
		
	}
	//////////////////////////////////////////////////////////////
	static  StringBuffer buffer = new StringBuffer("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
	private static void recur(int num,String underBar) {
		//1.종료조건
		if(num == 0) {
			buffer.append(underBar + qustion+"\n");
			buffer.append(underBar + "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
			buffer.append(underBar + end+"\n");
		}
		else {//반복조건
			buffer.append(underBar + qustion+"\n");
			for(int i = 0 ; i < answer.length; i++) {
				buffer.append(underBar + answer[i] + "\n");
			}
			recur(num -1,underBar+"____");
			buffer.append(underBar+ end+"\n");
		}
	}
	
	//////////////////////////////////////////////////////////////
	

	static void solution(int cnt, int cnt_underbar) {
		String underbar = "";
		for(int i = 0; i < cnt_underbar ; i++) {underbar += "____";}
		if (cnt != 0) {
			
			System.out.println(underbar + qustion);
			for (int j = 0; j < answer.length; j++) {System.out.println(underbar + answer[j]);}
			solution(cnt - 1, cnt_underbar + 1);
			System.out.println(underbar + end);
		} else {
			System.out.println(underbar + qustion);
			System.out.println(underbar + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(underbar + end);
		}

	}

}
