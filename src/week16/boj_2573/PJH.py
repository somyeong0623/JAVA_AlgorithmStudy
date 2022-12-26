#틀린 문제 인터넷에 나온 반례들은 모두 통과했는데 이유를 모르겠다.

import sys
input = sys.stdin.readline
dr = [-1,0,1,0]
dc = [0,1,0,-1]

def remove(nodes,table):
    remove_list = []
    for node in nodes:
        cnt = 0
        r = node[0]
        c = node[1]
        for i in range(4):
            if table[r+dr[i]][c+dc[i]] == 0:
                cnt += 1
        remove_list.append(cnt)
    
    #일괄 적용 빙하를 녹인다.
    for i in range(len(remove_list)):
        nodes[i][2] = max(nodes[i][2]-remove_list[i],0)

        #테이블에도 변경된 내용 적용
        table[nodes[i][0]][nodes[i][1]] = nodes[i][2]
    
    return list(filter(lambda node:node[2]!=0,nodes))

def iceCount(nodes,table):
    cnt = 0
    bool_table = [[False]*len(table[0]) for _ in table ]
    for node in nodes:
        bool_table[node[0]][node[1]] = True
    
    for node in nodes:
        target = []
        if bool_table[node[0]][node[1]]:
            cnt+=1
            bool_table[node[0]][node[1]]=False
            target.append([node[0],node[1]])
            

            while len(target)>0 and len(target)<10:
                target_point = target.pop()
                for i in range(4):
                    t_r = target_point[0]+dr[i]
                    t_c = target_point[1]+dc[i]
                    if bool_table[t_r][t_c]:
                        bool_table[t_r][t_c]=False
                        target.append([t_r,t_c])
                
    return cnt
        


N, M = map(int,input().split())
years = 0
#입력값
table = [list(map(int,input().split())) for _ in range(N)]


# 빙하데이터만 따로 저장
nodes = []
for i in range(len(table)):
    for j in range(len(table[i])):
        if(table[i][j]!=0):
            nodes.append([i,j,table[i][j]])

while True:
    nodes = remove(nodes,table)
    ice_cnt = iceCount(nodes,table)
    years += 1
    if(ice_cnt > 2):
        break
    elif(ice_cnt == 0):
        years = 0
        break

print(years)