import sys
input = sys.stdin.readline

n,k,q,m=map(int,input().split())
sleep=[0]*(n+3)
total=[0]*(n+3)
e=list(map(int,input().split()))
for i in e:
    sleep[i]=-1

data=list(map(int,input().split()))

for i in data:
    k=i
    if sleep[i]==-1:
        continue
    while k<=n+2:
        if sleep[k]==0:
            total[k]=1
        k+=i

for i in range(4,n+3):
    total[i]+=total[i-1]

for k in range(m):
    a1,a2=map(int,input().split())
    print(a2-a1+1-(total[a2]-total[a1-1]))