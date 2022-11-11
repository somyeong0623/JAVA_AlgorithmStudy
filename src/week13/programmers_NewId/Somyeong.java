import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        
        //1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
        String answer1=new_id.toLowerCase();
        
        // 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
        String answer2 ="";
        for(int i=0; i<answer1.length(); i++){
            char cur = answer1.charAt(i);
            if((cur>='a'&&cur<='z') || (cur>='A'&&cur<='Z')|| (cur>='0' && cur<='9') ||
              (cur=='-') || (cur=='_') || cur=='.'){
                answer2+=cur;
            }
        }
        // System.out.println("answer2: "+answer2);
        
        //3.  new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
        String answer3="";
        answer3+=answer2.charAt(0);
 
        for(int i=1; i<answer2.length(); i++){
           if((answer3.charAt(answer3.length()-1)=='.') && answer2.charAt(i)=='.' ) 
               continue;
            answer3+=answer2.charAt(i);
        }
        // System.out.println("answer3: "+answer3
        
        // 4. new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
        String answer4="";
        answer4=new String(answer3);
        if(!answer3.equals("")){
            if(answer3.charAt(0)=='.')
                answer4=new String(answer3.substring(1));
        }
        if(!answer4.equals("")){
            if(answer4.charAt(answer4.length()-1)=='.')
                answer4=new String(answer4.substring(0,answer4.length()-1));
        }
            
        // System.out.println("answer4:"+answer4);
        //5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
        String answer5=new String(answer4);
        if(answer5.equals(""))
            answer5=new String("a");
        
        // System.out.println("ansewr5:"+answer5);
        
        //6. new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
        //  만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
        String answer6="";
        if(answer5.length()>=16)
            answer6=new String(answer5.substring(0,15));
        else
            answer6=new String(answer5);
        
        if(answer6.charAt(answer6.length()-1)=='.')
            answer6= new String(answer6.substring(0,answer6.length()-1));
        
        // System.out.println("ansewr6:"+answer6);
        
        // 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.      
        String answer7 = new String(answer6);
        while(answer7.length()<3){
            answer7+=answer7.charAt(answer7.length()-1);
        }
        
          // System.out.println("ansewr7:"+answer7);
        answer=new String(answer7);
        
        return answer;
    }
}