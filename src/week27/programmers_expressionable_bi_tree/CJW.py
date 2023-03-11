from collections import deque


def solution(numbers):
    # 각 깊이 당 시작 위치
    limit_length = [1]
    # 노드 간 간격
    between_length = [1]

    # 사전 데이터 처리
    idx = 1
    while limit_length[-1] < int(10e15):
        limit_length.append(limit_length[-1] + (2 ** idx))
        between_length.append(2 ** idx)

        idx += 1
    answer = []

    idx = 0
    for num in numbers:
        # 2 진수로 변환
        arr = deque(map(int, str(bin(num))[2:]))

        # 최대 높이를 계산
        while len(arr) > limit_length[idx]:
            idx += 1

        # 포화 이진트리로 바꿈
        padding = limit_length[idx] - len(arr)
        for i in range(padding):
            arr.appendleft(0)

        is_break = False
        # 맨 위 깊이부터 탐색
        for depth in range(idx, 0, -1):
            # 자식 노드의 index 계산
            down = between_length[depth - 1]
            # 같은 레벨의 다음 노드 계산
            between = between_length[depth + 1]
            # 체크 할 부모 노드 index
            for step in range(limit_length[depth - 1], len(arr), between):
                # 부모 노드가 0인데 자식 노드가 둘 중 하나라도 1이면
                if arr[step] == 0 and (arr[step - down] == 1 or arr[step + down] == 1):
                    is_break = True
                    break
            if is_break:
                break

        if not is_break:
            answer.append(1)
        else:
            answer.append(0)
        # print(arr)
    return answer