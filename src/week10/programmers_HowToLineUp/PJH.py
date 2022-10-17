import math
def solution(n, k):
    answer = []
    numbers = [i for i in range(1, n+1)]
    for i in range(n-1,-1,-1):
        tmp = math.factorial(i)
        idx = k//tmp
        k = k%tmp
        if k == 0:
            answer.append(numbers.pop(idx-1))
        else :
             answer.append(numbers.pop(idx))

    
    return answer