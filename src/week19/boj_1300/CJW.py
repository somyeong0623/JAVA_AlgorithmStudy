from sys import stdin

N = int(stdin.readline())
K = int(stdin.readline())

answer = 0

s = 1
e = K

while s < e:
    mid = (s + e) // 2

    # i행에 mid 보다 작은 값들을 전부 더 해줌
    find = sum([min(mid // i, N) for i in range(1, N + 1)])

    if find < K:
        s = mid + 1
    else:
        e = mid

print(mid if s != e else s)
