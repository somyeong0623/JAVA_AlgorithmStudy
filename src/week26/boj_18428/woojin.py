import sys, copy
import heapq,math
from itertools import combinations,permutations
from collections import deque
sys.setrecursionlimit(10**9)
input = sys.stdin.readline
import functools,operator

teacher=[]
student=[]
free=[]
graph=[]
n=int(input().rstrip())
for i in range(n):
    m=list(map(str,input().split()))
    for j in range(n):
        if m[j]=="X":
            free.append((i,j))
        elif m[j]=="T":
            teacher.append((i,j))
        elif m[j]=="S":
            student.append((i,j))
    graph.append(m)

gg=list(combinations(free,3))

count=[]
check=0
def down(a,b):
    global count,check
    if graph[a][b] == "O":
        return
    if graph[a][b]=="S":
        if (a,b)not in count:
            count.append((a,b))
        check=1
        return
    if a+1<n and b<n:
        if graph[a+1][b]=="X" or graph[a+1][b]=="S" :
            down(a+1,b)
def up(a,b):
    global count,check
    if graph[a][b] == "O":
        return
    if graph[a][b]=="S":
        if (a,b)not in count:
            count.append((a,b))
        check=1
        return
    if a-1>=0 and b<n:
        if graph[a-1][b]=="X" or graph[a-1][b]=="S" :
            up(a-1,b)
def right(a,b):
    global count,check
    if graph[a][b] == "O":
        return
    if graph[a][b]=="S":
        if (a,b)not in count:
            count.append((a,b))
        check=1
        return
    if a<n and b+1<n:
        if graph[a][b+1]=="X" or graph[a][b+1]=="S" :
            right(a,b+1)
def left(a,b):
    global count,check

    if graph[a][b] == "O":
        return
    if graph[a][b]=="S":
        if (a,b) not in count:
            count.append((a,b))
        check=1
        return
    if a>=0 and b-1>=0:
        if graph[a][b-1]=="X" or graph[a][b-1]=="S" :
            left(a,b-1)
hi=0
hi2=0
for i in gg:
    count=[]
    for j in i:
        a1,a2=j
        graph[a1][a2]="O"
    for kk in teacher:
        k1,k2=kk
        up(k1,k2)
        down(k1, k2)
        left(k1, k2)
        right(k1, k2)
    if len(count)==len(student):
        hi=0
    if len(count)==0:
        hi2+=1

    for j in i:
        a1,a2=j
        graph[a1][a2]="X"
if hi2>0:
    print("YES")
else:
    print("NO")
