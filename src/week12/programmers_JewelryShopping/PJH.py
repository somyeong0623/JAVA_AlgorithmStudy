def solution(gems):
    gem_list = dict()
    answer = [0,len(gems)]
    for i in set(gems):
        gem_list[i] = 0
    
    start = 0
    end = 0
    
    gem_list[gems[start]]+=1
    while end!=len(gems):
        if check(gem_list):
            while gem_list[gems[start]]>1:
                gem_list[gems[start]]-=1
                start+=1
            
            if answer[1]-answer[0] > end-start:
                answer[0] = start
                answer[1] = end
            end+=1
        else:
            end+=1
            if end == len(gems):
                break
            gem_list[gems[end]] +=1
                
    answer[0]+=1
    answer[1]+=1
    return answer

def check(gem_list):
    for i in gem_list.keys():
        if gem_list[i]==0:
            return False
    return True