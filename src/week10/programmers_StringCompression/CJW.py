def sol(s, n):
    result = ''
    while len(s):
        counter = 1
        for i in range(0, len(s), n):
            status = (s[0:n] == s[i + n:i + 2*n])
            if status:
                counter += 1
            else:
                result += (str(counter) if counter > 1 else '') + s[0:n]
                s = s[counter * n:]
                break
        else:
            if counter > 1:
                result += (str(counter) if counter > 1 else '') + s[0:n]
            else:
                result += s[0]
            break

    return len(result)


def solution(s):
    arr = []
    for i in range(1, len(s) + 1):
        arr.append(sol(s, i))

    return min(arr)