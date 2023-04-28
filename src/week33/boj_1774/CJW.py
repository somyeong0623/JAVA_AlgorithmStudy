"""
    https://www.acmicpc.net/problem/1774
    완전그래프에서 각 노드사이의 가중치를 구함.
    최소스패닝 트리를 돌리고
    roads와 겹치는 값을 제외하면 정답.
"""
import math
from sys import stdin


class UnionFind:
    def __init__(self, n):
        self.parent = [i for i in range(n)]

    def find(self, x):
        if self.parent[x] == x:
            return x

        self.parent[x] = self.find(self.parent[x])
        return self.parent[x]

    def union(self, a, b):
        a = self.find(a)
        b = self.find(b)

        self.parent[b] = a


def getDistance(pos1, pos2):
    return math.sqrt((abs(pos2[1] - pos1[1])) ** 2 + (abs(pos2[0] - pos1[0])) ** 2)


N, M = map(int, stdin.readline().strip().split())

gods = dict()

disjointSet = UnionFind(N)

for i in range(N):
    x, y = map(int, stdin.readline().strip().split())
    gods[i] = (x, y)

roads = []

for _ in range(M):
    a, b = map(int, stdin.readline().strip().split())
    temp = (a - 1, b - 1) if b > a else (b, a)
    roads.append(temp)
    disjointSet.union(a - 1, b - 1)

paths = []

for a in range(N - 1):
    for b in range(a + 1, N):
        if (a, b) not in roads:
            paths.append((a, b, getDistance(gods[a], gods[b])))

paths.sort(key=lambda x: x[2])

answer = 0.0

count = 0

for a, b, w in paths:
    a = disjointSet.find(a)
    b = disjointSet.find(b)
    if a != b:
        answer += w
        disjointSet.union(a, b)
        count += 1

        if count >= len(paths) - 1:
            break
print("{:.2f}".format(answer))
