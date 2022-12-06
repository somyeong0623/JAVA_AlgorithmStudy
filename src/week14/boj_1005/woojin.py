import sys
from collections import deque
input = sys.stdin.readline
t=int(input().rstrip())
for _ in range(t):
    n,k=map(int,input().split())
    time=list(map(int,input().split()))
    road=[[]for _ in range(n+1)]
    result=[0 for _ in range(n+1)]
    degree=[0]*(n+1)
    for l in range(k):
        a1,a2=map(int,input().split())
        road[a1].append(a2)
        degree[a2]+=1

    last=int(input().rstrip())

    q=deque()

    for l in range(1,n+1):
        if degree[l]==0:
            q.append(l)


    while degree[last]>0:
        a1=q.popleft()
        for l in road[a1]:
            degree[l]-=1
            result[l] = max(result[l], time[a1 - 1] + result[a1])
            if degree[l] == 0:

                q.append(l)
    print(result[last]+time[last-1])