from sys import stdin

N, K, Q, M = list(map(int, stdin.readline().split()))

visited = [False for _ in range(N + 3)]
isSleep = [False for _ in range(N + 3)]

k_list = list(map(int, stdin.readline().split()))
for item in k_list:
    isSleep[item] = True

q_list = list(map(int, stdin.readline().split()))
result = []
for i in range(M):
    S, E = list(map(int, stdin.readline().split()))
    result.append((S, E))

for item in q_list:
    # 만약 잠을 자고 있다면 넘어감
    if isSleep[item]:
        continue

    idx = 1
    while item * idx <= N + 2:
        if isSleep[item * idx]:
            idx += 1
            continue
        visited[item * idx] = True
        idx += 1

visited.pop(0)
dp = [0]
for i in visited:
    dp.append(dp[-1] + (0 if i else 1))

for S, E in result:
    print(dp[E] - dp[S - 1])
