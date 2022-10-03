def solution(genres, plays):
    answer = []
    ranking_kind = dict()
    play = dict()
    for idx, key in enumerate(genres):
        if key not in play:
            play[key] = list()
            ranking_kind[key] = 0
        ranking_kind[key] += plays[idx]
        play[key].append((plays[idx], idx))

    for i in play:
        play[i].sort(key=lambda x:x[0],reverse=True)
    ranking_kind = [[i,ranking_kind[i]] for i in ranking_kind]
    ranking_kind.sort(key=lambda x:x[1],reverse=True)

    for i in ranking_kind:
        for repeat in range(2):
            try:
                answer.append(play[i[0]][repeat][1])
            except Exception:
                pass
    return answer