def solution(s):
    answer = len(s)#가장 긴경우는 이때에해당이 되며 문자열 길이 1일때를 넘길수 있음
    ans=""
    #문자열을 길이 단위로 해서 나누도록 하자
    for i in range(1, len(s) // 2 + 1):#이렇게 나눠주는 이유는
        last = ""
        temp = []
        j = 0
        t = []
        while (j < len(s)):#문자열 길이만큼 루프 돌수 있으며
            t = s[j:j + i]#테스트 길이만큼 증가시켜줌
            if (len(temp) == 0):#아무것도 없다면 넣어주고
                temp.append(t)
            else:
                if (t == temp[-1]):#입력이 되어있던 거랑 같다면 증가
                    temp.append(t)
                else:
                    if (len(temp) == 1):#다른게 튀어나왔는데 그전에 중복된게없던상황
                        # temp.append(t[-1])
                        last += temp[-1]
                    else:#다른게 튀어나왔는데 그전에 있던게 중복되어서 개수만큼 세어주고 진행
                        last = last + str(len(temp)) + temp[-1]
                    temp=[]
                    temp.append(t)
            j=j+i
        #진행이 되고나서 처리가 되지 않은 문자열이 있다면 처리를 해줌 ex) 7이라면 7/2=3 => +1 은 4까지
        #탐색을 하니 4 / 3 이 , 즉 3이 처리되지 않고 남은 문자 , 그러하니 나머지 문자열 처리~
        if (len(temp) == 1):
            last += temp[-1]
        else:
            last = last + str(len(temp)) + temp[-1]
        #최소값 갱신
        answer=min(answer,len(last))

    return answer