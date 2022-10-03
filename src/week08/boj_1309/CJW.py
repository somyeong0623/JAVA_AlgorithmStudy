from sys import stdin

N = int(stdin.readline())

dp = [[1, 1, 1] for _ in range(N + 1)]

for i in range(2, N + 1):
    # XO
    dp[i][0] = (dp[i - 1][1] + dp[i - 1][2]) % 9901
    # OX
    dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % 9901
    # XX
    dp[i][2] = (dp[i - 1][1] + dp[i - 1][0] + dp[i - 1][2]) % 9901

print(sum(dp[N]) % 9901)
