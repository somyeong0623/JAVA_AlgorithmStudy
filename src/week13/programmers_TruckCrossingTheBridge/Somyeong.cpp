//모르겠어서 구글링함 ㅜㅜㅜ
#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer=0; // 걸린 시간
    int currentWeight=0; // 현재 도로에 올라가져있는 차 무게
    
    queue<int> count; //도로에 올라가져 있는 차
    queue<int> bridgeOn; //차마다 남은 거리
    
    while(true){
        int size=bridgeOn.size();// 중간에 차가 빠져나가면 계산이 바뀌기 때문에 사이즈 고정
        for(int i=0; i<size; i++){
            int length= bridgeOn.front();
            bridgeOn.pop();
            if(length<=1){ //남은 길이가 없다면
                currentWeight-=count.front();
                count.pop();
                continue;
                 
            }
            
            bridgeOn.push(length-1); //남아있으면 길이 감소 후 다시 넣기
        }
        
        //대기차가 1대이상 있고 도로가 무게를 견딜 수 있다면
        if(truck_weights.size()>0 && currentWeight+truck_weights[0]<=weight){
            count.push(truck_weights[0]); //도로에 올라가져 있는 차 추가
            currentWeight+=truck_weights[0]; //현재 올라가져있는 무게 추가
            bridgeOn.push(bridge_length); //남은 도로거리 추가
            truck_weights.erase(truck_weights.begin()); //대기차량 삭제
        }
        answer++; //시간초 증가
        
        //도로에 올라가져있는 차와 대기차가 모두 없다면 반복문 탈출
        if(truck_weights.size()==0 && count.size()==0)
            break;
    }
    
    return answer;
}
