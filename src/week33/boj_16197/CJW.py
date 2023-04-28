from sys import stdin
from collections import deque

Y, X = map(int, stdin.readline().strip().split())

world = [list(stdin.readline().strip()) for _ in range(Y)]

# 상 우 하 좌
dirs = ((0, -1, 'u'), (1, 0, 'r'), (0, 1, 'd'), (-1, 0, 'l'))

coins = []


def isBlock(x, y, dx, dy):
    return isArea(x + dx, y + dy) and world[y + dy][x + dx] == '#'


def isArea(x, y):
    return 0 <= x < X and 0 <= y < Y


def soluation():
    for y, items in enumerate(world):
        for x, item in enumerate(items):
            if item == "o":
                coins.append((x, y))

    coin1 = coins[0]
    coin2 = coins[1]

    que = deque()

    # coin1, coin2, abs_xy
    que.appendleft((coin1, coin2, (X // 2, Y // 2)))

    for idx in range(10):
        if len(que) == 0:
            return -1
        for _ in range(len(que)):
            cur_coin1, cur_coin2, cur_abs_xy = que.pop()
            for dx, dy, dir_name in dirs:
                nx = cur_abs_xy[0] + dx
                ny = cur_abs_xy[1] + dy

                drop_coin = 0

                # new_coin_1
                nc1_x, nc1_y = cur_coin1[0], cur_coin1[1]
                if not isBlock(nc1_x, nc1_y, dx, dy):
                    nc1_x += dx
                    nc1_y += dy

                # new_coin_2
                nc2_x, nc2_y = cur_coin2[0], cur_coin2[1]
                if not isBlock(nc2_x, nc2_y, dx, dy):
                    nc2_x += dx
                    nc2_y += dy

                drop_coin += 1 if not isArea(nc1_x, nc1_y) else 0
                drop_coin += 1 if not isArea(nc2_x, nc2_y) else 0

                if drop_coin == 0:
                    # 이전과 같으면
                    if (nc1_x == cur_coin1[0] and nc1_y == cur_coin1[1]) and \
                            (nc2_x == cur_coin2[0] and nc2_y == cur_coin2[1]):
                        continue

                    # 둘이 좌표가 겹치면
                    if nc1_x == nc2_x and nc1_y == nc2_y:
                        continue

                    # 하나도 안 떨어짐
                    que.appendleft(((nc1_x, nc1_y),
                                    (nc2_x, nc2_y),
                                    (nx, ny)))
                elif drop_coin == 1:
                    # 하나만 떨어짐
                    return idx + 1
    return -1


print(soluation())
