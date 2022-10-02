import sys, copy, heapq

input = sys.stdin.readline



n,m=map(int,input().split())
dist=[int(1e9) for _ in range(n+1)]
edge=[]
for _ in range(m):
    a1,a2,a3=map(int,input().split())
    edge.append((a1,a2,a3))

dist[1]=0
for i in range(1,n+1):
    for start,next,cost in edge:
        if dist[start]+cost<dist[next] and dist[start]!=int(1e9):
            dist[next]=dist[start]+cost
            if i==n:
                print(-1)
                exit(0)

for i in range(2,n+1):
    if(dist[i]!=int(1e9)):
        print(dist[i])
    else:
        print(-1)