#효율성 테스트x

def solution(board, skill):
    answer = 0
    
    for sk in skill:
        attack_hill = 1
        if sk[0] == 1: # 공격인가 힐인가
            attack_hill = -1
        for i in range(sk[1],sk[3]+1):
            for j in range(sk[2],sk[4]+1):
                board[i][j] += attack_hill*sk[-1]
                
    for i in board:
        for j in i:
            if j>0:
                answer+=1
                
    
    return answer