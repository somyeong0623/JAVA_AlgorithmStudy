import sys
from heapq import heappush, heappop

sys.stdin = open('../input.txt')

global N, M
delta = [[1, 0], [0, 1], [-1, 0], [0, -1]]

if __name__=="__main__":
    N = int(sys.stdin.readline())
    board = []
    for _ in range(N):
        board.append(list(map(int, sys.stdin.readline().rstrip())))

    visited = [[0]* N for _ in range(N)]
    heap = []
    heappush(heap, [0, 0, 0])
    visited[0][0] = 1

    while(heap):
        tmp = heappop(heap)
        if tmp[1] == N-1 and tmp[2] == N-1:
            print(tmp[0])
            exit(0)
        for i in range(4):
            dx = tmp[1] + delta[i][0]
            dy = tmp[2] + delta[i][1]
            if 0 <= dx < N and 0 <= dy < N and visited[dx][dy] == 0:
                visited[dx][dy] = 1
                if board[dx][dy] == 0:
                    heappush(heap, [tmp[0]+1, dx, dy])
                else:
                    heappush(heap, [tmp[0], dx, dy])
    print(visited)

