def DFS(data, start, result, deapth):
    status = False
    for i in data:
        status = status or (len(data[i]) > 0)
    if status:
        if start in data:
            for idx, target in enumerate(data[start]):
                data[start].remove(target)
                result[deapth] = target
                var = DFS(data, target, result, deapth + 1)
                if var is not None:
                    return result
                data[start].insert(idx, target)
    else:
        return result


def solution(tickets):
    data = dict()
    counter = 0
    for i in tickets:
        if i[0] not in data:
            data[i[0]] = []
        data[i[0]].append(i[1])
        counter += 1

    for i in data:
        data[i].sort()

    var = DFS(data, 'ICN', [0 for _ in range(counter)], 0)

    var.insert(0, 'ICN')
    return var