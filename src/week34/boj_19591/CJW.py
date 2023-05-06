import re
from sys import stdin
from collections import deque


def calc(a, oper, b):
    if oper == '/':
        count = 0
        count += 1 if '-' in a else 0
        count += 1 if '-' in b else 0
        if count == 1:
            return -1 * (abs(int(a)) // abs(int(b)))
        else:
            return int(a) // int(b)
    else:
        value = 0
        if oper == '+':
            value = int(a) + int(b)
        elif oper == '-':
            value = int(a) - int(b)
        elif oper == '*':
            value = int(a) * int(b)
        return value


def remove_front():
    for _ in range(3):
        calc_list.popleft()


def remove_back():
    for _ in range(3):
        calc_list.pop()


str1 = stdin.readline().strip()

# 숫자와 연산자를 추출하는 정규 표현식
pattern = r'(\d+|[+\-*/])'

calc_list = deque(re.findall(pattern, str1))

# 맨 앞에 -가 오는 경우 전처리
if calc_list[0] == '-' or calc_list[0] == '*':
    a = calc_list.popleft()
    b = calc_list.popleft()
    calc_list.appendleft(a + b)

try:
    # 조건 1 앞, 뒤 분리
    front = (calc_list[0], calc_list[1], calc_list[2])
    back = (calc_list[-3], calc_list[-2], calc_list[-1])
    value1 = calc(*front)
    value2 = calc(*back)

    while len(calc_list) > 3:
        # 연산 순위 검색
        if (calc_list[1] == '*' or calc_list[1] == '/') and \
                (calc_list[-2] == '+' or calc_list[-2] == '-'):
            # 조건 2 (앞에 부터 계산)
            remove_front()
            calc_list.appendleft(str(calc(*front)))
            front = (calc_list[0], calc_list[1], calc_list[2])
            value1 = calc(*front)
        elif (calc_list[1] == '+' or calc_list[1] == '-') and \
                (calc_list[-2] == '*' or calc_list[-2] == '/'):
            # 조건 2 (뒤에 부터 계산)
            remove_back()
            calc_list.append(str(calc(*back)))
            back = (calc_list[-3], calc_list[-2], calc_list[-1])
            value2 = calc(*back)
        else:
            # 연산 순위가 같으면 값 비교
            if value1 > value2:
                # 조건 3 앞에꺼 계산
                remove_front()
                calc_list.appendleft(str(calc(*front)))
                front = (calc_list[0], calc_list[1], calc_list[2])
                value1 = calc(*front)
            elif value1 < value2:
                # 조건 3 뒤에꺼 계산
                remove_back()
                calc_list.append(str(calc(*back)))
                back = (calc_list[-3], calc_list[-2], calc_list[-1])
                value2 = calc(*back)
            else:
                # 조건 4 값이 같다면 앞에거 계산
                remove_front()
                calc_list.appendleft(str(calc(*front)))
                front = (calc_list[0], calc_list[1], calc_list[2])
                value1 = calc(*front)
except IndexError:
    pass

if len(calc_list) == 1:
    print(int(calc_list[0]))
else:
    print(int(calc(*calc_list)))
