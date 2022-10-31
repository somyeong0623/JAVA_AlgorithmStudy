from sys import stdin

N = int(stdin.readline())
arr = list(map(int, stdin.readline().split()))

arr.sort()
s, e = 0, len(arr) - 1
answer = (0, 0, 10e9)
while s < e:
    var = arr[s] + arr[e]
    if abs(answer[2]) > abs(var):
        answer = (arr[s], arr[e], var)
    if var == 0:
        answer = (arr[s], arr[e], var)
        break
    elif var > 0:
        e -= 1
    elif var < 0:
        s += 1
print(answer[0], answer[1])