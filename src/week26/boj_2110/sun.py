import sys
sys.stdin = open('../input.txt')

if __name__=="__main__":
    N, C = map(int, sys.stdin.readline().split())
    arr = []
    result = 0

    for _ in range(N):
        arr.append(int(sys.stdin.readline()))
    arr.sort()
    start, end = 1, arr[-1]-arr[0]

    while start <= end:
        mid = (start + end) // 2

        count = 1
        current = arr[0]

        for i in range(1, N):
            if arr[i] >= current + mid:
                current = arr[i]
                count += 1

        if count >= C:
            result = mid
            start = mid + 1
        else:
            end = mid - 1
    print(result)


