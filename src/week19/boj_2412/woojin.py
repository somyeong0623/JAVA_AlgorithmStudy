import sys
from collections import deque
input = sys.stdin.readline
n,t=map(int,input().split())
graph=[[]for _ in range(200001)]

for _ in range(n):
    a1,a2=map(int,input().split())
    graph[a2].append(a1)

q=deque()
q.append((0,0,0))

while q:
    a1,a2,a3=q.popleft()

    start_y=a2-2
    if start_y<0:#start_y값을 초기화 해줌
        start_y=0
    start_fy=a2+2
    start_fx=a1-2
    start_tx=a1+2
    #가능한 y의 범위에서 진행
    for i in range(start_y,start_fy+1):
        #가능한 x의 범위에서 진행
        dele = []  # 삭제할 얘들을 한꺼번에 지우고자함
        for k in range(len(graph[i])):
            #x:k , y:i
            e=graph[i][k]
            if e>=start_fx and e<=start_tx:
                dele.append(k)
                q.append((e,i,a3+1))
                if i==t:
                    print(a3+1)
                    sys.exit(0)
        for k in range(len(dele)-1,-1,-1):
            graph[i].pop(dele[k])


print(-1)
