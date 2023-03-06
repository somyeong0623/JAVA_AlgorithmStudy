import sys
input = sys.stdin.readline

to=[]
n,c=map(int,input().split())
for _ in range(n):

    to.append(int(input().rstrip()))
to.sort()

left=1
right=to[-1]-to[0]+1
def calculate(mid,to):
    count=1
    l=1
    before=to[0]
    while l<n:
        temp=to[l]-before
        if temp>=mid:
            before=to[l]
            count+=1
        l+=1
    return count

answer=sys.maxsize
while left<=right:
    mid=(left+right)//2
    cal=calculate(mid,to)
    if cal==c:
        answer=min(answer,mid)
    if cal<c:
        right=mid-1
    else:
        left=mid+1

print(left-1)