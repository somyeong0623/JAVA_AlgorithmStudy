from collections import deque


def write(x1, y1, x2, y2, world):
    for y in range(y2 - y1 + 1):
        for x in range(x2 - x1 + 1):
            world[y1 + y][x1 + x] = True


def side(world):
    newWorld = [[False] * (51 * 2) for _ in range(51 * 2)]
    visited = [[False] * (51 * 2) for _ in range(51 * 2)]
    que = deque()

    # 상 우 하 좌, 대각
    dirs = ((0, -1), (1, 0), (0, 1), (-1, 0),
            # 상좌 상우 하우 하좌
            (-1, -1), (1, -1), (1, 1), (-1, 1))

    for y in range(51 * 2):
        for x in range(51 * 2):
            if not world[y][x]:
                que.appendleft((x, y))
                visited[y][x] = True
                while que:
                    x, y = que.pop()
                    for dx, dy in dirs:
                        nx, ny = x + dx, y + dy
                        if 0 <= nx <= 101 and 0 <= ny <= 101 and not visited[ny][nx]:
                            visited[ny][nx] = True
                            if world[ny][nx]:
                                newWorld[ny][nx] = True
                            else:
                                que.appendleft((nx, ny))
                break
    return newWorld


def bfs(cx, cy, ix, iy, world):
    visited = [[False] * (51 * 2) for _ in range(51 * 2)]
    que = deque()

    # 상 우 하 좌
    dirs = ((0, -1, 'u'), (1, 0, 'r'), (0, 1, 'd'), (-1, 0, 'l'))

    que.appendleft((cx, cy, 0))
    visited[cy][cx] = True
    while que:
        x, y, d = que.pop()
        # print(x, y, d, end=' ')
        for dx, dy, ds in dirs:
            nx, ny = x + dx, y + dy
            if 0 <= nx <= 101 and 0 <= ny <= 101 and not visited[ny][nx]:
                visited[ny][nx] = True
                if nx == ix and ny == iy:
                    return d + 1
                if world[ny][nx]:
                    # print(ds, end=' ')
                    que.appendleft((nx, ny, d + 1))
        # print()


def solution(rectangle, characterX, characterY, itemX, itemY):
    world = [[False] * (51 * 2) for _ in range(51 * 2)]
    for x1, y1, x2, y2 in rectangle:
        write(x1 * 2, y1 * 2, x2 * 2, y2 * 2, world)
    world = side(world)
    result = bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2, world)
    return result // 2