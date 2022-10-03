from collections import defaultdict


def solution(genres, plays):
    answer = []
    genre_list = defaultdict(int)
    input = []
    cnt = 0
    for genre, play in zip(genres, plays):
        genre_list[genre] += play
        input.append([cnt, genre, play])
        cnt += 1

    input.sort(key=lambda x: (-genre_list[x[1]], -x[2]))
    # print(genre_list)
    # print(input)

    for i in range(len(input) - 1, 1, -1):
        if (input[i][1] == input[i - 1][1] and input[i][1] == input[i - 2][1]):
            del input[i]

    for i in input:
        answer.append(i[0])

    return answer