def solution(bridge_length, weight, truck_weights):
    window=[0 for i in range(bridge_length)]
    answer=0
    
    while truck_weights:
        if (sum(window)-window.pop(0)+truck_weights[0])<=weight:
            window.append(truck_weights.pop(0))
        else:
            window.append(0)
        print(window)
        print()
        answer+=1

    return answer+len(window)
