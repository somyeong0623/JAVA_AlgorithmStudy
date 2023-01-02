import sys
sys.setrecursionlimit(10**5)
input=sys.stdin.readline

n=int(input().rstrip())
ans=-sys.maxsize
tree=[[]for _ in range(n+1)]

for i in range(1,n+1):
    u=list(map(int,input().split()))
    l=1
    s=u[0]
    while l<=len(u)-2:
        a2=u[l]
        a3=u[l+1]
        l+=2
        tree[s].append((a2, a3))


answer=0
visit=[0]*(n+1)
def dfs(vertex,value):
    global answer


    left=0
    right=0
    jason=[]
    for i in range(len(tree[vertex])):
        down_left=tree[vertex][i]
        if visit[down_left[0]]==0:
            visit[down_left[0]]=1
            left=dfs(down_left[0],down_left[1]+value)
            jason.append(left)
            visit[down_left[0]]=0
    if len(jason)>0:
        if len(jason)>=2:
            jason.sort()
            answer=max(answer,jason[-1]+jason[-2]-value*2)
        elif len(jason)==1:
            answer = max(answer, jason[0] - value )
        return(max(jason))
    else:
        return value

visit[1]=1

ans=dfs(1,0)
print(answer)