from collections import defaultdict, deque
from itertools import permutations
from copy import deepcopy
delta = [[-1, 0], [0, 1], [1, 0], [0, -1]]


def move(board, start, end):
    if start == end:
        return 0
    queue = deque()
    queue.append((start[0], start[1], 0))
    visited = [[True] * 4 for _ in range(4)]
    while (queue):
        x, y, c = queue.popleft()
        for i in range(4):
            next_x = x + delta[i][0]
            next_y = y + delta[i][1]
            ctrl_x = x
            ctrl_y = y
            while True:
                ctrl_x += delta[i][0]
                ctrl_y += delta[i][1]
                if not (0 <= ctrl_x < 4 and 0 <= ctrl_y < 4):
                    ctrl_x -= delta[i][0]
                    ctrl_y -= delta[i][1]
                    break
                if (board[ctrl_x][ctrl_y] != 0):
                    break
            if (next_x, next_y) == end or (ctrl_x, ctrl_y) == end:
                return c + 1
            if (0 <= next_x < 4 and 0 <= next_y < 4 and visited[next_x][next_y]):
                visited[next_x][next_y] = False
                queue.append((next_x, next_y, c + 1))
            if (visited[ctrl_x][ctrl_y]):
                visited[ctrl_x][ctrl_y] = False
                queue.append((ctrl_x, ctrl_y, c + 1))


def check(board, card, current_location, order, total_count):
    if (len(order) == 0):
        return total_count
    idx = order[0] + 1
    order_move_count = move(board, current_location, card[idx][0]) + move(board, card[idx][0], card[idx][1]) + 2
    reverse_order_move_count = move(board, current_location, card[idx][1]) + move(board, card[idx][1], card[idx][0]) + 2

    next_board = deepcopy(board)
    next_board[card[idx][0][0]][card[idx][0][1]] = 0
    next_board[card[idx][1][0]][card[idx][1][1]] = 0

    if order_move_count < reverse_order_move_count:
        return check(next_board, card, card[idx][1], order[1:], total_count + order_move_count)
    else:
        return check(next_board, card, card[idx][0], order[1:], total_count + reverse_order_move_count)

if __name__=="__main__":
    board = [[1, 0, 0, 3], [2, 0, 0, 0], [0, 0, 0, 2], [3, 0, 1, 0]]
    r = 1
    c = 0
    answer = 1247000000
    card = defaultdict(list)
    for i in range(4):
        for j in range(4):
            num = board[i][j]
            if num != 0:
                card[num].append((i, j))
    for case in permutations(range(len(card)), len(card)):
        answer = min(answer, check(board, card, (r, c), case, 0))

    print(answer)


def solution(board, r, c):
    answer = 1247000000
    card = defaultdict(list)
    for i in range(4):
        for j in range(4):
            num = board[i][j]
            if num != 0:
                card[num].append((i, j))
    for case in permutations(range(len(card)), len(card)):
        answer = min(answer, check(board, card, (r, c), case, 0))

    return answer