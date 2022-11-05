package week13.programmers_NewId;
import java.util.Arrays;

public class Jul {
	public static void main(String[] args) {
		System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
//		System.out.println(solution("z-+.^."));
//		System.out.println(solution("=.="));
//		System.out.println(solution("123_.def"));
//		System.out.println(solution("abcdefghijklmn.p"));
		System.out.println((int)'0'+ " "+(int)'z'+" "+(int)'1'+ " "+(int)'9'+" "+(int)'-'+" "+(int)'.'+" "+(int)'_');
	}
	 public static String solution(String new_id) {
	        String answer = "";
	        new_id = new_id.toLowerCase();
	        System.out.println(new_id);
	        String pre = "";
	        for (int i = 0; i < new_id.length(); i++) {
				if((((int)new_id.charAt(i)>=48&&(int)new_id.charAt(i)<=57)||(int)new_id.charAt(i)>=97&&(int)new_id.charAt(i)<=122) || (int)new_id.charAt(i) == 45 || (int)new_id.charAt(i) == 46 || (int)new_id.charAt(i) == 95) {
					pre+= new_id.charAt(i)+"";
				}

			}
	        new_id = pre;
	        pre = "";
	        String point = "";
	        System.out.println(new_id);
	        for (int i = 0; i < new_id.length(); i++) {
				char p = new_id.charAt(i);
				if(p == '.') {
					point+=p+"";
				}else {
					if(point.length()>0)pre+=".";
					pre+=p+"";
					point="";
				}
			}
	        if(point.length()>0)pre+=".";
			point="";
	        new_id = pre;
	        pre = "";
	        System.out.println(new_id);
	        if(new_id.charAt(0) == '.') {
	        	if(new_id.length()>1)new_id = new_id.substring(1);
	        	else new_id = "";
	        }
	        if(new_id != "") {
	        	if(new_id.charAt(new_id.length()-1) == '.') {
	        		if(new_id.length()>1) {
	        			new_id = new_id.substring(0, new_id.length()-1);
	        		}else new_id = "";
	        	}
	        }
	        if(new_id == "") new_id = "a";
	        if(new_id.length()>15) new_id = new_id.substring(0,15);
	        if(new_id.charAt(new_id.length()-1) == '.') {
        		new_id = new_id.substring(0, new_id.length()-1);
        	}
	        if(new_id.length() == 1)new_id += new_id.charAt(0)+""+new_id.charAt(0);
	        else if(new_id.length() == 2) new_id += new_id.charAt(1);
	        answer = new_id;
	        System.out.println("정답 : "+new_id);
	        return answer;
	    }
}
