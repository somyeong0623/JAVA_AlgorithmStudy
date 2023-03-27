import sys
from copy import deepcopy
input = sys.stdin.readline

n=int(input().rstrip())
e=list(map(int,input().split()))
e.sort()


answer=0

def check(left,right,temp,now):

    while left<right:
        mid=(temp[left]+temp[right])
        if mid>now:
            right-=1
        else:
            if mid==now:
                return 1
            left+=1
    return 0

answer=0
for l in range(n):
    temp=deepcopy(e)
    temp.pop(l)

    now=check(0,n-2,temp,e[l])
    if now==1:
        answer+=1
print(answer)
