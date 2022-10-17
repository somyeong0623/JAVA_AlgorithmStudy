def solution(n, times):
    times.sort()
    max_value = n * times[-1]
    s = 0
    e = max_value
    find_min = 1000000000 * 1000000000  # 찾은 수
    while s <= e:
        mid = (s + e) // 2
        search_people = sum([mid // i for i in times])
        if search_people == n:
            if mid < find_min:
                find_min = mid
            e = mid - 1
        elif search_people > n:
            e = mid - 1
        elif search_people < n:
            s = mid + 1
    if find_min == 1000000000 * 1000000000:
        find_min = s
    return find_min