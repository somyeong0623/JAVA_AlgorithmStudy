#1시간 40분 걸림.................

import sys
from collections import deque
input = sys.stdin.readline
graph=[]#현재 그래프의 색깔을 의미를 함

horse=[]#말의 위치가 움직여 지는 것을 의미를 함
n,k=map(int,input().split())
graph_horse=[[[]for _ in range(n)] for _ in range(n)]#말의 현재 배치 상황을 의미를 한다
#0:흰색, 1:빨간색, 2: 파란색
for _ in range (n):
    graph.append(list(map(int,input().split())))


for i in range(1,k+1):
    x,y,dir=map(int,input().split())
    horse.append([x-1,y-1,dir,i])
    graph_horse[x-1][y-1].append(i)

#턴이 진행되던 중에 말이 4개 이상 쌓이는 순간에 게임이 종료가 된다


def whiteAndred(graph_horse,horse,x,y,zx,zy,color,test_now):

    #흰색일시에는 이동을 하고자 한다.
    now=graph_horse[x][y]#현재 있는 말들이 한꺼번에 움직여 질거임
    q=deque()
    count=0

    #뽑고자 하는 얘 위까지만 구하기 위한 부분
    for i in range(len(now)-1,-1,-1):
        count += 1
        temp=horse[now[i]-1]
        q.append(temp[3])
        if temp[3]==test_now:
            temp[0] = zx
            temp[1] = zy
            horse[now[i]-1] = temp
            break
        temp[0]=zx
        temp[1]=zy
        horse[now[i]-1]=temp
    #제거 해주는 과정
    for _ in range(count):
        graph_horse[x][y].pop()
    if color=="white":
        while q:
            graph_horse[zx][zy].append(q.pop())
    else:
        while q:
            graph_horse[zx][zy].append(q.popleft())


def move_dir(i):
    if i==1:
        return 2
    elif i==2:
        return 1
    elif i==3:
        return 4
    elif i==4:
        return 3

def move(graph,graph_horse,horse,count):
    move_to=[(0,1),(0,-1),(-1,0),(1,0)]
    for i in range(len(horse)):
        x,y,dir,k=horse[i]
        #말이 이동 좌표를 파악
        zx=x+move_to[dir-1][0]
        zy=y+move_to[dir-1][1]
        checkd=0
        if len(graph_horse[x][y])>=4:
            print(count+1)
            exit(0)

        # 가장 아래에 있는 말만 움직일수가 있다.
        now = graph_horse[x][y][0]
        if now != k:  # 가장 아래에 있지 못하니 다음 말이 이동을 해야한다
            continue

        if 0<=zx<n and 0<=zy<n:
            if graph[zx][zy]==0:#흰색
                whiteAndred(graph_horse,horse,x,y,zx,zy,"white",i+1)

            elif graph[zx][zy]==1:#빨간색
                whiteAndred(graph_horse, horse, x, y, zx, zy,"red",i+1)

            elif graph[zx][zy]==2:#파란색
                #이동하려는 칸이 파란색일 경우에는 이동방향을 반대로 해야함
                #그후 한칸을 이동을 하면됨
                #방향 바꿨는데도 파란색이면 그냥 멈추면 된다

                #방향 바꾼거 수정
                to_dir = move_dir(dir)
                horse[i][2] = to_dir
                zx = x + move_to[to_dir - 1][0]
                zy = y + move_to[to_dir - 1][1]
                if 0 <= zx < n and 0 <= zy < n:
                    if graph[zx][zy] == 0:  # 흰색
                        whiteAndred(graph_horse, horse, x, y, zx, zy, "white", i + 1)
                    elif graph[zx][zy] == 1:  # 빨간색
                        whiteAndred(graph_horse, horse, x, y, zx, zy, "red", i + 1)
                else:
                    checkd = 1

        #파란색과 같은 경우
        else:
            to_dir=move_dir(dir)
            horse[i][2] = to_dir
            zx = x + move_to[to_dir - 1][0]
            zy = y + move_to[to_dir - 1][1]
            if graph[zx][zy] == 0:  # 흰색
                whiteAndred(graph_horse, horse, x, y, zx, zy, "white", i + 1)
            elif graph[zx][zy] == 1:  # 빨간색
                whiteAndred(graph_horse, horse, x, y, zx, zy, "red", i + 1)
        
        #이동한후에 4개 이상이면 종료 하도록 하자
        if checkd==0 and len(graph_horse[zx][zy])>=4:
            print(count+1)
            exit(0)

count=0
while count<1000:
    move(graph,graph_horse,horse,count)
    count+=1
print(-1)