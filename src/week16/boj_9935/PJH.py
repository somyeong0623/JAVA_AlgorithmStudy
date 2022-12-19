import sys
input = sys.stdin.readline

def check(arr,boom):
    for i,j in zip(arr,boom):
        if(i!=j):
            return False
    return True

target = input().strip()
boom = input().strip()

stack = []
for i in target:
    stack.append(i)
    if(len(stack)>=len(boom) and check(stack[-len(boom):],boom)):
        del stack[-len(boom):]

# 시간초과
# while(target.find(boom)!=-1):
#     target = "".join(target.split(boom))

if(len(stack)!=0):
    print("".join(stack))
else:
    print("FRULA")