from collections import deque


def solution(numbers):
    limit_length = [1]
    between_length = [1]
    idx = 1
    while limit_length[-1] < int(10e15):
        limit_length.append(limit_length[-1] + (2 ** idx))
        between_length.append(2 ** idx)

        idx += 1
    answer = []

    idx = 0
    for num in numbers:
        arr = deque(map(int, str(bin(num))[2:]))

        while len(arr) > limit_length[idx]:
            idx += 1

        padding = limit_length[idx] - len(arr)
        for i in range(padding):
            arr.appendleft(0)
        is_break = False
        for depth in range(idx, 0, -1):
            down = between_length[depth - 1]
            between = between_length[depth + 1]
            for step in range(limit_length[depth - 1], len(arr), between):
                if arr[step] == 0 and (arr[step - down] == 1 or arr[step + down] == 1):
                    is_break = True
                    break
            if is_break:
                break

        if not is_break:
            answer.append(1)
        else:
            answer.append(0)
        # print(arr)
    return answer