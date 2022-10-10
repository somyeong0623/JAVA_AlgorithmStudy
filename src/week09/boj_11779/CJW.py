import heapq
from sys import stdin

INF = 1234567890

N = int(stdin.readline())
M = int(stdin.readline())
graph = [[] for _ in range(N)]

for _ in range(M):
    f, t, w = list(map(int, stdin.readline().split()))
    f -= 1
    t -= 1
    graph[f].append([t, w])

s, e = list(map(int, stdin.readline().split()))
s -= 1
e -= 1

d = [INF for _ in range(N + 1)]
d[s] = 0
que = []
heapq.heappush(que, [d[s], s])

paths = {}

while que:
    cost, node = heapq.heappop(que)
    if d[node] < cost:
        continue
    for n, c in graph[node]:
        c += cost
        if c < d[n]:
            paths[n] = node
            d[n] = c
            heapq.heappush(que, [c, n])

print(d[e])
cnt = 0

path = [e]
cur = e
while cur in paths:
    cur = paths[cur]
    path.append(cur)
    cnt += 1
print(len(path))
path.reverse()
for i in range(len(path)):
    path[i] += 1
print(' '.join(list(map(str, path))))
