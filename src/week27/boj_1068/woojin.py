import sys
input = sys.stdin.readline


n=int(input().rstrip())
tree=[[]for _ in range(n)]

e=list(map(int,input().split()))
last=int(input().rstrip())

start=[]
for l in range(n):
    now=e[l]
    if now!=-1:
        tree[now].append(l)
    else:
        start.append(l)


def go(tree,now):

    if len(tree[now])==0:
        tree[now].append(0)
        return
    for l in tree[now]:
        go(tree,l)



for l in tree[last]:
    go(tree,l)


now=e[last]

if now!=-1 and last in tree[now]:
    tree[now].remove(last)

tree[last].append(0)

answer=0
for l in range(n):
    if len(tree[l])==0:
        answer+=1
print(answer)