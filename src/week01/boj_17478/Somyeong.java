package week01.boj_17478;

import java.util.Scanner;

//17478.재귀함수가 뭔가요? 
public class Somyeong {
	
	static int cnt=0;
	static String line="";
	static int n;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		recur(0,line);
	}
	
	static void recur(int cnt,String line) {
		
		if(cnt==n) {
			System.out.println(line+"\"재귀함수가 뭔가요?\"");
			System.out.println(line+"\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(line+"라s고 답변하였지.");
			return;
		}
		if(cnt==0) {
			System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		}
		
		
		System.out.println(line+"\"재귀함수가 뭔가요?\"");
		System.out.println(line+"\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		System.out.println(line+"마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		System.out.println(line+"그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		cnt++;
		String temp=line+"____"; //재귀 끝난후 원래함수에서는 기존의 line을 유지해야해서 temp라는 임시변수에 저장하여 그 값을 다음 재귀함수로 보냄. 
		recur(cnt,temp);
		System.out.println(line+"라고 답변하였지.");
	}

}
