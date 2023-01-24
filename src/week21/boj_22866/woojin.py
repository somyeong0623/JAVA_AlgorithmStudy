import sys
from collections import deque
input = sys.stdin.readline

n=int(input().rstrip())
arr=list(map(int,input().split()))


total=[[] for _ in range(n+1)]
total_len=[0]*(n+1)


q=deque()


for i in range(1,n+1):
    now=arr[i-1]
    if len(q)==0:
        q.append((now,i))
    else:
        while len(q)>0:
            n1,n2=q.pop()
            if n1>now:
                q.append((n1,n2))
                break
        while len(q)>0:
            t1,t2=q.pop()
            total[i].append(t2)
            q.append((t1,t2))
            break
        total_len[i]+=len(q)
        q.append((now,i))

q=deque()
for i in range(n,0,-1):
    now=arr[i-1]
    if len(q)==0:
        q.append((now,i))
    else:
        while len(q)>0:
            n1,n2=q.pop()
            if n1>now:
                q.append((n1,n2))
                break
        while len(q)>0:
            t1,t2=q.pop()
            total[i].append(t2)
            q.append((t1,t2))
            break
        total_len[i]+=len(q)
        q.append((now,i))


for i in range(1,n+1):
    now=total[i]
    if len(now)==0:
        print(0)
    else:
        min_val=sys.maxsize
        min_now=-1
        now.sort()
        for j in  now:
            if abs(i-j)<min_val:
                min_val=abs(i-j)
                min_now=j

        print(total_len[i],min_now)


