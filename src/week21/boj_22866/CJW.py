from sys import stdin

N = int(stdin.readline())
arr = list(map(int, stdin.readline().split()))

stack = []
label = [0] * (N + 1)

# around[a][0] => a에서 가장 가까운 건물 번호
# around[a][1] => a에서 가장 가까운 건물의 거리
around = [[int(10e9), int(10e9)] for _ in range(N + 1)]

# 뒤를 기준으로 가까운 번호를 저장
for idx, val in enumerate(arr):
    # 가장 가까운 건물의 거리보다 크면 볼 수 없으니까 빼버림
    while len(stack) > 0 and stack[-1][1] <= val:
        stack.pop()
    label[idx + 1] += len(stack)

    if len(stack) > 0:
        # 거리를 계산함
        temp = abs(stack[-1][0] - (idx + 1))

        # 볼 수 있는 최대 값을 갱신함
        if temp < around[idx + 1][1]:
            around[idx + 1][0] = stack[-1][0]
            around[idx + 1][1] = temp
        elif temp == around[idx + 1][1] and stack[-1][0] < around[idx + 1][0]:
            around[idx + 1][0] = stack[-1][0]
    stack.append([idx + 1, val])

stack = []
# 앞을 기준으로 가까운 거리를 저장
arr.reverse()
for idx, val in enumerate(arr):
    idx = len(arr) - idx - 1
    while len(stack) > 0 and stack[-1][1] <= val:
        stack.pop()
    # 뒤 + 앞이므로 += 을 이용함.
    label[idx + 1] += len(stack)

    if len(stack) > 0:
        temp = abs(stack[-1][0] - (idx + 1))
        if temp < around[idx + 1][1]:
            around[idx + 1][0] = stack[-1][0]
            around[idx + 1][1] = temp
        elif temp == around[idx + 1][1] and stack[-1][0] < around[idx + 1][0]:
            around[idx + 1][0] = stack[-1][0]
    stack.append([idx + 1, val])

for i in range(1, N + 1):
    if label[i] > 0:
        print(str(label[i]) + ' ' + str(around[i][0]))
    else:
        print(0)
