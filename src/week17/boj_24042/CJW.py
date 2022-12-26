import heapq
import sys
from sys import stdin

N, M = list(map(int, stdin.readline().split()))
arr = []
gragh = [[] for i in range(N + 1)]
for _ in range(M):
    a, b = list(map(int, stdin.readline().split()))
    arr.append((a, b))
    gragh[a].append((b, _))
    gragh[b].append((a, _))

hq = []
heapq.heappush(hq, (0, 1))

distances = [sys.maxsize for _ in range(N + 1)]
distances[1] = 0
"""
    c_ = current
    n_ = next
"""
while hq:
    c_cost, c_node = heapq.heappop(hq)

    if distances[c_node] < c_cost:
        continue

    for n_node, n_idx in gragh[c_node]:
        # 이동 가능한 다음 주기는 현재 비용보다 커야한다.
        n_cost = c_cost + (n_idx - c_cost) % M

        # 이동 시간을 포함 해서 최적을 찾으면 갱신
        if distances[n_node] > n_cost + 1:
            distances[n_node] = n_cost + 1
            heapq.heappush(hq, (n_cost + 1, n_node))

print(distances[N])
