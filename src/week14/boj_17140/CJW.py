from sys import stdin

r, c, k = list(map(int, stdin.readline().split()))
r -= 1
c -= 1
arr = [list(map(int, stdin.readline().split())) for _ in range(3)]


def mySort(arr: list):
    data = dict()

    for item in arr:
        if item not in data:
            data[item] = 0
        data[item] += 1
    data = list(zip(data.keys(), data.values()))
    data.sort(key=lambda x: (x[1], x[0]))
    return sum(data, ())[:100]


result = 0
while True:
    try:
        if arr[r][c] == k:
            break
        if result > 100:
            result = -1
            break
    except IndexError as e:
        pass

    result += 1
    if len(arr) >= len(arr[0]):
        # 행 계산
        temp = []
        for i in range(len(arr)):
            temp.append(list(mySort([i for i in arr[i] if i != 0])))

        value = max([len(i) for i in temp])
        for i in range(len(arr)):
            for di in range(value - len(temp[i])):
                temp[i].append(0)
        arr = temp

    else:
        # 열 계산
        temp = []
        for i in range(len(arr[0])):
            temp.append(list(mySort([i for i in list(zip(*arr))[i] if i != 0])))
        value = max([len(i) for i in temp])
        for i in range(len(arr[0])):
            for di in range(value - len(temp[i])):
                temp[i].append(0)
        arr = list(map(list, zip(*temp)))
print(result)
