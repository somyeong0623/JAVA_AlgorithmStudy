import sys
sys.setrecursionlimit(300000)
import re
from collections import deque
from heapq import heappush, heappop
sys.stdin = open('../input.txt')

def cal(num1, num2, exp):
    if exp == '+':
        return num1 + num2
    elif exp == '-':
        return num1 - num2
    elif exp == '*':
        return num1 * num2
    elif exp == '/':
        if num1 * num2 < 0 and num1 % num2 !=0:
            return num1 // num2 + 1
        return num1 // num2
    return 0

if __name__=="__main__":
    S = sys.stdin.readline()
    dic = {'+' : 0, '-' : 0, '*' : 1, '/' : 1}
    if S[0] == '-':
        num = re.split('[-|+|*|/]', S[1:])
    else:
        num = re.split('[-|+|*|/]', S)

    num = list(map(int, num))
    exp = deque()
    for i in range(1, len(S)):
        if i == 1 and S[i-1] == '-':
            num[0] = -num[0]
        if S[i] == '-' or S[i] == '+' or S[i] == '*' or S[i] == '/':
            exp.append(S[i])

    s = 0
    e = len(num) - 1

    while exp:
        if dic[exp[0]] > dic[exp[-1]]:
            num[s+1] = cal(num[s], num[s+1], exp.popleft())
            s += 1
        elif dic[exp[0]] == dic[exp[-1]]:
            if cal(num[s], num[s+1], exp[0]) >= cal(num[e-1], num[e], exp[-1]):
                num[s + 1] = cal(num[s], num[s + 1], exp.popleft())
                s += 1
            else:
                num[e - 1] = cal(num[e - 1], num[e], exp.pop())
                e -= 1
        else:
            num[e-1] = cal(num[e-1], num[e], exp.pop())
            e -= 1

    print(num[e])