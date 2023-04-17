from sys import stdin
from collections import deque

dirs = ((0, 1), (0, -1), (1, 0), (-1, 0))


def bfs(r1, c1):
    que = deque()
    que.append((r1, c1))
    visited[r1][c1] = True
    while que:
        x, y = que.popleft()
        for dx, dy in dirs:
            nx = dx + x
            ny = dy + y
            # 범위 체크
            if nx < 0 or ny < 0 or nx >= N or ny >= N:
                continue
            # 방문 체크
            if visited[nx][ny]:
                continue
            # 다리 체크
            if adj_mtx[x][y][nx][ny]:
                continue

            que.append((nx, ny))
            visited[nx][ny] = True


N, K, R = map(int, stdin.readline().strip().split())  # n*n, k: 마리, r: 정해진 길

adj_mtx = [[[[False for _ in range(N)] for _ in range(N)] for _ in range(N)] for _ in range(N)]
answer = 0
# 길 위치 담기
for _ in range(R):
    r1, c1, r2, c2 = map(int, stdin.readline().strip().split())
    adj_mtx[r1 - 1][c1 - 1][r2 - 1][c2 - 1] = True
    adj_mtx[r2 - 1][c2 - 1][r1 - 1][c1 - 1] = True

# 소 위치 정보
cows = list()
for _ in range(K):
    a, b = map(int, stdin.readline().strip().split())
    cows.append((a - 1, b - 1))

# 방문 탐색
for idx, (x, y) in enumerate(cows):
    visited = [[False] * N for _ in range(N)]
    # 2. 현재 소가 정해진 길 없이 가는 경로를 탐색
    bfs(x, y)
    for r2, c2 in cows[idx + 1:]:
        # 3. 방문을 완료하지 못한 경우 결과 + 1
        if not visited[r2][c2]:
            answer += 1
print(answer)
