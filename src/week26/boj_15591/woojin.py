import sys
from collections import deque
input = sys.stdin.readline
n,q=map(int,input().split())
graph=[[] for _ in range(n+1)]

for _ in range(n-1):
    a1,a2,a3=map(int,input().split())
    graph[a1].append((a2,a3))
    graph[a2].append((a1, a3))

#이제 탐색을 진행 해보도록 하자
def bfs(vertex,l,graph):
    q=deque()
    q.append(vertex)
    visit=[0]*(n+1)
    visit[vertex]=1

    tt=0
    while q:
        now=q.popleft()
        for i in graph[now]:
            a1,a2=i
            if visit[a1]==0 and a2>=l:
                visit[a1]=1
                q.append(a1)
                tt+=1
    print(tt)

for l in range(q):
    a1,a2=map(int,input().split())
    e=bfs(a2,a1,graph)