import sys
from collections import deque

input=sys.stdin.readline


n=int(input().rstrip())
indegree=[0]*(n+1)
graph=[[] for _ in range(n+1)]
time=[0]*(n+1)
fixed=[0]*(n+1)#이걸 더해 줘야함

op=1
for _ in range(n):
    e=list(map(int,input().split()))
    fixed[op]=e[0]
    for i in range(1,len(e)-1):
        t=e[i]
        graph[t].append(op)
        indegree[op]+=1
    op+=1

q=deque()
for i in range(1,n+1):
    if indegree[i]==0:
        q.append((i,fixed[i]))
        time[i]=fixed[i]

while q:
    a1,a2=q.popleft()

    for i in graph[a1]:
        indegree[i]-=1
        time[i] = max(time[i], fixed[i] + a2)
        if indegree[i]==0:
            q.append((i,time[i]))


ans=""
for i in range(1,n+1):
    ans+=str(time[i])+"\n"
print(ans)