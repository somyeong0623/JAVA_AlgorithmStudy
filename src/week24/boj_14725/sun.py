import sys
from collections import deque
from heapq import heappush, heappop

sys.stdin = open('../input.txt')

def add(dict, arr):
    if len(arr) == 0:
        return
    if arr[0] not in dict:
        dict[arr[0]] = {}
    add(dict[arr[0]], arr[1:])

def printDict(dict, L):
    for x in sorted(dict.keys()):
        print("--" * L + x)
        printDict(dict[x], L+1)

if __name__=="__main__":
    N = int(sys.stdin.readline())
    dict = {}
    for _ in range(N):
        tmp = list(map(str, sys.stdin.readline().split()))
        add(dict, tmp[1:])
    printDict(dict, 0)