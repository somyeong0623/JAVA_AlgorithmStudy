import sys
import heapq
input = sys.stdin.readline

from string import ascii_uppercase
n=int(input().rstrip())

total=[]
max_len=-sys.maxsize
total_alphabet=[]
cal=[0]*len(ascii_uppercase)
for _ in range(n):
    e=list(map(str,input().rstrip()))
    total.append(e)
    max_len=max(max_len,len(e))

#이제 최대 치의 길이를 파악을 했으니 그 후로 작업을 하도록 하자

#-1을 붙여서 길이를 동일 하게 해줌
for l in range(n):
    now=total[l]
    if len(now)<max_len:
        check=max_len-len(now)
        temp=[]
        for k in range(check):
            temp.append("-1")
        total[l]=temp+total[l]
total_alpha=[[] for _ in range(max_len)]

for k in range(max_len-1,-1,-1):
    for l in total:
        now=l
        if now[k]!="-1":
            total_alpha[k].append(now[k])


u=max_len-1
for l in range(len(total_alpha)):
    now=10**u
    for k in total_alpha[l]:
        cal[ord(k)-ord("A")]+=now
    u-=1

test=[]
for l in range(len(cal)):
    if cal[l]>0:
        heapq.heappush(test,(-cal[l],l))
now=9
while test:
    a1,a2=heapq.heappop(test)
    cal[a2]=now
    now-=1
answer=0
u=max_len-1
for l in range(len(total_alpha)):
    now=10**u
    for k in total_alpha[l]:
        answer+=cal[ord(k)-ord("A")]*(now)
    u-=1
print(answer)
