def solution(bridge_length, weight, truck_weights):
    queue = []
    times = 1
    pres_weight = 0
    while len(truck_weights) or len(queue):
        if len(truck_weights) > 0:
            if pres_weight + truck_weights[0] <= weight:
                data = truck_weights.pop(0)
                pres_weight += data
                queue.append([data, bridge_length])

        remove_stack = []
        for idx, val in enumerate(queue):
            if val[1] - 1 <= 0:
                remove_stack.append((idx, val[0]))
            else:
                val[1] -= 1
        remove_stack.reverse()
        for i in remove_stack:
            pres_weight -= i[1]
            queue.pop(i[0])

        times += 1
    return times