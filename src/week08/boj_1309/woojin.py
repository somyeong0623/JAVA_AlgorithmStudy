import sys, copy, heapq

x=int(input())

d=[[0]*3 for _ in range(100001)]
d[1][0]=1
d[1][1]=1
d[1][2]=1

for i in range(2,x+1):
    d[i][0]=(d[i-1][1]+d[i-1][2])%9901
    d[i][1] = (d[i - 1][0] + d[i - 1][2]) % 9901
    d[i][2] = (d[i - 1][0] + d[i - 1][1]+d[i - 1][2]) % 9901
print(sum(d[x])%9901)