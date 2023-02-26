from sys import stdin


def getTurn():
    class Character:
        def __init__(self, num, dir):
            self.num = num
            self.dir = dir
            self.dirs_ko = ["우", "좌", "상", "하"]

        def __repr__(self):
            return str(self.num) + " " + self.dirs_ko[self.dir]

        def __eq__(self, num):
            return self.num == num

    def reverse_dir(dir):
        if dir == 0:  # 우
            return 1
        elif dir == 1:  # 좌
            return 0
        elif dir == 2:  # 상
            return 3
        elif dir == 3:  # 하
            return 2

    def goWhite():
        # 흰색
        # 존재 하면 가장 위에 올려둠
        # 이동 시 기존에 있던 것들도 이동함
        for temp_char in temp:
            character[temp_char.num] = (n_x, n_y)
        map_info[n_y][n_x].extend(temp)

    def goRed():
        # 빨간색
        # 모든 말의 쌓여있는 순서를 반대로 바꿈
        # 이미 존재하면 순서를 바꾼 후 넣어줌
        temp.reverse()
        for temp_char in temp:
            character[temp_char.num] = (n_x, n_y)
        map_info[n_y][n_x].extend(temp)

    def goBlue():
        # 파란색
        # 이동방향을 반대로하고 한칸을 이동함,
        # 다만 이동 하려는 칸이 파란색이면 방향만 바꿔줌
        char.dir = reverse_dir(char.dir)
        dx, dy = dirs[char.dir]
        n_x, n_y = p_x + dx, p_y + dy
        if n_x < 0 or n_x >= N or n_y < 0 or n_y >= N:
            map_info[p_y][p_x].extend(temp)
        else:
            if world[n_y][n_x] == 2:
                map_info[p_y][p_x].extend(temp)
            elif world[n_y][n_x] == 1:
                temp.reverse()
                for temp_char in temp:
                    character[temp_char.num] = (n_x, n_y)
                map_info[n_y][n_x].extend(temp)
            elif world[n_y][n_x] == 0:
                for temp_char in temp:
                    character[temp_char.num] = (n_x, n_y)
                map_info[n_y][n_x].extend(temp)

    # (x, y)
    # 우 좌 상 하

    dirs = ((1, 0), (-1, 0), (0, -1), (0, 1))

    N, K = list(map(int, stdin.readline().strip().split()))
    world = [list(map(int, stdin.readline().strip().split())) for _ in range(N)]

    map_info = [[[] for _ in range(N)] for _ in range(N)]

    character = dict()
    for num in range(1, K + 1):
        y, x, d = list(map(int, stdin.readline().strip().split()))
        y -= 1
        x -= 1
        d -= 1

        character[num] = (x, y)
        map_info[y][x].append(Character(num, d))

    turn = 1

    while turn <= 1000:
        for i in range(1, K + 1):
            # 현재 좌표를 가져옴
            p_x, p_y = character[i]
            char = map_info[p_y][p_x][0]

            if len(map_info[p_y][p_x]) >= 4:
                return turn

            if char == i:
                # 해당 말이 아래에 있으면
                temp = map_info[p_y][p_x][:]
                map_info[p_y][p_x].clear()

                # 위치 잡아줌
                dx, dy = dirs[char.dir]
                n_x, n_y = p_x + dx, p_y + dy

                # 맵 밖으로 나가면 방향을 바꿔줌
                if n_x < 0 or n_x >= N or n_y < 0 or n_y >= N:
                    goBlue()
                    continue

                if world[n_y][n_x] == 0:
                    goWhite()
                elif world[n_y][n_x] == 1:
                    goRed()
                elif world[n_y][n_x] == 2:
                    goBlue()

                if len(map_info[n_y][n_x]) >= 4:
                    return turn

        turn += 1
    return -1


print(getTurn())
