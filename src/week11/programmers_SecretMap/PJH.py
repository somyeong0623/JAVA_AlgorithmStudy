def solution(n, arr1, arr2):
    answer = []
    for i,j in zip(arr1,arr2):
        tmp = str(bin(i|j))[2:].zfill(n)
        input = ""
        for i in tmp:
            if i=='1':
                input += "#"
            else:
                input += " "
        answer.append(input)
    return answer
