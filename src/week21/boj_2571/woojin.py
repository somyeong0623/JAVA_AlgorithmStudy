import sys
input = sys.stdin.readline
graph=[[0]*100 for _ in range(100)]
n=int(input().rstrip())
for _ in range(n):
    a1,a2=map(int,input().split())
    a1-=1
    a2-=1
    for i in range(a1,a1+10):
        for j in range(a2,a2+10):
            graph[i][j]=1

answer=-sys.maxsize

def check(x,y,width):

    #현재 시작 점에서의 세로의 누적합을 의미를 함
    now_min=graph[x][y]

    for i in range(y,y+width+1):
        now_min=min(now_min,graph[x][i])
    return now_min*(width+1)

def go(x,y):
    now=-sys.maxsize
    for j in range(0,101):
        if y+j>=100:
            break
        if graph[x][y+j]==0:
            break
        temp=check(x,y,j)
        now=max(temp,now)
    return now

for i in range(99):
    for j in range(100):
        if graph[i][j]!=0 and graph[i+1][j]!=0:
            graph[i+1][j]=graph[i][j]+1

for i in range(100):
    for j in range(100):
        if graph[i][j]==0:
            continue
        temp=go(i,j)
        answer=max(temp,answer)


print(answer)
