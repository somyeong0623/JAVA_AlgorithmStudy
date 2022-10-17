
def binary(left, right, times, n):
    while left <= right:
        mid = (left + right) //2
        cnt = 0
        for i in times:
            cnt += mid//i
        if cnt < n:
            left = mid + 1
        else :
            right = mid - 1

    return left


def solution(n, times):
    times.sort()
    left = 0
    right = times[-1]*n
    answer = binary(left, right, times, n)
    return answer










































