from sys import stdin

arr1 = stdin.readline().strip()
arr2 = stdin.readline().strip()

last = arr2[-1]

stack = []

for word in arr1:
    stack.append(word)
    if word == last and ''.join(stack[-len(arr2):]) == arr2:
        del stack[-len(arr2):]

print(''.join(stack) if len(stack) != 0 else "FRULA")

# 시간 초과
# from sys import stdin
#
# arr1 = stdin.readline().strip()
# arr2 = stdin.readline().strip()
#
# now = len(arr1)
# while True:
#     arr1 = arr1.replace(arr2, '')
#     if now == len(arr1):
#         break
#     now = len(arr1)
#
# print(arr1 if arr1 != '' else "FRULA")
