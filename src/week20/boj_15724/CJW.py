from sys import stdin

Y, X = list(map(int, stdin.readline().split()))

arr = [list(map(int, stdin.readline().split())) for _ in range(Y)]
arr.insert(0, [0 for i in range(X + 1)])
for i in arr:
    i.insert(0, 0)
K = int(stdin.readline())

dp = [[0 for _ in range(X + 1)] for _ in range(Y + 1)]
dp[1][1] = arr[1][1]
for y in range(1, Y + 1):
    for x in range(1, X + 1):
        if y == 1 and x == 1:
            continue
        temp = dp[y - 1][x] - dp[y - 1][x - 1]
        dp[y][x] = dp[y][x - 1] + temp + arr[y][x]

for _ in range(K):
    y1, x1, y2, x2 = list(map(int, stdin.readline().split()))
    A = dp[y1 - 1][x1 - 1]
    B = dp[y1 - 1][x2]
    C = dp[y2][x1 - 1]
    D = dp[y2][x2]
    print(D + A - (B + C))

