from sys import stdin
from collections import deque

"""
    0 : 빈 공간
    1 : 뱀
    5 : 사과
    
"""
# 우하좌상
dirs = ((1, 0), (0, 1), (-1, 0), (0, -1))

N = int(stdin.readline())
world = [[0] * N for y in range(N)]
apple_cnt = int(stdin.readline())

for _ in range(apple_cnt):
    y, x = list(map(int, stdin.readline().split()))
    world[y - 1][x - 1] = 5  # 사과

dir_cnt = int(stdin.readline())
time_que = deque()

for _ in range(dir_cnt):
    t, d = list(map(str, stdin.readline().split()))
    time_que.append((int(t), d))

snake_set = set()

snake_que = deque()
snake_que.append((0, 0))
snake_set.add((0, 0))
time = 0

dir = 0

time_wait = time_que.popleft()

while True:
    if time_wait is not None and time_wait[0] == time:
        if time_wait[1] == 'D':
            dir += 1
        else:
            dir -= 1

        if dir >= 4:
            dir = 0
        if dir < 0:
            dir = 3
        if len(time_que) > 0:
            time_wait = time_que.popleft()
        else:
            time_wait = None

    x, y = snake_que[len(snake_que) - 1]

    nx, ny = x + dirs[dir][0], y + dirs[dir][1]

    if not (0 <= nx < N and 0 <= ny < N):
        break

    loop_break = False
    if len(snake_que) > 1:
        for hx, hy in snake_que:
            if hx == nx and hy == ny:
                loop_break = True
                break
    if loop_break:
        break

    snake_que.append((nx, ny))
    if world[ny][nx] == 5:
        world[ny][nx] = 0
    else:
        snake_que.popleft()

    time += 1
print(time + 1)