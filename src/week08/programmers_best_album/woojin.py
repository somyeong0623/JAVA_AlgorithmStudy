def solution(genres, plays):
    gen=dict()
    answer=[]
    #초기 입력 받아 주는 부분
    for i in range (0,len(plays)):
        if genres[i] not in gen:
            gen[genres[i]]=[]
            gen[genres[i]].append((i,plays[i]))
        else:
            gen[genres[i]].append((i,plays[i]))
    rank=[]
    #장르별 순위 지정하기 위한 부분
    for l,v in gen.items():
        temp=0
        for l1 ,l2 in v:
            temp+=l2
        rank.append((l,temp))
    rank=sorted(rank,key=lambda rank:-rank[1])

    #장르별로 정답 도출 하는 부분
    for l in rank:
        temp=gen[l[0]]
        #장르내에서 정렬 해주는 부분
        temp=sorted(temp,key=lambda x:(-x[1],x[0]))
        for i in range(2):
            answer.append(temp[i][0])
            #길이가 1일경우 처리 해주는 부분
            if(len(temp)==1):
                break

    return answer