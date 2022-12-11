# 1976 여행가자
def find(a,parent):
    if a == parent[a]:
        return a
    else:
        parent[a] = find(parent[a],parent)
        return parent[a]

def union(a,b,parent):
    a = parent[a]
    b = parent[b]
    if a>b:
        parent[a] = b
    else:
        parent[b] = a

import sys
read = sys.stdin.readline
table = []
plan_list = []
parent = []

city_cnt = int(read())
plan_cnt = int(read())
parent = list(range(city_cnt))

for i in range(city_cnt):
    table.append(list(map(int,read().split())))

plan_list = list(map(int,read().split()))
for i in range(len(plan_list)):
    plan_list[i] -= 1
for i,target_city in enumerate(table):
     for j,able in enumerate(target_city):
        if able==1:
            if find(i,parent) != find(j,parent):
                union(i,j,parent)

for i in range(len(parent)):
    parent[i] = find(i,parent)

answer = 'YES'
for i in range(len(plan_list)-1):
    if parent[plan_list[i]] != parent[plan_list[i+1]]:
        answer = 'NO'
        break
print(answer)

