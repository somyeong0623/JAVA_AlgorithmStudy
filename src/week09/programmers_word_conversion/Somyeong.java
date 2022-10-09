package week09.programmers_word_conversion;

import java.util.*;

//lv3_dfs/bfs. 단어 변환 
class Somyeong{
    static int answer=Integer.MAX_VALUE;
    static boolean[] visited;
    static ArrayList<String> list;
    public int solution(String begin, String target, String[] words) {
       
        visited=new boolean[words.length];
        
        //words[]배열의 모든 단어를 타겟으로 dfs를 실행해보기 
        for(int i=0; i<words.length; i++){
            if(isChange(begin,words[i])){
                visited[i]=true;
                dfs(i,1,words,words[i],target);
                visited[i]=false;
            }
        }
        if(answer==Integer.MAX_VALUE) // answer가 변화없는경우 바꿀 수 없는 경우이므로 0으로 갱신
            answer=0;
        
        
        return answer;
    }
    public void dfs(int index, int cnt,String[] words,String cur,String target){
        
        if(cur.equals(target)){ // target 단어로 바꿀 수 있는경우, 지금까지 몇번 변환했는지 최솟값 갱신 
            answer=Math.min(answer,cnt); 
            return;
        }
        
        for(int i=0; i<words.length; i++){
            if(visited[i]) continue;
            if(isChange(cur,words[i])){
                visited[i]=true;
                dfs(i,cnt+1,words,words[i],target);
                visited[i]=false;
            }
        }        
        
    }
    // before과 end가 한글자만 다르면 true, 아니면 false리턴 
    public boolean isChange(String before, String end){
        int cnt=0;
        for(int i=0; i<before.length(); i++){
            if(before.charAt(i)!=end.charAt(i))
                cnt++;
        }
        
        if(cnt==1)
            return true;
        else
            return false;
    }
}