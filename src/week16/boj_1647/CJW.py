import heapq
from sys import stdin

hq = []

N, M = list(map(int, stdin.readline().split()))

for _ in range(M):
    A, B, C = list(map(int, stdin.readline().split()))
    heapq.heappush(hq, (C, A, B))

parent = [i for i in range(N + 1)]


def find(x):
    global parent
    if x != parent[x]:
        parent[x] = find(parent[x])
        return parent[x]
    return parent[x]


def union(a, b):
    global parent
    a = find(a)
    b = find(b)
    if a != b:
        if a < b:
            parent[b] = a
        else:
            parent[a] = b


result = 0
last = 0
while hq:
    C, A, B = heapq.heappop(hq)

    if find(A) != find(B):
        union(A, B)
        result += C
        last = C

print(result - last)
