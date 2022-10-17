

from collections import deque
#bfs(len(tickets)+1,route,0,[],"ICN")
def dfs(end,route,cnt,ans,now):#
    if (now not in route or len(route[now]) == 0):
        if (cnt == end):
            return ans
        else:
            return []
    for i in route[now]:
        temp=route[now].pop(0)
        ans.append(temp)
        z=dfs(end,route,cnt+1,ans,temp)
        if(len(z)!=0):
            return z
        route[now].append(temp)
        ans.pop(-1)

    return []

def solution(tickets):
    answer = []

    # 항상 시작은 icn에서 시작을 하는거임

    route = dict()
    count = 0
    tickets.sort()
    for i in tickets:
        start, end = i[0], i[1]
        if start not in route:
            route[start] = []
            route[start].append(i[1])
        else:
            route[start].append(i[1])

    last = []
    answer=dfs(len(tickets)+1,route,1,["ICN"],"ICN")


    return answer