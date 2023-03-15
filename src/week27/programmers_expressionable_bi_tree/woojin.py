import sys
input = sys.stdin.readline


def search(number):
    length = len(number)

    if length == 1:
        if '0' in number:
            return "0"
        else:
            return "1"

    mid = length // 2

    left = search(number[:mid])
    right = search(number[mid + 1:])

    if left==False or right==False:
        return False
    if number[mid]=="0" and left=="1":
        return False

    if number[mid]=="0"and right=="1":
        return False
    if left=="0"and right=="0" and number[mid]=="0":
        return "0"

    return "1"



def solution(numbers):
    answer = []
    perfect = [2 ** x - 1 for x in range(50)]

    for l in numbers:
        now = bin(l)[2:]
        now_len = len(now)
        for k in perfect:
            if now_len <= k:
                now = '0' * (k - now_len) + now
                break
        next = search(now)
        if next == "1":
            answer.append(1)
        else:
            answer.append(0)

    return answer