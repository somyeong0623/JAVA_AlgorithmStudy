def solution(board, skill):
    answer = 0
    row=len(board[0])+1
    col=len(board)+1
 
    sumz=[[0]*row for _ in range(col)]
    

    for ty,x1,y1,x2,y2,degree in skill:
        if ty==1:
            degree*=-1
        sumz[x1][y1]+=degree
        sumz[x1][y2+1]-=degree
        sumz[x2+1][y1]-=degree
        sumz[x2+1][y2+1]+=degree
    
   # 행 
    for i in range(len(sumz) - 1):
        for j in range(len(sumz[0]) - 1):
            sumz[i][j + 1] += sumz[i][j]
 
    # 열
    for j in range(len(sumz[0]) - 1):
        for i in range(len(sumz) - 1):
            sumz[i + 1][j] += sumz[i][j]
 

    print(sumz)
    
    
    for i in range(len(board)):
        for j in range(len(board[i])):
            board[i][j]+=sumz[i][j]
            if board[i][j]>0:
                answer+=1

    
    
    
    
    return answer