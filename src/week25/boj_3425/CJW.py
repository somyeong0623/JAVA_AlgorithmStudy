from sys import stdin
from collections import deque

MAX = int(10e8) + 1

cmd_list = []

cmd_promise = [["NUM"], ["POP", "INV", "DUP"], ["SWP", "ADD", "SUB", "MUL", "DIV", "MOD"]]


def div(a, b):
    count = 0
    count += 1 if a < 0 else 0
    count += 1 if b < 0 else 0
    answer = abs(a) // abs(b)
    return -answer if count == 1 else answer


def mod(a, b):
    count = 0
    count += 1 if a < 0 else 0
    answer = abs(a) % abs(b)
    return -answer if count == 1 else answer


def find_command_idx(cmd):
    for idx, val in enumerate(cmd_promise):
        if val.count(cmd) > 0:
            return idx


def shell(cmd, args, stack):
    if cmd == "NUM":
        stack.append(args)
    elif cmd == "POP":
        stack.pop()
    elif cmd == "INV":
        stack[-1] = -stack[-1]
    elif cmd == "DUP":
        stack.append(stack[-1])
    elif cmd == "SWP":
        stack[-2], stack[-1] = stack[-1], stack[-2]
    elif cmd == "ADD":
        a = stack.pop()
        b = stack.pop()
        result = a + b
        if abs(result) >= MAX:
            return False
        stack.append(result)
    elif cmd == "SUB":
        a = stack.pop()
        b = stack.pop()
        result = b - a
        if abs(result) >= MAX:
            return False
        stack.append(result)
    elif cmd == "MUL":
        a = stack.pop()
        b = stack.pop()
        result = a * b
        if abs(result) >= MAX:
            return False
        stack.append(result)
    elif cmd == "DIV":
        a = stack.pop()
        b = stack.pop()
        if a == 0:
            return False
        result = div(b, a)
        if abs(result) >= MAX:
            return False
        stack.append(result)
    elif cmd == "MOD":
        a = stack.pop()
        b = stack.pop()
        if a == 0:
            return False
        result = mod(b, a)
        if abs(result) >= MAX:
            return False
        stack.append(result)

    return True


while True:
    command = stdin.readline().strip()
    if command == "QUIT":
        break

    if command == "":
        continue

    if command == "END":
        stack = deque()
        N = int(stdin.readline().strip())

        # 데이터 받음
        for val in range(N):
            stack.clear()
            stack.append(int(stdin.readline().strip()))
            answer = 0

            for cmds in cmd_list:
                if answer == "ERROR":
                    continue

                cmd = cmds[0]
                args = None

                if cmd == "NUM":
                    args = int(cmds[1])

                need_args = find_command_idx(cmd)
                # 필요한 인자 개수 체크
                if len(stack) < need_args:
                    answer = "ERROR"

                if not shell(cmd, args, stack):
                    answer = "ERROR"

            print("ERROR" if answer == "ERROR" or len(stack) > 1 else stack[0])
        cmd_list.clear()
        print()
    else:
        cmd_list.append(command.split())
