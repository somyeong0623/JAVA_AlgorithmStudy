def solution(tickets):
    global answer
    global len_answer
    len_answer = len(tickets)
    answer = []
    tickets.sort()
    for i in range(len(tickets)):
        if tickets[i][0] =="ICN":
            tmp = tickets[i]
            del tickets[i]
            DFS(tickets,tmp[1],[]+[tmp[0]])
            tickets.insert(i,tmp)
        
        
    
    return answer

def DFS(tickets,start,log):
    global answer
    if len(tickets)==0 and len(answer)==0:
        answer = log+[start]
        return
    
    for i in range(len(tickets)):
        if tickets[i][0] == start and len(answer)==0:
            tmp = tickets[i]
            del tickets[i]
            DFS(tickets,tmp[1],log+[tmp[0]])
            tickets.insert(i,tmp)
            
            
    
    
    
    
    