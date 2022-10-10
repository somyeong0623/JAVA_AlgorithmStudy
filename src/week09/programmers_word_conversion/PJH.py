from collections import deque
def solution(begin, target, words):
    answer = 0
    begin = deque([begin])

    while (not len(begin) == 0):
        size = len(begin)

        for i in range(size):
            tmp = begin.pop()
            for j in range(len(words) - 1, -1, -1):
                if (check(tmp, words[j])):
                    begin.appendleft(words[j])
                    del words[j]
        answer += 1
        if target in begin:
            return answer

    return 0

def check(word1, word2):
    cnt = 0

    for i in range(len(word1)):
        if (word1[i] != word2[i]):
            cnt += 1
    if cnt == 1:
        return True
    else:
        return False
