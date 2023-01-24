import sys
input = sys.stdin.readline


n,m=map(int,input().split())
k=int(input().rstrip())

arr=[]
for _ in range(n):
    t=list(map(str,input()))
    arr.append(t)

jungle=[[0]*(m+1) for _ in range(n+1)]
sea=[[0]*(m+1) for _ in range(n+1)]
ice=[[0]*(m+1) for _ in range(n+1)]

#합 구하는 과정을 해보자
for i in range(1,n+1):
    for j in range(1,m+1):
        jungle[i][j]=jungle[i-1][j]+jungle[i][j-1]-jungle[i-1][j-1]
        sea[i][j] = sea[i - 1][j] + sea[i][j - 1] - sea[i - 1][j - 1]
        ice[i][j] = ice[i - 1][j] + ice[i][j - 1] - ice[i - 1][j - 1]
        if arr[i-1][j-1]=="J":
            jungle[i][j]+=1
        elif arr[i-1][j-1]=="O":
            sea[i][j]+=1
        else:
            ice[i][j]+=1


for _ in range(k):
     x1,y1,x2,y2=map(int,input().split())
     x1-=1
     y1-=1
     x2-=1
     y2-=1

     #정글, 바다, 얼음
     junz=jungle[x2+1][y2+1]-jungle[x1][y2+1]-jungle[x2+1][y1]+jungle[x1][y1]
     seaz=sea[x2+1][y2+1]-sea[x1][y2+1]-sea[x2+1][y1]+sea[x1][y1]
     icez=ice[x2+1][y2+1]-ice[x1][y2+1]-ice[x2+1][y1]+ice[x1][y1]
     print(str(junz)+" "+str(seaz)+" "+str(icez))
