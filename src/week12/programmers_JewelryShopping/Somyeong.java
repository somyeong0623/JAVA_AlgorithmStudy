package week12.programmers_JewelryShopping;

import java.util.HashMap;
import java.util.HashSet;

class Somyeong {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int n = gems.length;
        HashSet<String> set = new HashSet<String>();
        for(String s : gems){
            set.add(s);
        }
        int cnt = set.size();
        System.out.println("cnt: "+cnt);
        
        
        int left=0;
        int right=0;
        int startIndex=0;
        int endIndex=0;
        int diff=Integer.MAX_VALUE;
        
        //현재 범위에서(left~right) 각 보석의 갯수를 확인할 때 사용할 map
        HashMap<String, Integer> map = new HashMap<String,Integer>();

        while(true){
           if(set.size()==map.size()){  // set의 사이즈가 map과 같으면 left를 오른쪽으로 이동 
                
                // left에있는 보석갯수 하나 줄이기.
                // 이때는 left에 해당하는 보석이 무조건 map에 존재하므로 그냥 1감소 시키면 됨.
                map.put(gems[left],map.get(gems[left])-1);
                
                // 범위에 존재하지 않으면 map에서도 빼주기
                if(map.get(gems[left])==0)
                    map.remove(gems[left]); // key이름 지정해서 찾아주면 됨.
                
                left ++; // left 증가
                
            }else if(right==n){ // right==n인 경우는 left를 증가시킬 일만 남았는데 set size가 아니라면 더이상 left 증가가 의미없기 때문에 끝내주기
               break;
               
           }else{  // set.size()!=map.size() 
                // right 증가시키기 
              
                map.put(gems[right],map.getOrDefault(gems[right],0)+1);
                right++; 
            }
            
            if(set.size()==map.size()){
                    if((right-1)-left<diff){
                        startIndex=left;
                        endIndex=right-1;
                        diff=(right-1)-left;
                    }
                }

        }
        answer[0]=startIndex+1;
        answer[1]=endIndex+1;
        return answer;
    }
}