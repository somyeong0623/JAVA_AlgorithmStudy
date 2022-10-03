import sys
import heapq

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())
adj = [[] for _ in range(N + 1)]
for _ in range(M):
    s, e, d = map(int, sys.stdin.readline().split())
    adj[s].append([e, d])
S, E = map(int, sys.stdin.readline().split())

dist = [float("INF")] * (N + 1)
q = [[0, S]]
dist[S] = 0

while q:
    nowCost, nowNum = heapq.heappop(q)

    if nowCost > dist[nowNum]:
        continue

    for nextnum, nextcost in adj[nowNum]:
        distance = nextcost + nowCost
        # 기록된 거리보다 distance가 작을때 기록된 거리를 업데이트 하기
        if dist[nextnum] > distance:
            dist[nextnum] = distance
            heapq.heappush(q, [distance, nextnum])
print(dist[E])