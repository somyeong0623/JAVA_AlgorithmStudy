import sys
input=sys.stdin.readline

r,c,k=map(int,input().split())
r-=1
c-=1
graph=[]

for _ in range(3):
    graph.append(list(map(int,input().split())))

time=0

row=3
col=3


def makerow(graph,dir):
    global row,col
    total=[]
    ee=0
    op=0
    #행을 진행하면서 계산을 해야지
    for i in range(len(graph)):
        #한 행 안에서 연산을 진행 해 볼거임
        cal = dict()
        for k in graph[i]:
            if k!=0:
                if k not in cal:
                    cal[k]=1
                else:
                    cal[k]+=1
        temp=[]
        for l in cal.items():
            temp.append(l)
        temp=sorted(temp,key=lambda  x: (x[1],x[0]) )
        op=max(op,len(temp)*2)
        temp_1=[]
        for l in temp:
            temp_1.append(l[0])
            temp_1.append(l[1])

        total.append(temp_1)

    for l in total:
        if len(l)<op:
            while(len(l)<op):
                l.append(0)
    if dir==1:
        return list(zip(*total))
    else:
        return total


while time<=100:

    if(0<=r<len(graph) and 0<=c<len(graph[0]) and graph[r][c]==k ):
        print(time)
        sys.exit(0)

    #r,c연산중에서 어디를 진행을 할지를 정하도록 해야함
    if(len(graph)>=len(graph[0])):
        graph=makerow(graph,0)
    else:
         graph=makerow(list(zip(*graph)),1)
    time+=1


print(-1)