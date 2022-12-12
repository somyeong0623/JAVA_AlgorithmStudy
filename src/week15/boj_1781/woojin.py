import sys,heapq
input=sys.stdin.readline
n=int(input().rstrip())
total=list(list(map(int,input().split())) for _ in range(n))
total=sorted(total,key=lambda x:(x[0]))

q=[]
sumz=0
for a1,a2 in total:
    heapq.heappush(q,(a2,a1))
    sumz+=a2
    if len(q)>a1:
        a3,a4=heapq.heappop(q)
        sumz-=a3
print(sumz)