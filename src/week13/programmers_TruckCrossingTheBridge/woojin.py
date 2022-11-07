import sys, copy, heapq
from collections import deque


def solution(bridge_length, weight, truck_weights):
    answer = 0
    # 모든 트럭이 다리를 건러려면 몇초가 걸리는가?
    # 최단 시간 안에 다리를 건너고자 함
    # 다리가 건널수 있는 무게가 존재를 함
    # 다리의 길이가 존재를 함

    time=0
    we=0#들어가 있는 무게를 의미함

    q=deque()
    while 1:

        if(len(q)>0):

            t,wei=q.popleft()
            if(t!=time):
                    q.appendleft((t,wei))

            else:
                    we-=wei

        if len(q)<bridge_length and len(truck_weights)>0 :
            #비어있으면 넣을수 있는지 체크를 해보자

            if(we+truck_weights[0]<=weight):
                    q.append((time+bridge_length,truck_weights[0]))
                    we+=truck_weights[0]
                    truck_weights.pop(0)

        time += 1
        if(len(q)==0):
            break

    answer=time






    return answer
