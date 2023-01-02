from sys import stdin
from collections import deque


def topology_sort():
    result = [0] * (N + 1)
    que = deque()
    for i in range(1, N + 1):
        if inArr[i] == 0:
            que.append(i)

    while que:
        qsize = len(que)
        for _ in range(qsize):
            now = que.popleft()

            # 이전에 걸린 시간 + 현재 건물 짓는 시간을 더함
            result[now] += arr[now]
            for i in graph[now]:
                inArr[i] -= 1
                # 먼저 지어야 하는 사전 필요 시간으로 갱신
                result[i] = max(result[i], result[now])
                if inArr[i] == 0:
                    que.append(i)
    return result


N = int(stdin.readline())

inArr = [0] * (N + 1)
arr = [0] * (N + 1)
graph = [[] for i in range(N + 1)]

for i in range(1, N + 1):
    data = list(map(int, stdin.readline().split()))[:-1]
    arr[i] = data[0]
    build = data[1:]

    # 그래프 생성 [사전 필요 데이터 여러개]
    for j in build:
        graph[j].append(i)
        inArr[i] += 1

answer = topology_sort()

for i in range(1, N + 1):
    print(answer[i])
