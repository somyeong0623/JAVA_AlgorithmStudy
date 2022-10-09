package week08.programmers_best_album;

import java.util.*;

class Music implements Comparable<Music>{
    int index;
    int plays;
    public Music(int index, int plays){
        this.index=index;
        this.plays=plays;
    }
    
    @Override
    public int compareTo(Music m){ // 재생횟수 내림차순으로 정렬하되, 같으면 고유번호 오름차순 정렬
        if(this.plays==m.plays)
            return this.index-m.index; // 고유번호 오름차순 
        
        return m.plays-this.plays; //재생횟수 내림차순 
    }
    
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
    
        HashMap<String, Integer> map = new HashMap<String,Integer>();
        for(int i=0; i<genres.length; i++){
            if(map.containsKey(genres[i]))
                map.put(genres[i],map.get(genres[i])+plays[i]);
            else
                map.put(genres[i],plays[i]);
        }
        // entryList: 재생횟수 내림차순으로 <장르,재생횟수> 가 정렬되어있는 리스트 
        List<Map.Entry<String,Integer>> entryList = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        
        entryList.sort((o1,o2)->o2.getValue()-o1.getValue()); // 재생횟수 많은 순으로 entryList 정렬 
        
        // for(Map.Entry<String,Integer>entry: entryList){
        //     System.out.println("key: "+entry.getKey()+", value: "+entry.getValue());
        // }
        
        int cnt=0; //cnt:  정답배열인 answer[]의 사이즈 구하기  
        ArrayList<Integer> answers= new ArrayList<Integer>(); // 정답배열에 들어갈 숫자들 임시 저장할 리스트 
        for(Map.Entry<String,Integer> entry : entryList){
            String curGenre = entry.getKey(); 
            ArrayList<Music> list = new ArrayList<Music>();
            for(int i=0; i<genres.length; i++){
                if(genres[i].equals(curGenre))
                    list.add(new Music(i,plays[i]));
            }
            Collections.sort(list); // Music class에 정의한 정렬 조건에 따라서 정렬 (재생횟수 내림차순, 인덱스 오름차순)
            if(list.size()>1){ // 장르에 속한곡이 2개 이상이면 
                answers.add(list.get(0).index);
                answers.add(list.get(1).index);                
                cnt+=2;
            }else if(list.size()==1){
                answers.add(list.get(0).index);
                cnt+=1;
            }  
            
        }
        
        //ArrayList에 담은 정답들 int[]배열에 옮겨주기 
        int[] answer= new int[cnt];
        for(int i=0; i<cnt; i++){
                answer[i]=answers.get(i);
        }
        return answer;
    }
}