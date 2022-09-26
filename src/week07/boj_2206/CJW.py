import copy
from sys import stdin
from queue import deque

# 상 하 좌 우
dirs = ((0, -1), (0, 1), (-1, 0), (1, 0))


def BFS(world):
    que = deque()
    que.append((0, 0, 0))

    visited = [[[0] * 2 for _ in range(X)] for _ in range(Y)]
    visited[0][0][0] = 1
    while que:
        px, py, pd = que.popleft()

        if px == X - 1 and py == Y - 1:
            return visited[py][px][pd]

        for dir in dirs:
            nx = px + dir[0]
            ny = py + dir[1]
            if nx < 0 or nx >= X or ny < 0 or ny >= Y:
                continue

            # 이동할 수 있는데 방문을 안했다면
            if world[ny][nx] == 0 and visited[ny][nx][pd] == 0:
                visited[ny][nx][pd] = visited[py][px][pd] + 1
                que.append([nx, ny, pd])
            # 이동할 수 없는데 벽을 뚫을 수 있다면
            elif world[ny][nx] == 1 and pd == 0:
                # 한 번은 뚫어도 된다.
                visited[ny][nx][pd + 1] = visited[py][px][pd] + 1
                que.append([nx, ny, pd + 1])

    return -1


Y, X = list(map(int, stdin.readline().split(" ")))
world = [[x for x in list(map(int, stdin.readline().strip()))] for _ in range(Y)]

result = BFS(world)

print(result)
