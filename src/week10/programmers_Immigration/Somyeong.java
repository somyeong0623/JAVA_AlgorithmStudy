package week10.programmers_Immigration;

import java.util.*;

// 이분탐색_입국심사 
class Solution {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        int size= times.length;
        // Arrays.sort(times); 이거쓰면 테케 반정도 틀림 ..왜지 ....
        long max=0;
        for(int i=0; i<size; i++){
            if(times[i]>max) max=times[i];
        }
        
        long start=1;
        // long end=n*times[size-1]; // 배열하고 마지막원소*n을 end로설정하면 테케 반정도 틀리는데 왜그런지 모르겠음 
        long end=max*n;
        long mid=0;
        while(start<=end){
            mid=(start+end)/2;
            
            long temp=0;
            for(int i=0; i<size; i++){
                temp+=mid/times[i];
            }
            if(temp>=n){
                answer=Math.min(answer,mid);
                end=mid-1;
            }else{
                start=mid+1;
            }
        }
        return answer;
    }
}