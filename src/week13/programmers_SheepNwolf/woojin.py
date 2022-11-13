def dfs(sheep, wolf,visit,edge,info ) : # 양개수 , 늑대개수 ,현재 방문한얘들 ,visit 배열
    global answer
    for l in range (0,len(visit)):
        if(visit[l]==1):
            for a1 in edge[l]:
                if( visit[a1]==0):#방문을 하지 않았는데
                   t=info[a1]
                   if(t==0):
                       visit[a1]=1
                       answer=max(answer,sheep+1)
                       dfs(sheep+1,wolf,visit,edge,info)
                       visit[a1]=0
                   elif(t==1):
                       if(sheep>wolf+1):
                           visit[a1]=1
                           dfs(sheep, wolf+1, visit, edge, info)
                           visit[a1] = 0



def solution(info, edges):
    global answer

    answer = 1
    # 새로 갈수 있는곳이 발견 되었을시 지금까지 방문한 곳 배열을 넘겨주고
    #
    visit=[0 for i in range(len(info))]
    now=[]
    edge=[[]for i in range(len(info))]
    for i in edges:
        a1,a2=i
        edge[a1].append(a2)

    visit[0]=1
    #0 은양 1 은 늑대
    #정점을 보고 나온 정점의 edge들중에 탐색 가능한게 있으면 탐색을 해보도록 하자
    dfs(1, 0,visit,edge,info )  # 양개수 , 늑대개수 ,현재 방문한얘들 ,visit 배열


    return answer