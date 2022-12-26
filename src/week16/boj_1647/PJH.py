import sys
input = sys.stdin.readline

N,M = map(int,input().split())

parent = [i for i in range(N)]
def find(a):
    if parent[a] == a:
        return a
    parent[a] = find(parent[a])
    return parent[a]

def union(a,b):
    a = find(a)
    b = find(b)
    if( a > b):
        parent[a] = b
    elif(a<b):
        parent[b] = a

answer = []
edges = []
for _ in range(M):
    edges.append(list(map(int,input().split())))

edges.sort(key=lambda x:x[2])

for edge in edges:
    a = edge[0] - 1
    b = edge[1] - 1 
    if find(a) != find(b):
        union(a,b)
        answer.append(edge[2])
answer.pop()
print(sum(answer))