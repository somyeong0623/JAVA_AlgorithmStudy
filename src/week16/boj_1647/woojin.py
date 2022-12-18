import sys
input=sys.stdin.readline


n,m=map(int,input().split())
home=[0]*(n+1)

edge=[]
parents=[0]*(n+1)

def make():
    for i in range(1,n+1):
        parents[i]=i

def find(a):
    if parents[a]==a:
        return a
    parents[a]=find(parents[a])
    return parents[a]

def union(a,b):
    a1=find(a)
    a2=find(b)

    if a1==a2:
        return 0

    if a1<a2:
        parents[a2]=a1
    else:
        parents[a1]=a2
    return 1



for _ in range(m):
    a1,a2,a3=map(int,input().split())
    edge.append((a1,a2,a3))

edge=sorted(edge,key=lambda x:(x[2]))



so=0
maz=0
make()
for i in edge:
    a1,a2,a3=i
    t=union(a1,a2)
    if t==1:
        so+=a3
        maz=max(maz,a3)

print(so-maz)