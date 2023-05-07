import sys
from collections import deque

input = sys.stdin.readline

# 음수는 맨앞에서 만 나온다

numbers = deque()
operators = deque()
temp_op = ["*", "+", "-", "/"]
n = list(map(str, input().rstrip()))

op_high = ["*", "/"]
op_low = ["+", "-"]

temp=""
start=0

if n[0] == "-":
    temp = "-"
    start=1
else:
    temp = ""
for l in range(start, len(n)):
    if n[l] == "0":
        temp += n[l]
        continue
    if n[l] in temp_op:
        operators.append(n[l])
        if len(temp) > 0:
            numbers.append((int)(temp))
        temp = ""
    else:
        temp += n[l]
if len(temp) > 0:
    numbers.append((int)(temp))


def cal(n1, n2, op):
    if op == "*":
        return n1 * n2
    elif op == "-":
        return n1 - n2
    elif op == "/":
        if n1 * n2 < 0 and n1 % n2 != 0:
            return (n1 // n2) + 1
        return n1 // n2
    elif op == "+":
        return n1 + n2


def other(a1, a2):
    t1 = numbers.popleft()
    t2 = numbers.popleft()
    t3 = numbers.pop()
    if len(numbers) == 0:
        n1 = cal(t1, t2, a1)
        n2 = cal(t2, t3, a2)
        if n1 >= n2:
            operators.append(a2)
            numbers.append(t3)
            numbers.appendleft(n1)
        else:
            operators.appendleft(a1)
            numbers.appendleft(t1)
            numbers.append(n2)
    else:
        t4 = numbers.pop()
        n1 = cal(t1, t2, a1)
        n2 = cal(t4, t3, a2)
        if n1 >= n2:
            operators.append(a2)
            numbers.append(t4)
            numbers.append(t3)
            numbers.appendleft(n1)
        else:
            operators.appendleft(a1)
            numbers.appendleft(t2)
            numbers.appendleft(t1)
            numbers.append(n2)


while operators:

    a1 = operators.popleft()
    if len(operators) == 0:
        next = cal(numbers.popleft(), numbers.popleft(), a1)
        numbers.appendleft(next)
        continue
    a2 = operators.pop()
    next = 0
    if a1 in op_high and a2 in op_high:
        # 둘다 계산을 해서 누가 더 높은지 파악 하면됨
        other(a1, a2)

    elif a1 in op_high and a2 not in op_high:
        operators.append(a2)
        next = cal(numbers.popleft(), numbers.popleft(), a1)
        numbers.appendleft(next)

    elif a1 not in op_high and a2 in op_high:
        operators.appendleft(a1)
        t1=numbers.pop()
        t2=numbers.pop()
        next = cal(t2, t1, a2)
        numbers.append(next)
    else:
        # 둘다 +,- 인 경우
        other(a1, a2)

print((int)(numbers.popleft()))