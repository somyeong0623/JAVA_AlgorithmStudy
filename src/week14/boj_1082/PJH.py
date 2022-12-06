N = int(input())
number = list(map(int,input().split()))
M = int(input())
dp = [-1]*(M+1)
for i in range(N-1,-1,-1):
  tmp = number[i]
  for j in range(tmp,M+1):
    dp[j] = max(dp[j-tmp]*10+i,i,dp[j])

    #print(j,j-tmp,dp)

#print(dp)
print(dp[j])
