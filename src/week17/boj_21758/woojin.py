import sys

input=sys.stdin.readline

n=int(input().rstrip())
total=list(map(int,input().split()))

ssum=[0]*(n)
ssumr=[0]*n
ssum[0]=total[0]
for i in range(1,n):
    ssum[i]=ssum[i-1]+total[i]

ssumr[n-1]=total[n-1]
for i in range(n-2,-1,-1):
    ssumr[i]=ssumr[i+1]+total[i]

answer=-1
#벌벌꿀
for i in range(1,n-1):
    left=ssum[n-1]-total[0]-total[i]
    right=ssum[n-1]-ssum[i]
    temp=left+right
    answer=max(answer,temp)

#벌꿀벌
for i in range(1,n-1):
    left=ssum[i]-total[0]
    right=ssum[n-2]-ssum[i]+total[i]
    temp=left+right
    answer = max(answer, temp)


#꿀벌벌
for i in range(1,n-1):
    left=ssumr[0]-ssumr[i]
    right=ssumr[0]-total[i]-total[n-1]
    temp=left+right
    answer = max(answer, temp)

print(answer)