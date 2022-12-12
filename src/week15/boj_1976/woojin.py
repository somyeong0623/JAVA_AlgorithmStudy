import sys
input=sys.stdin.readline

n=int(input().rstrip())
m=int(input().rstrip())
graph=[[] for _ in range(n+1)]
parents=[0 for _ in range(n+1)]


def make():
   for i in range(1,n+1):
       parents[i]=i

def find(a):
    if parents[a]==a:
        return a
    parents[a] = find(parents[a])
    return parents[a]

def union(a,b):
    a1=find(a)
    a2=find(b)

    if(a1==a2):
        return True

    if(a1<a2):
        parents[a2]=a1
    else:
        parents[a1]=a2

    return False


make()
for l in range(n):
    e=list(map(int,input().split()))
    for k in range(len(e)):
        if e[k]==1:
            #graph[l+1].append(k+1)
            union(k+1,l+1)

for i in range(1,n+1):
    parents[i]=find(i)



t=list(map(int,input().split()))

ea=parents[t[0]]
for l in range(1,len(t)):
    tt=parents[t[l]]
    if tt!=ea:
        print("NO")
        sys.exit(0)
print("YES")