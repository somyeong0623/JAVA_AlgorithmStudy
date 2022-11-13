edges = []
info = []
answer = 0
visited = []
def solution(paraminfo, paramEdges):
    global edges
    global answer
    global info
    global visited
    answer = -2147000000
    edges = paramEdges
    info = paraminfo
    visited = [0] * len(info)
    visited[0] = 1
    dfs(1, 0)
    return answer

def dfs(sheep, wolf):
    global answer
    if (sheep > wolf):
        answer = max(answer, sheep)
    else:
        return
    for i in range(len(edges)):
        parent = edges[i][0]
        child = edges[i][1]
        isWolf = info[child]
        if (visited[parent] and not visited[child]):
            visited[child] = 1
            if(isWolf):
                dfs(sheep, wolf+1)
            else:
                dfs(sheep+1,wolf)
            visited[child] = 0