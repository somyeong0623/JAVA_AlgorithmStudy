import sys
from collections import deque
from copy import deepcopy
input = sys.stdin.readline

n,k=map(str,input().split())

visit=[([0]*11) for _ in range(10**6+1)]
visit[int(n)][0]=1
q=deque()

k=(int)(k)
answer=-sys.maxsize
total=len(n)

n=list(n)
q.append((n,0))

while q:
    a1,a2=q.popleft()
    if a2<k:
        for i in range(total):
            for j in range(i+1,total):
                now=deepcopy(a1)
                temp=now[j]
                now[j]=now[i]
                now[i]=temp
                test=''.join(map(str,now))
                if test[0]=='0':
                    continue
                test_len=len(test)
                test=(int)(test)

                if visit[test][a2+1]==0:
                        visit[test][a2+1]=1
                        q.append((now,a2+1))
    elif a2==k:
        tt=''.join(map(str, a1))
        answer=max(answer,(int)(tt))


if answer==-sys.maxsize:
    print(-1)

else:
    print(answer)