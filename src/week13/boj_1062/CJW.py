# 0 2 8 13 19

from sys import stdin

answer = 0


def comb(depth, start, key):
    global answer
    if depth == k:
        temp = 0
        for items in arr:
            for item in items:
                # 키가 포함되어있지 않으면 break
                if (key >> item) & 1 == 0:
                    break
            else:
                # 모든 데이터가 있으면 증가
                temp += 1
        answer = max(answer, temp)
        return

    for i in range(start, len(inputs)):
        key |= (1 << inputs[i])
        comb(depth + 1, i + 1, key)
        key = key & ~(1 << inputs[i])


exists = [0, 2, 8, 13, 19]
key = 0
for i in exists:
    key |= (1 << i)

n, k = list(map(int, stdin.readline().split()))
arr = [[ord(w) - 97 for w in list(stdin.readline().strip())][4:][:-4] for _ in range(n)]

if k < 5:
    # 5보다 작으면 출력이 안됨
    print(0)
elif k >= 26:
    # 주어진게 문자열 길이보다 길다면
    # 모든 것을 출력 가능
    print(len(arr))
else:
    inputs = [i for i in range(26) if i not in exists]
    k -= 5
    comb(0, 0, key)
    print(answer)