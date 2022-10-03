import copy
from sys import stdin
from queue import deque


def isRange(x, y):
    if x < 0 or x >= X or y < 0 or y >= Y:
        return False
    return True


def isMove(x, y):
    if world[y][x] == 0:
        return True
    return False


# 상 우 하 좌
dirs = ((0, -1), (1, 0), (0, 1), (-1, 0))

X, Y = list(map(int, stdin.readline().split(" ")))
world = [[x for x in list(map(int, stdin.readline().strip()))] for _ in range(Y)]

visited = [[-1] * X for _ in range(Y)]
que = deque([(0, 0)])
visited[0][0] = 0

while que:
    x, y = que.popleft()

    for dx, dy in dirs:
        nx = x + dx
        ny = y + dy
        if isRange(nx, ny):
            if visited[ny][nx] == -1:
                if isMove(nx, ny):
                    que.appendleft((nx, ny))
                    visited[ny][nx] = visited[y][x]
                else:
                    que.append((nx, ny))
                    visited[ny][nx] = visited[y][x] + 1

print(visited[Y - 1][X - 1])
