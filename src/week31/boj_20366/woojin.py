import sys

input = sys.stdin.readline

n = int(input().rstrip())
e = list(map(int, input().split()))

e.sort()

ans = sys.maxsize

for i in range(n):
    for j in range(i + 3, n):
        temp = e[i] + e[j]
        left = i + 1
        right = j - 1
        while left < right:
            mid = e[left] + e[right]
            now=mid-temp
            ans=min(ans,abs(now))
            if now>0:
                right-=1
            else:
                left+=1

print(ans)
