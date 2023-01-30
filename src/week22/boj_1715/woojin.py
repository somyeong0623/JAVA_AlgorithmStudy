import sys
import heapq
input = sys.stdin.readline

n=int(input().rstrip())

e=[]

for _ in range(n):
    temp=int(input().rstrip())
    heapq.heappush(e,temp)

if n==1:
    print(0)
else:
    total=0

    while len(e)>1:
        a1=heapq.heappop(e)
        a3=heapq.heappop(e)
        total+=(a1+a3)
        heapq.heappush(e,a1+a3)
    print(total)