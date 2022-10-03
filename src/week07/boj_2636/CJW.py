from sys import stdin
from collections import deque

Y, X = list(map(int, stdin.readline().split()))
visited = [[False] * X for _ in range(Y)]

world = [list(map(int, stdin.readline().split())) for _ in range(Y)]

# 우 하 좌 상
dirs = ((1, 0), (0, 1), (-1, 0), (0, -1))


def bfs(temp_que):
    result = set()

    que = deque(temp_que)
    visited[temp_que[0][1]][temp_que[0][0]] = True
    while que:
        x, y = que.popleft()

        for dx, dy in dirs:
            nx = x + dx
            ny = y + dy

            if not (0 <= nx < X and 0 <= ny < Y):
                continue

            if world[ny][nx] == 1:
                result.add((nx, ny))
            else:
                if not visited[ny][nx]:
                    visited[ny][nx] = True
                    que.append((nx, ny))
    return list(result)


que = deque()
que.append((0, 0))
time = 0
result = []
remain = 0
for items in world:
    for item in items:
        if item == 1:
            remain += 1
result.append(remain)
while remain:
    temp = bfs(que)
    remain -= len(temp)
    for x, y in temp:
        world[y][x] = 0
    result.append(remain)
    que = deque(temp[:])
    time += 1

print(time)
print(result[-2])