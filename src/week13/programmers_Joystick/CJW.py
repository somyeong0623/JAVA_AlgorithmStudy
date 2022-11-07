alphabet = [str(chr(i + 65)) for i in range(26)]


def small_distance(word):  # 문자의 최소 길이
    pos = alphabet.index(word)
    d1 = 0
    for i in range(26):
        if alphabet[(pos + i) % 26] == 'A':
            d1 = i
            break
    d2 = 0
    for i in range(26):
        if alphabet[pos - i] == 'A':
            d2 = i
            break
    return d2 if d1 > d2 else d1


def word_distance(name, pos):  # 문자열끼리의 최소 길이
    d1 = 0
    for i in range(len(name) - 1):
        if name[(pos + i + 1) % len(name)] != 'A':
            d1 = i + 1
            break

    d2 = 0
    for i in range(len(name) - 1):
        if name[(pos - i - 1) % len(name)] != 'A':
            d2 = i + 1
            break

    return 0 if d1 == d2 else - 1 * d2 if d1 > d2 else d1


def solution(name):
    pos = 0
    calc = 0
    while name != ''.join([str('A') for i in range(len(name))]):
        calc += small_distance(name[pos % len(name)])
        name = name[0:pos % len(name)] + 'A' + name[pos % len(name) + 1:]
        if name == ''.join([str('A') for i in range(len(name))]):
            break

        dir = word_distance(name, pos)
        if dir == 0:
            dir = 1
        pos += dir
        calc += abs(dir)

    return calc if calc != 0 else 0