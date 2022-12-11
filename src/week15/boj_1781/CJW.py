import heapq
from sys import stdin

N = int(stdin.readline())
arr = []
for i in range(N):
    d, n = list(map(int, stdin.readline().split()))
    arr.append((d, n))

arr.sort(key=lambda x: x[0])

hq = []

for d, n in arr:
    heapq.heappush(hq, n)
    if d < len(hq):
        heapq.heappop(hq)

print(sum(hq))
