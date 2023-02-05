from collections import deque
def solution(cap, n, deliveries, pickups):
    answer = 0
    
    # cap이 주어졌으니 매 턴마다 갈수 있느냐 없느냐로 판단을 핤거임
    
    go=deque()#앞으로 진행 ,해당 되는 거리 + 
    back=deque()#뒤로 다시 돌아옴
    
    
    for i in range(n):
        if deliveries[i]!=0:
            go.append((i+1,deliveries[i]))
        if pickups[i]!=0:
            back.append((i+1,pickups[i]))
    

    
    while True:
        turn =0
        gogo=0
        baba=0
        
        if len(go)==0 and len(back)==0:
            break
        
        
        if len(go)>0:
            while len(go)>0:
                a1,a2=go.pop()#거리, 개수
                gogo=max(gogo,a1)#오른쪽으로 얼마나 갔는가?
                #여기 부분에서 넣은 거로끝나는지 아닌지를 판단을 해야함
                if turn+a2<=cap:
                    turn=turn+a2
                    if turn ==cap:#지금 넣은상태에서 가득 차지않았다면 멈추면됨
                        break#지금 넣은거로 가득 찼다면 멈춰야 함
                else:
                    #만약 최대한 넣을수 있는게 있다면 넣어줘야함
                    now=cap-turn#지금 상태에서 넣을수 있는 개수를 의미를 함
                    a2-=now
                    go.append((a1,a2))
                    break
        turn=0
        if len(back)>0:
            while len(back)>0:
                a1,a2=back.pop()#거리, 개수
                baba=max(baba,a1)#오른쪽으로 얼마나 갔는가?
                #여기 부분에서 넣은 거로끝나는지 아닌지를 판단을 해야함
                if turn+a2<=cap:
                    turn=turn+a2
                    if turn ==cap:#지금 넣은상태에서 가득 차지않았다면 멈추면됨
                        break#지금 넣은거로 가득 찼다면 멈춰야 함
                else:
                    #만약 최대한 넣을수 있는게 있다면 넣어줘야함
                    now=cap-turn#지금 상태에서 넣을수 있는 개수를 의미를 함
                    a2-=now
                    back.append((a1,a2))
                    break
                    
        if gogo>=baba:
            answer+=gogo*2
        else:
            answer+=baba*2
       
    
    
    
    
    
    return answer