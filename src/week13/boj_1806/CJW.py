from sys import stdin

n, target = list(map(int, stdin.readline().split()))

arr = list(map(int, stdin.readline().split(" ")))
s = 0
e = 0
total = arr[s]
result = int(10e9)
while s < len(arr):
    if total >= target:
        result = min(result, e - s + 1)
        if s + 1 >= len(arr):
            break
        total -= arr[s]
        s += 1
    elif total < target:
        if e + 1 >= len(arr):
            total -= arr[s]
            s += 1
            continue
        e += 1
        total += arr[e]

print(0 if result == int(10e9) else result)