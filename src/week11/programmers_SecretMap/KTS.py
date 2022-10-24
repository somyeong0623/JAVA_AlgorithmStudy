def solution(n, arr1, arr2):
    answer = []
    
    for i in range(n):
        num1_bin = str(format(arr1[i], 'b')).zfill(n)
        num2_bin = str(format(arr2[i], 'b')).zfill(n)
        tmp = ''
        print(num1_bin)

        for j in range(n):
            if num1_bin[j] == '1' or num2_bin[j] == '1':
                tmp += '#'
            else:
                tmp += ' '
                
        answer.append(tmp)
        
    return answer
