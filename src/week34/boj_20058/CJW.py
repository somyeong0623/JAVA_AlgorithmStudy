from sys import stdin
from collections import deque

# 좌 상 우 하
dirs = ((-1, 0), (0, -1), (1, 0), (0, 1))
N, Q = map(int, stdin.readline().strip().split())

world = [list(map(int, stdin.readline().strip().split())) for _ in range(2 ** N)]

arr = list(map(int, stdin.readline().strip().split()))


def get_mtx(sx, sy, ex, ey):
    return [row[sx:ex] for row in world[sy:ey]]


def rotate_mtx(matrix):
    n = len(matrix)
    # 행과 열을 바꾼 새로운 행렬 생성
    new_matrix = [[matrix[j][i] for j in range(n)] for i in range(n)]
    # 각 행 뒤집기
    for row in new_matrix:
        row.reverse()
    return new_matrix


def get_melt(sx, sy, size):
    pos = []
    for y in range(len(world)):
        for x in range(len(world)):
            count = 0
            if y - 1 >= 0:
                count += 1 if world[y - 1][x] > 0 else 0

            if y + 1 < len(world):
                count += 1 if world[y + 1][x] > 0 else 0

            if x - 1 >= 0:
                count += 1 if world[y][x - 1] > 0 else 0

            if x + 1 < len(world):
                count += 1 if world[y][x + 1] > 0 else 0

            if world[y][x] > 0 and count < 3:
                pos.append((x, y))
    return pos


def push_mtx(sx, sy, mtx):
    for y in range(sy, sy + len(mtx)):
        for x in range(sx, sx + len(mtx)):
            world[y][x] = mtx[y - sy][x - sx]


for l in arr:
    comb = []
    for i in range(0, 2 ** N, 2 ** l):
        comb.append(i)

    # 탐색 시작 위치를 미리 구한다.
    start_pos = []

    # make me
    for y in comb:
        for x in comb:
            start_pos.append((y, x))

    for sx, sy in start_pos:
        # 특정 구역을 가져와서 돌리고 넣는다.
        mtx = get_mtx(sx, sy, sx + 2 ** l, sy + 2 ** l)
        push_mtx(sx, sy, rotate_mtx(mtx))

    pos = get_melt(sx, sy, 2 ** l)
    for x, y in pos:
        world[y][x] -= 1

print(sum(sum(row) for row in world))
visited = [[False for _ in range(2 ** N)] for _ in range(2 ** N)]

answer = 0
for y in range(len(world)):
    for x in range(len(world)):
        if world[y][x] == 0 or visited[y][x]:
            continue
        temp = 1
        que = deque()
        que.append((x, y))
        visited[y][x] = True

        while que:
            cx, cy = que.pop()
            for dx, dy in dirs:
                nx = cx + dx
                ny = cy + dy
                if not (0 <= nx < len(world) and 0 <= ny < len(world)):
                    continue

                if visited[ny][nx]:
                    continue
                if world[ny][nx] <= 0:
                    continue
                visited[ny][nx] = True
                temp += 1
                que.append((nx, ny))
        answer = max(answer, temp)
print(answer)