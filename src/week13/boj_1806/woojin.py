import sys
input = sys.stdin.readline
n,m=map(int,input().split())
arr=list(map(int,input().split()))
left=0
right=0
sum=arr[0]
answer=sys.maxsize
while 1:
    if(sum>=m):
        answer=min(answer,right-left+1)
        #그렇게 한 후에
        sum-=arr[left]
        left+=1
    elif(sum<m):
        if(right!=n-1):
            right+=1
            sum+=arr[right]
        else:
            sum -= arr[left]
            left += 1
    if(left==n):
        break
if(answer!=sys.maxsize):
    print(answer)
else:
    print(0)