import sys
import heapq

sys.stdin = open('../input.txt')

if __name__=="__main__":
    N = int(sys.stdin.readline())
    problem = []

    for _ in range(N):
        deadLine, cupCount = map(int, sys.stdin.readline().split())
        problem.append((deadLine, cupCount))

    problem.sort()

    result = []

    for i in problem:
        heapq.heappush(result, i[1])
        if i[0] < len(result):
            heapq.heappop(result)

    print(sum(result))