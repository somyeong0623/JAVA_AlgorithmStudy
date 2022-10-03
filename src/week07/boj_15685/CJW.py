from sys import stdin

dragon_cove = dict()
r = [1, 0]
d = [0, 1]
l = [-1, 0]
u = [0, -1]

# 우 하 좌 상
dragon_cove[0] = [
    [r],
    [d],
    [l],
    [u]
]

for i in range(1, 11):
    tmp = []
    for j in range(4):
        tmp1 = []
        for k in dragon_cove[i - 1][j]:
            tmp1.append(k)
        tmp2 = dragon_cove[i - 1][j - 1][:]
        tmp2.reverse()
        for k in tmp2:
            tmp1.append(k)
        tmp.append(tmp1[:])
    dragon_cove[i] = tmp[:]

# dragon_cove[1] = [
#     (r, u),
#     (d, r),
#     (l, d),
#     (u, l)
# ]
#
# dragon_cove[2] = [
#     (r, u, l, u),
#     (d, r, u, r),
#     (l, d, r, d),
#     (u, l, d, l)
# ]
#
# dragon_cove[3] = [
#     (r, u, l, u, l, d, l, u),
#     (d, r, u, r, u, l, u, r),
#     (l, d, r, d, r, u, r, d),
#     (u, l, d, l, d, r, d, l)
# ]


n = int(stdin.readline())
visited = set()

visit = [[False] * 102 for i in range(102)]
for i in range(n):
    px, py, d, g = list(map(int, stdin.readline().split()))
    pos = (px, py)

    visit[py][px] = True

    visited.add(pos)

    if d == 1:
        d = 3
    elif d == 3:
        d = 1

    for x, y in dragon_cove[g][d]:
        px += x
        py += y
        if 0 <= px <= 101 and 0 <= py <= 101:
            visit[py][px] = True
            visited.add((px, py))
        else:
            break
count = 0
for x, y in visited:
    if 0 <= y + 1 <= 101 and 0 <= x + 1 <= 101:
        if visit[y][x] == visit[y][x + 1] == visit[y + 1][x] == visit[y + 1][x + 1]:
            count += 1

print(count)