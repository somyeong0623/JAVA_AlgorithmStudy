from sys import stdin
from collections import deque

N = int(stdin.readline())


def bfs(start):
    visit = [-1] * (N + 1)
    que = deque()
    que.append(start)
    visit[start] = 0
    _max = [0, 0]

    while que:
        start = que.popleft()

        for end, weight in graph[start]:
            if visit[end] == -1:
                visit[end] = visit[start] + weight
                que.append(end)
                if _max[0] < visit[end]:
                    _max = visit[end], end

    return _max


graph = [[] for _ in range(N + 1)]

for _ in range(N):
    c = list(map(int, stdin.readline().split()))
    for e in range(1, len(c) - 2, 2):
        graph[c[0]].append((c[e], c[e + 1]))

answer, node = bfs(1)
answer, node = bfs(node)
print(answer)
