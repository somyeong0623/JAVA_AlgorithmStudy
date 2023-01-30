import sys
from collections import deque
input = sys.stdin.readline

n=int(input().rstrip())
graph=[]
count=0
for _ in range(n):
    e=list(map(int,input().rstrip()))
    graph.append(e)
    for l in e:
        if l==0:
            count+=1
visit=[[count for _ in range(n)] for _ in range(n)]
def bfs(x,y):
    q=deque()
    visit[x][y]=0
    q.append((x,y,0))
    dx=[-1,0,1,0]
    dy=[0,1,0,-1]
    while q:
        a1,a2,a3=q.popleft()
        for i in range(4):
            zx=dx[i]+a1
            zy=dy[i]+a2
            if 0<=zx<n and 0<=zy<n:
                if zx==n-1 and zy==n-1:

                    if graph[zx][zy]==0:
                        visit[zx][zy]=min(visit[zx][zy],a3)
                    else:
                        visit[zx][zy]=min(visit[zx][zy],a3+1)

                if graph[zx][zy]==1 and a3<visit[zx][zy]:
                    visit[zx][zy]=a3
                    q.append((zx,zy,a3))
                elif graph[zx][zy]==0 and a3+1<visit[zx][zy]:
                    visit[zx][zy] = a3+1
                    q.append((zx, zy, a3+1))

bfs(0,0)
print(visit[n-1][n-1])