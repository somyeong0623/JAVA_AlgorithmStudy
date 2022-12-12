import sys
from collections import deque

sys.stdin = open('../input.txt')

global graph
global visit

def bfs(start):
    q = deque()
    q.append(start)
    visit[start] = 1

    while q:
        tmp = q.popleft()

        for idx, value in enumerate(graph[tmp]):
            if value and visit[idx] == 0:
                visit[idx] = 1
                q.append(idx)

if __name__=="__main__":
    N = int(sys.stdin.readline())
    M = int(sys.stdin.readline())
    graph = []
    visit = [0] * N

    for _ in range(N):
        graph.append(list(map(int, input().split())))
    trip = list(map(int, input().split()))

    bfs(trip[0]-1)

    flag = True

    for item in trip:
        if visit[item-1] == 0:
            flag = False

    if flag:
        print("YES")
    else:
        print("NO")