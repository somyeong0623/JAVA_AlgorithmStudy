import sys,heapq
input=sys.stdin.readline
g=int(input().rstrip())
def dijk(s,now,road,g,h,vertex):
    q=[]
    vertex[s]=0
    heapq.heappush(q,(0,s))
    end=0
    check=0
    while q:
        a1,a2=heapq.heappop(q)#가중치, 지금 정점
        if a2==g or a2==h:
            end+=1
            if end==2:
                check=a2


        #이제 도로를 돌면서 확인을 하고자 함
        for v,e in road[a2]:
            if vertex[v]>a1+e:
                vertex[v]=a1+e
                heapq.heappush(q,(vertex[v],v))
    return check
def dijk1(s,vertex):
    q=[]
    heapq.heappush(q,(vertex[s],s))
    end=0
    while q:
        a1,a2=heapq.heappop(q)#가중치, 지금 정점
        #이제 도로를 돌면서 확인을 하고자 함
        for v,e in road[a2]:
            if vertex[v]>a1+e:
                vertex[v]=a1+e
                heapq.heappush(q,(vertex[v],v))

for _ in range(g):
    n,m,t=map(int,input().split())#교차로, 도로, 목적지 후보의 개수들
    s,g,h=map(int,input().split())#출발지, g와 h교차로 사이에 있는 도로를 지나갔음을 의미를 함
    vertex=[sys.maxsize]*(n+1)
    road=[[]for _ in range(n+1)]
    vertex1=[sys.maxsize]*(n+1)
    hubo=[]#목적지 후보들이 들어가게될 리스트를 의미를 함
    for _ in range(m):
        a,b,d=map(int,input().split())
        road[a].append((b,d))#a도로에서 b로가는 d만큼의 가중치를 가진 도로를 추가 하겠다는 것을 의미
        road[b].append((a,d))
    for _ in range(t):
        hubo.append((int(input().rstrip())))#후보들을 넣어둘 곳을 의미를 함

    start=dijk(s,0,road,g,h,vertex)
    find=vertex[start]

    vertex1[start]=find
    # print(vertex)
    # print(start)

    dijk1(start,vertex1)
    answer=[]
    ans=sys.maxsize
    for i in hubo:
        if vertex[i]!=sys.maxsize:
            if vertex[i]==vertex1[i]:
                answer.append(i)

    answer.sort()
    print(*answer)

