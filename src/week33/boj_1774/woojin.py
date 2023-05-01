import sys,math
input=sys.stdin.readline



n,m=map(int,input().split())
parents=[0]*(n+1)
total=[]
total.append((0,0))
for _ in range(n):
    total.append(list(map(float,input().split())))
def make():
    for i in range(1,n+1):
        parents[i]=i

def find(a1):
    if parents[a1]==a1:
        return a1
    parents[a1] = find(parents[a1])
    return parents[a1]


def union (a1,a2):
    f1=find(a1)
    f2=find(a2)
    if f1!=f2:
        if f1<f2:
            parents[f2]=f1
        else:
            parents[f1]=f2
make()
for _ in range(m):
    a1,a2=map(int,input().split())
    union(a1,a2)
edges=[]

for i in range(1,n):
    a1,a2=total[i]
    for j in range(i+1,n+1):
        t1,t2=total[j]
        zx=a1-t1
        zy=a2-t2
        zx=zx**2
        zy=zy**2

        temp=math.sqrt(zx+zy)
        edges.append((temp,i,j))

edges.sort()
answer=0
for a1,a2,a3 in edges:
    if find(a2)==find(a3):
        continue
    answer+=a1
    union(a2,a3)
print("{:.2f}".format(answer))