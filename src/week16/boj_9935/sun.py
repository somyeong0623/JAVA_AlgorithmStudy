import sys
sys.stdin = open('../input.txt')

if __name__=="__main__":
    S = sys.stdin.readline().strip()
    bomb = list(sys.stdin.readline().strip())
    bombSize = len(bomb)
    stack = []

    for char in S:
        stack.append(char)
        if (len(stack) >= len(bomb)):
            if (bomb == stack[-bombSize:]):
                for _ in range(bombSize):
                    stack.pop()
    if len(stack) > 0:
        print(''.join(s for s in stack))
    else:
        print("FRULA")