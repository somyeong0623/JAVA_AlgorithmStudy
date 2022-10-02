import sys, copy, heapq
import heapq, math
from queue import PriorityQueue
from itertools import permutations, combinations, product
from collections import deque
from itertools import product

#sys.setrecursionlimit(10 ** 5)
#a=list(product(i,repeat=len(powers)))
#b=list(product(*a)) #리스트 안에 있는 원소들끼리 조합


from itertools import combinations_with_replacement as cwr
from collections import Counter
input = sys.stdin.readline



#우선 벽을 부수는 행위를 생각해서 진행을 해보자!
m,n=map(int,input().split())

graph=[]

for _ in range(n):
    t=list(map(int,input().rstrip()))
    graph.append(t)
visit=[[0 for _ in range(m)] for _ in range(n)]


#최소한 부수어야 하는 벽을 의미함

def bfs():

    no=deque()
    yes=deque()

    dx=[-1,0,1,0]
    dy=[0,1,0,-1]

    no.append((0,0,0))
    visit[0][0]=1


    while(len(no)>0) :
        a1,a2,a3=no.popleft()
        for i in range(4):
            zx=a1+dx[i]
            zy=a2+dy[i]
            if(0<=zx<n and 0<=zy<m):
                if (zx == n - 1 and zy == m - 1):
                    if (graph[zx][zy]==0):
                        print(a3)
                    else:
                        print(a3+1)
                    exit(0)
                if(graph[zx][zy]==0):
                    if(visit[zx][zy]==0):
                        visit[zx][zy]=1
                        no.appendleft((zx,zy,a3))
                else:
                    if (visit[zx][zy]==0):
                        visit[zx][zy]=1
                        no.append((zx, zy, a3+1))




if(n==m and n==1):
    print(0)
else:
    bfs()