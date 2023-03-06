import sys
from collections import deque
input = sys.stdin.readline
def check(x, y, zx, zy, cross, time):
    # 우선은 현재 지나갈수 있는 시간대인지 아닌지를 파악을 해야함
    # 지금 현재 시간에서 교차로의 위치가 변경이 되어야 한다면 변환해 주어야 함
    a1, a2, a3, a4 = cross  # 0 - 동서 남북
    now_dir = a2
    now_time=-1
    if now_dir=="-":
        now_time = (int)(a3)
    else:
        now_time = (int)(a4)
        
    while now_time < time:
        op = ""
        if now_dir == "|":
            op = "-"
        else:
            op = "|"
        if op =="|":
            now_dir = op
            now_time = now_time + (int)(a4)
        else:
            now_dir = op
            now_time = now_time + (int)(a3)

    # 이제 현재 시간대에 맞춰져서 나오게 됨
    # 현재 시간대에서 가지 못했다면... 다음 시간대에 갈수 있겠지
    if now_time >= time:
        if now_dir == "-":
            if x == zx and y == zy - 1:
                return time
            if x == zx and y == zy + 1:
                return time
        else:
            if x == zx - 1 and y == zy:
                return time
            if x == zx + 1 and y == zy:
                return time
    # 이렇게 해서 어떻게 해서든 넘어 갈수는 있겠구나
    return now_time + 1


def game(x, y, graph, next, n, m):
    visit = [[-1] * (m) for _ in range(n)]
    # 걸리는 최소 시간을 파악 하고자 한다.
    # 이를 찾기 위해서 방법을 파악 해보도록 하자
    # 교차로를 관리하는 얘들을 따로 만들어야 할듯
    q = deque()
    visit[x][y] = 0
    q.append((0, x, y))
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]
    last = sys.maxsize
    while q:
        a1, a2, a3 = q.popleft()
        for l in range(4):
            zx = a2 + dx[l]
            zy = a3 + dy[l]
            if 0 <= zx < n and 0 <= zy < m:
                if graph[zx][zy] != ".":
                    temp_time = a1 + 1

                    if graph[zx][zy] == "B":
                        last = min(temp_time, last)
                    else:
                        if graph[zx][zy] not in ("A", "B", "#"):
                            check_now = next[(int)(graph[zx][zy])]
                            uu = check(a2, a3, zx, zy, check_now, a1 + 1)
                            temp_time = uu

                        # 그냥 지나갈수 있는것은 그냥 지나가도록 한다.
                        if visit[zx][zy] == -1:
                            visit[zx][zy] = temp_time
                            q.append((temp_time, zx, zy))

                        elif temp_time < visit[zx][zy]:
                            visit[zx][zy] = temp_time
                            q.append((temp_time, zx, zy))
    if last == sys.maxsize:
        print("impossible")
    else:
        print(last)

while True:
    m, n = map(int, input().split())
    if m == 0 and n == 0:
        break

    graph = []
    check_temp = -1
    do = ["#", ".", "A", "B"]
    startx = -1
    starty = -1
    for i in range(m):
        temp = list(map(str, input().rstrip()))
        graph.append(temp)
        j = 0
        for l in temp:
            if l not in do:
                check_temp = max(check_temp, (int)(l))
            if l == "A":
                startx = i
                starty = j
            j += 1
    next = []
    if check_temp >= 0:
        for l in range(check_temp + 1):
            ee = list(map(str, input().split()))
            next.append(ee)
        input().split()

    else:
        input().split()

    # 이제 게임을 시작 해보도록 하면 된다
    time = 0
    game(startx, starty, graph, next, m, n)