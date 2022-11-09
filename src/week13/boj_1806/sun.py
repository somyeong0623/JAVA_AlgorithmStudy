import sys

if __name__ == "__main__":
    N, S = map(int, sys.stdin.readline().split())
    numbers = list(map(int, sys.stdin.readline().split()))
    left = 0
    right = 0
    answer = 2147000000
    sumNum = 0
    sumNum += numbers[left]
    while (left<len(numbers)):
        if (sumNum >= S):
            answer = min(answer, right - left + 1)
            sumNum -= numbers[left]
            left += 1
        else:
            if (right < len(numbers)-1):
                right += 1
                sumNum += numbers[right]
            else:
                break
    if (answer == 2147000000):
        print(0)
    else:
        print(answer)