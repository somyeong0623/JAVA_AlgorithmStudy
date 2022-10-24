import sys

if __name__=="__main__":
    n=int(sys.stdin.readline())
    T=list()
    P=list()
    dp = [0] * (n+1)
    for i in range(n):
        a, b=map(int, sys.stdin.readline().split())
        T.append(a)
        P.append(b)
    res=-2147000000
    max_value = 0;
    for i in range(n):
        max_value = max(max_value, dp[i])
        if i + T[i] > n:
            continue;
        dp[i + T[i]] = max(max_value + P[i], dp[i + T[i]])
    print(max(dp))
