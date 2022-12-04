from collections import deque
from sys import stdin


def topology_sort(dp: list):
    global i
    result = []
    que = deque()
    for i in range(N):
        if inArr[i] == 0:
            que.append(i)
            dp[i] = arr[i]

    while que:
        qsize = len(que)
        for _ in range(qsize):
            now = que.popleft()
            for i in graph[now]:
                inArr[i] -= 1
                dp[i] = max(dp[i], dp[now] + arr[i])
                if inArr[i] == 0:
                    que.append(i)
            print(end='')
    return result


T = int(stdin.readline())

for tc in range(1, T + 1):
    # 건물 개수 N, 건설 순서 규칙 수 K
    N, K = list(map(int, stdin.readline().split()))
    inArr = [0] * N

    arr = list(map(int, stdin.readline().split()))
    graph = [[] for i in range(N + 1)]
    for _ in range(K):
        f, t = list(map(int, stdin.readline().split()))
        graph[f - 1].append(t - 1)
        inArr[t - 1] += 1

    last = int(stdin.readline()) - 1
    result = 0
    dp = [0] * N
    topologies = topology_sort(dp)
    print(dp[last])
