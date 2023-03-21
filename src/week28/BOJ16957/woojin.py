import sys

input = sys.stdin.readline

n,m=map(int,input().split())
graph=[]
mama=-sys.maxsize
for i in range(n):
    e=list(map(int,input().split()))
    graph.append(e)
    mama=max(mama,max(e))
parents=[0 for _ in range(mama+1)]
def make():
    for i in range(1,mama+1):
        parents[i]=i
def find(a):
    if parents[a]==a:
        return a
    parents[a]=find(parents[a])
    return parents[a]

def union(a,b):
    a1=find(a)
    a2=find(b)

    if (a1 < a2):
        parents[a2] = a1
    else:
        parents[a1] = a2


dx=[-1,-1,-1,0,1,1,1,0]
dy=[-1,0,1,1,1,0,-1,-1]

make()
for i in range(n):
    for j in range(m):
        now = graph[i][j]
        nextx = -1
        nexty = -1
        nexttemp = sys.maxsize
        nextcount = 0
        for l in range(8):
            a1 = i + dx[l]
            a2 = j + dy[l]
            if 0 <= a1 < n and 0 <= a2 < m:
                if graph[a1][a2] < now and graph[a1][a2] < nexttemp:
                    nexttemp = graph[a1][a2]
                    nextx = a1
                    nexty = a2
        if nextx!=-1 and nexty!=-1:

            union(now,graph[nextx][nexty])

for l in range(0,mama+1):
    parents[l]=find(l)


answer=[0 for _ in range(mama+1)]
for l in range(0,mama+1):
    answer[parents[l]]+=1

for l in graph:
    for j in l:
        print(answer[j],end=" ")
    print()
