def check(str1, str2):
    if len(str1) != len(str2):
        return False

    counter = 0
    for i in range(len(str1)):
        if str1[i] != str2[i]:
            counter += 1
    return True if counter == 1 else False


def solution(begin, target, words):
    queue = []
    queue.append((begin, 0))

    while len(queue):
        data = queue.pop(0)
        if data[0] == target:
            return data[1]
        for idx, val in enumerate(words):
            if check(data[0], val):
                queue.append((val, data[1] + 1))
                words.pop(idx)
    return 0