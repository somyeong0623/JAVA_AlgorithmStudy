def solution(n, info):
    global max_state
    global max_answer
    max_state = 0
    max_answer = [0]*len(info)
    answer = [0]*len(info)
    combi(n,0,info,answer)
    if max_state == 0:
        max_answer = [-1]
    return max_answer

def combi(n,start,info,answer):
    if n==0:
        global max_state
        global max_answer
        tmp = calc(info,answer)
        
        if tmp>max_state:
            max_state = tmp
            max_answer = answer.copy()
        elif tmp==max_state:
            for i in range(10,-1,-1):
                if max_answer[i] < answer[i]:
                    max_state = tmp
                    max_answer = answer.copy()
                    return
                elif max_answer[i] > answer[i]:
                    return
            
        return
    elif start == 10:
        answer[-1] += n
        combi(0,11,info,answer)
        answer[-1] -= n
    
    for i in range(start,11):
        tmp = info[i]-answer[i]+1
        if n>=tmp:
            answer[i]+=tmp
            combi(n-tmp,i+1,info,answer)
            answer[i]-=(tmp)
        else:
            combi(n,i+1,info,answer)
            
def calc(info,answer):
    lion = 0
    apeach = 0
    data = list(range(11))
    data.reverse()
    for i in range(11):
        if answer[i] > info[i]:
            lion += data[i]
        elif info[i] != 0: # info가 크거나 같을 경우에 0이 아닌 경우.
            apeach += data[i]
    return lion-apeach
            
            
