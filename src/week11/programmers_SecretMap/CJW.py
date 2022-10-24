def solution(n, arr1, arr2):
    map_data = [[0 for _ in range(n)] for _ in range(n)]
    for i in range(n):
        for j in range(n):
            map_data[i][j] |= ((arr1[i] % 2) | (arr2[i] % 2))
            arr1[i] >>= 1
            arr2[i] >>= 1
        map_data[i].reverse()
    map_data = [''.join(['#' if i == 1 else ' ' for i in j]) for j in map_data]
    return map_data