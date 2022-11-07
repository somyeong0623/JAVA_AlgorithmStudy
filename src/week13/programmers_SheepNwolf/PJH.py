def solution(info, edges):
    global answer
    visit = [False]*len(info)
    visit[0] = True
    answer = 0
    dfs(info,edges,visit,[1,0])
    return answer

def dfs(info,edges,visit,sheep_wolf): 
    global answer
    if answer < sheep_wolf[0]:
        answer=sheep_wolf[0]
    for edge in edges: #탐색시작
        if visit[edge[0]] and not visit[edge[1]]: #부모방문했고 자식방문x
            sheep_wolf[info[edge[1]]]+=1
            if sheep_wolf[0]>sheep_wolf[1]:
                visit[edge[1]]=True
                dfs(info,edges,visit,sheep_wolf)
                visit[edge[1]]=False
            sheep_wolf[info[edge[1]]]-=1
            
            
        
