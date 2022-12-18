import sys
from collections import deque
input=sys.stdin.readline

e=list(input().rstrip())
test=input().rstrip()

last=test[-1]
leng=len(test)
stack=[]
for i in e:
    stack.append(i)
    if i==last and len(stack)>=leng:
        la=len(stack)
        tt=0
        check=0
        if la-leng>=0:
            ah="".join(stack[la-leng:])
            if ah==test:
                for k in range(len(test)):
                    stack.pop()

if stack:
    print("".join(stack))
else:
    print("FRULA")