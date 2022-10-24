board = []
delta = [[-1, 0], [0, 1], [1, 0], [0, -1]]
from collections import deque
def solution(rectangles, characterX, characterY, itemX, itemY):
    answer = 0
    board = [[-1] * 102 for _ in range(102)]
    for rectangle in rectangles:
        x1, y1, x2, y2 = map(lambda x: x*2, rectangle)
        for i in range(x1, x2+1):
            for j in range(y1, y2+1):
                if x1 < i < x2 and y1 < j < y2:
                    board[i][j] = 0
                elif board[i][j] != 0:
                    board[i][j] = 1
    queue = deque()
    queue.append([characterX*2, characterY*2])
    visited = [[0]*102 for _ in range(102)]
    visited[characterX*2][characterY*2] = 1
    while(queue):
        tmp = queue.popleft()
        if (tmp[0] == itemX*2 and tmp[1] == itemY*2):
            answer = visited[tmp[0]][tmp[1]] // 2
            break
        for i in range(4):
            nx = tmp[0] + delta[i][0]
            ny = tmp[1] + delta[i][1]
            if (0<=nx<102 and 0<=ny<102 and board[nx][ny] == 1 and visited[nx][ny] == 0):
                visited[nx][ny] = visited[tmp[0]][tmp[1]] + 1
                queue.append([nx,ny])
            
    return answer
