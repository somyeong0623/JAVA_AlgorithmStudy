from collections import deque
from sys import stdin

# 상 우 하 좌
dirs = ((0, -1), (1, 0), (0, 1), (-1, 0))

dirs_map = dict()
dirs_map[0] = (1, 3)
dirs_map[1] = (0, 2)
dirs_map[2] = (1, 3)
dirs_map[3] = (0, 2)

N = int(stdin.readline())

world = [list(stdin.readline().strip()) for _ in range(N)]

points = []

for y, lines in enumerate(world):
    for x, val in enumerate(lines):
        if val == "#":
            points.append((x, y))
start = points[0]
end = points[1]

visited = [[[False] * (1000) for _ in range(N + 1)] for _ in range(N + 1)]

# (x, y, dir, build)
que = deque()

for idx, (dx, dy) in enumerate(dirs):
    nx, ny = start[0] + dx, start[1] + dy
    if 0 <= nx < N and 0 <= ny < N:
        if world[ny][nx] != '*':
            if world[ny][nx] == '!':
                que.appendleft((nx, ny, idx, 0))
                for td in dirs_map[idx]:
                    que.appendleft((nx, ny, td, 1))
            elif world[ny][nx] == '#':
                que.clear()
                break
            else:
                que.appendleft((nx, ny, idx, 0))
            visited[ny][nx][0] = True

visited[start[1]][start[0]][0] = True

while que:
    x, y, d, b = que.pop()
    dx, dy = dirs[d]
    nx, ny = x + dx, y + dy
    if 0 <= nx < N and 0 <= ny < N:
        if visited[ny][nx][b]:
            continue
        if nx == end[0] and ny == end[1]:
            print(b)
            break
        if world[ny][nx] == '.':
            que.append((nx, ny, d, b))
            visited[ny][nx][b] = True
        elif world[ny][nx] == '!':
            que.append((nx, ny, d, b))
            for td in dirs_map[d]:
                que.appendleft((nx, ny, td, b + 1))
                visited[ny][nx][b + 1] = True
else:
    print(0)
