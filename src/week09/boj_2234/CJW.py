from sys import stdin
from collections import deque

X, Y = list(map(int, stdin.readline().split()))

visited = [[False] * X for _ in range(Y)]

map = [list(map(int, stdin.readline().split())) for _ in range(Y)]

# 서 북 동 남
# W N E S
dirs = ((-1, 0), (0, -1), (1, 0), (0, 1))


def bfs(x, y):
    que = deque()
    que.append((x, y))
    cnt = 1
    visit[y][x] = True
    while que:
        px, py = que.popleft()

        able = [False if map[py][px] >> i & 1 else True for i in range(4)]

        for _able, dir in zip(able, dirs):
            if _able:
                nx = px + dir[0]
                ny = py + dir[1]
                if 0 <= nx < X and 0 <= ny < Y:
                    if visit[ny][nx]:
                        continue
                    visit[ny][nx] = True
                    que.append((nx, ny))
                    cnt += 1

    return cnt


def dfs(x, y):
    result = 0
    if visited[y][x]:
        return 0

    able = [False if map[y][x] >> i & 1 else True for i in range(4)]
    visited[y][x] = True
    result += 1

    for _able, dir in zip(able, dirs):
        if _able:
            nx = x + dir[0]
            ny = y + dir[1]
            if not visited[ny][nx]:
                result += dfs(nx, ny)
    return result


que = deque()
room_count = 0
room_max_length = 0
max_room_size = 0
for y in range(Y):
    for x in range(X):
        if visited[y][x]:
            continue
        room_count += 1
        room_max_length = max(room_max_length, dfs(x, y))

for y in range(Y):
    for x in range(X):
        num = 1
        while num < 9:
            if num & map[y][x]:
                visit = [[0] * X for _ in range(Y)]
                # 벽을 하나씩 뚫어봄
                map[y][x] -= num
                # 뚫어보고 탐색
                max_room_size = max(max_room_size, bfs(x, y))
                # 벽을 하나씩 복구함
                map[y][x] += num
            num *= 2

print(room_count)
print(room_max_length)
print(max_room_size)
