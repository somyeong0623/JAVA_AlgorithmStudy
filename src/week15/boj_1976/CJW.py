from queue import deque
from sys import stdin

N = int(stdin.readline())
M = int(stdin.readline())

adjMtx = [list(map(int, stdin.readline().split())) for i in range(N)]
adjList = dict()
for y, y_val in enumerate(adjMtx):
    for x, x_val in enumerate(y_val):
        if x_val == 1:
            if x not in adjList:
                adjList[x] = []

            adjList[x].append(y)

arr = list(map(int, stdin.readline().split()))


# a -> b로 가는 길이 있는지 확인
def bfs(a, b, adjList):
    visited = [False] * (M + 9999)
    que = deque()
    que.append(a)
    visited[a] = True

    while que:
        cur = que.popleft()
        if cur == b:
            return True
        if cur in adjList:
            for nxt in adjList[cur]:
                if not visited[nxt]:
                    que.append(nxt)
                    visited[nxt] = True

    return False


def result():
    # 다음 번째를 갈 수 있는지 확인
    for i in range(len(arr) - 1):
        a = arr[i] - 1
        b = arr[i + 1] - 1
        if not bfs(a, b, adjList):
            break

    else:
        return 'YES'
    return 'NO'


print(result())
