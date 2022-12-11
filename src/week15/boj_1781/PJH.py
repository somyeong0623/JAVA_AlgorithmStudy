# 1781 컵라면문제

import heapq
import sys
read = sys.stdin.readline
TC = int(read())
input_list = []
select_value = []
for i in range(TC):
    input_list.append(list(map(int,read().split())))
input_list.sort(key=lambda x:(x[0]))

for timer, cnt in input_list:
    heapq.heappush(select_value,cnt)
    if len(select_value)>timer:
        heapq.heappop(select_value)
print(sum(select_value))

#시간초과 
# import sys
# read = sys.stdin.readline

# TC = int(read())
# input_list = []
# check = [True] * TC
# answer = 0
# timer = 0
# for i in range(TC):
#     target = list(map(int,read().split()))
#     target[0] -= 1 #1부터시작 -> 인덱스 0으로 맞추기
#     input_list.append(target)
# input_list.sort(key=lambda x:(-x[1],x[0]))

# for problem in input_list:
#     #print(problem)
#     for i in range(problem[0],-1,-1):
#         if check[i]:
#             check[i]=False
#             answer+=problem[1]
#             break
# print(answer)