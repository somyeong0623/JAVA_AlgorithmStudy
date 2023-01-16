import sys
from collections import deque
from heapq import heappush, heappop

sys.stdin = open('../input.txt')

if __name__=="__main__":
    N, M = map(int, sys.stdin.readline().split())
    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

    for i in range(1, N):
        for j in range(M):
            board[i][j] += board[i-1][j]

    T = int(sys.stdin.readline())

    for _ in range(T):
        s_x, s_y, e_x, e_y = map(int, sys.stdin.readline().split())
        answer = sum(board[e_x-1][s_y-1:e_y])
        if s_x > 1:
            answer -= sum(board[s_x-2][s_y-1:e_y])
        print(answer)