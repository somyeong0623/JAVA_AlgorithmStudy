from sys import stdin
from collections import deque

target = int(stdin.readline().strip())

que = deque()

# num, clip, sec
que.append((1, 0, 0))
visited = [[False for _ in range(2001)] for _ in range(2001)]
visited[1][0] = True

while que:
    origin = que.popleft()
    if origin[0] == target:
        print(origin[2])
        break

    cur_num, cur_clip, cur_sec = origin
    # 화면에 있는 이모티콘을 복사
    new_num, new_clip = cur_num, cur_num
    if not visited[new_num][new_clip]:
        visited[new_num][new_clip] = True
        que.append((new_num, new_clip, cur_sec + 1))

    cur_num, cur_clip, cur_sec = origin
    # 붙여넣기
    new_num, new_clip = cur_num + cur_clip, cur_clip
    if not (new_num >= 1001 or new_num < 0 or new_clip >= 1001 or new_clip < 0 or visited[new_num][new_clip]):
        visited[new_num][new_clip] = True
        que.append((new_num, new_clip, cur_sec + 1))

    cur_num, cur_clip, cur_sec = origin
    # 삭제
    new_num, new_clip = cur_num - 1, cur_clip
    if not (new_num >= 1001 or new_num < 0 or new_clip >= 1001 or new_clip < 0 or visited[new_num][new_clip]):
        visited[new_num][new_clip] = True
        que.append((new_num, new_clip, cur_sec + 1))
