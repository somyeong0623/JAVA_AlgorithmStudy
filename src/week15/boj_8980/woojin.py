import sys
input=sys.stdin.readline


n,c=map(int,input().split())
e=int(input().rstrip())

total=[list(map(int,input().split())) for _ in range(e)]

#정렬하는 마을과 받는 마을과
total=sorted(total,key=lambda x:(x[1],x[0]))


#문제 풀이
#1.dp?
#2.그래프?
#.그리디?

#그리디 정한 이유

#문제 풀이 어찌할지?
#1.정렬 기준?
#2.각 마을에 담는거 어찌할지?


#https://steady-coding.tistory.com/58
#잘못된 풀이
#거리 순 정렬
#뒤부터 문제 풀이
#

answer=0

#각 마을에서 출발하며 받는 마을 전까지 얼만큼의 택배를 가져 가야 할지를 담을 배열
city=[0 for _ in range(n)]

for i in total:
    send, get, size = i  # a1:보내는 마을, a2: 받는 마을

    # 인덱스 조정하는 과정(city배열과 동일시 하기 위해서)
    send -= 1
    get -= 1

    # city배열에서 보내는 마을 부터 받는 마을전 까지의 인덱스 에 해당되는 값들을
    # 탐색을 하며 가지고 있는 최댓값을 파악을 하여 현재 뽑힌 size에서 얼만큼을
    # 들고 갈수 있는지를 파악하고자 한다
    temp_size = 0  # 경로상에서 최댓값 파악하기 위한 변수
    for l in range(send, get):
        temp_size = max(temp_size, city[l])

    # 즉, 경로상에서 가진 최댓값이 배송 가능한 최대 박스 수를 넘을시엔
    # 나는 담고 싶어도 담을수 없으니 다음 순서에 해당 되는 얘로 넘어감
    if (temp_size >= c):
        continue

    # 담을수 있는 만큼을 계산하는 변수
    temp_cal = c - temp_size
    # 계산한 값에 따라 얼만큼 담는지 계산
    if temp_cal <= size:
        size = temp_cal

    answer += size
    for l in range(send, get):
        city[l] += size

print(answer)