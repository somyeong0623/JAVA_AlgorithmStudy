import heapq


def solution(jobs):
    answer = 0
    fin = len(jobs)
    jobs = sorted(jobs, key=lambda x: (x[0], x[1]))
    a1, a2 = jobs[0]

    jobs.pop(0)

    heap = []

    heapq.heappush(heap, (a2, a1, 0))  # 소요시간 #시작시간 #입력 들어온 시간
    now = a1
    change = 0
    while (1):
        if (len(heap) == 0):
            break
        t = heapq.heappop(heap)
        a1, a2, a3 = t  # 걸릴시간 #입력들어온 시간 #시작 시간

        if (change == 0):  # 차례대로 계속 실행이 되는 거지
            now += (a1)  # 그전꺼 끝난 시간 이후 걸린 시간 더해주면 됨
            answer += (now - a2)
        else:
            answer += a1
            now = a1 + a2
            change = 0

        temp = []
        for i in range(len(jobs)):
            if (jobs[i][0] <= now):
                heapq.heappush(heap, (jobs[i][1], jobs[i][0], now))
                temp.append(i)
        for i in range(len(temp) - 1, -1, -1):
            jobs.pop(i)
        if (len(heap) == 0 and (len(jobs)) != 0):
            # 이때는 차례대로 실행이 되지 않는 상황이니 다르게 처리를 해줘야지
            now = jobs[0][0]
            heapq.heappush(heap, (jobs[0][1], jobs[0][0], now))
            jobs.pop(0)
            change = 1

    answer = answer // fin

    return answer

