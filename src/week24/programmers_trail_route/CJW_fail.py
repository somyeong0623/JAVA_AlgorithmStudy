import heapq
from collections import defaultdict


def solution(n, paths, gates, summits):
    INF = int(10e9)
    distances = [INF] * (n + 1)
    hq = []

    summits = set(summits)
    graph = defaultdict(list)
    for i, j, w in paths:
        graph[i].append((w, j))
        graph[j].append((w, i))

    for gate in gates:
        heapq.heappush(hq, (0, gate))
        distances[gate] = 0

    while hq:
        p_d, p_n = heapq.heappop(hq)

        # 산봉우리거나, 더 큰 weight라면 방문 안함.
        if p_n in summits or p_d > distances[p_n]:
            continue

        for d, n in graph[p_n]:
            n_d = max(d, p_d)

            if n_d < distances[n]:
                distances[n] = n_d
                heapq.heappush(hq, (n_d, n))

    answer = [0, INF]
    for summit in summits:
        if distances[summit] < answer[1]:
            answer[0] = summit
            answer[1] = distances[summit]

    return answer
