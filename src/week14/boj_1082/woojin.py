import sys
input = sys.stdin.readline
n=int(input().rstrip())
p=list(map(int,input().split()))
m=int(input().rstrip())


#우선 구할수 있는 최소의 리스트 개수를 구하도록 하자

q=list()
total=list()
total_sum=0
#인덱스 1부터 끝까지 최소 되는 것을 구하자
first=sys.maxsize
first_index=-1
for i in range(1,n):
    if p[i]<first:
        first=p[i]
        first_index=i


if(first>m):
    print(0)
else:
    total_sum+=first
    total.append(first)
    q.append(first_index)

    if(p[0]<first):
        first=p[0]
        first_index=0
    while(total_sum<=m):
        if total_sum+first>m:
            break
        total_sum += first
        total.append(first)
        q.append(first_index)

    for l in range(0,len(q)):
        #맨뒤부터 넣을수 있다면 해당 자리수 부터 변경을 하자
        now=total[l]
        for i in range(len(p)-1,0,-1):
            temp=p[i]
            if(temp>=now):
                if(temp-now+total_sum<=m):
                    total_sum+=temp-now
                    q[l]=i
                    total[l]=temp
                    break

    y=""
    for l in q:
        y+=str(l)
    print(y)