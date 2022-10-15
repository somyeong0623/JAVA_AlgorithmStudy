package week10.programmers_StringCompression;

public class Jul {

	public static void main(String[] args) {
		System.out.println(solution("aabbaccc"));
		System.out.println(solution("ababcdcdababcdcd"));
		System.out.println(solution("abcabcdede"));
		System.out.println(solution("abcabcabcabcdededededede"));
		System.out.println(solution("xababcdcdababcdcd"));
		System.out.println(solution("a"));

	}
    public static int solution(String s) {
        int answer = 100000;
        for (int i = 1; i < s.length()/2+2; i++) {
			String pre = "";
			int count = 1;
			String temp = "";
			for (int j = 0; j < i; j++) {
				temp+=s.charAt(j)+"";
			}
			System.out.println("temp : "+temp);
			int last =s.length();
			for (int j = i; j < s.length(); j+=i) {
				String com = "";
				if(j+i>s.length()) {
					System.out.println("count:!!!!"+ count);
					last=j;
//					pre+=temp;
//					pre+=s.substring(j);
					break;
				}
				for (int k = j; k < j+i; k++) {
					com += s.charAt(k)+"";
				}
				System.out.println("com : "+com);
				if(temp.equals(com)) {
					count+=1;
					System.out.println(temp+" "+com+" 가 같다 "+count);
				}else {
					if(count==1) pre+=temp;
					else {
						pre+=count+""+temp;
					}
					System.out.println("결과 : "+pre);
					count=1;
					temp = com;
				}
			}
			if(count == 1)pre+=temp+s.substring(last);
			else pre+=count+""+temp+s.substring(last);
			System.out.println(pre+" : result !!");
			answer = Math.min(answer, pre.length());
			System.out.println("답 : "+answer );
		}
        return answer;
    }
}
