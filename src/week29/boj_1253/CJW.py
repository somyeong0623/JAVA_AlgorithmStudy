from sys import stdin

N = int(stdin.readline().strip())
arr = list(map(int, stdin.readline().strip().split()))
arr.sort()


def two_pointer(index):
    if index == 0:
        start = 1
    else:
        start = 0
    if index == len(arr) - 1:
        end = len(arr) - 2
    else:
        end = len(arr) - 1

    target = arr[index]
    while start < end:
        result = arr[start] + arr[end]

        if target == result:
            return True
        elif target < result:
            if end - 1 == index:
                end -= 2
            else:
                end -= 1
        else:
            if start + 1 == index:
                start += 2
            else:
                start += 1

    return False


answer = 0
for idx, _ in enumerate(arr):
    if two_pointer(idx):
        answer += 1

print(answer)
