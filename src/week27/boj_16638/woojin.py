import sys
from copy import deepcopy
from collections import deque
input=sys.stdin.readline
n=int(input().rstrip())
first=list(map(str,input().rstrip()))

number=[]
operator=[]

for l in first:
    if "0"<=l<="9":
        number.append((int)(l))

    else:
        operator.append(l)
answer=-sys.maxsize

def calculate(first,second,operator):

    if operator=="+":
        return first+second
    elif operator=="-":
        return first-second
    else:
        return first*second


def game(now,number,operator,test,n):
    global answer
    #연산자 순으로 진행을 해보도록 하자

    if now>=len(operator):

        #이제 계산을 해보도록 하자
        #이때는 연산자가 우선이 되는것들을 한번에 처리를 해보도록 하자
        index=0
        q=deque()
        #우선 곱하기 연산 부터 우선순위를 가지니 연산을 해보자
        while index<len(test):
            temp=test[index]
            if temp=="*":
                before=q.pop()
                next=test[index+1]
                q.append(calculate(before,next,temp))
                index+=2
            else:
                q.append(temp)
                index+=1
        test_sum=q.popleft()
        
        #우선순위들을 모두 처리를 한후에 연산을 진행해보는 과정
        while q:
            temp=q.popleft()
            if temp =="+" or temp=="-":
                next=q.popleft()
                test_sum=calculate(test_sum,next,temp)
        answer=max(answer,test_sum)
        return

    test1=deepcopy(test)#괄호를 만들지 않고 연산해내기 위해 사용하는 용도
    test2=deepcopy(test)#괄호를 만들고 연산하기 위해 사용하는 용도

    #괄호를 만들지 않고
    test1.append(number[now])
    test1.append(operator[now])
    if now==len(operator)-1:
        test1.append(number[now+1])
    game(now+1, number, operator, test1, n)


    #괄호를 만든후 진행
    #괄호를 넣는 다면 인덱스 두개를 넘겨서 진행을 해야함

    #now+2가 정해진 인덱스를 넘어서게 된다면....
    # 3 8 7 9 2
    #  + * - *  => now는 - 즉 2인 상태를 의미
    a1 = number[now]
    a2 = number[now + 1]
    opop = operator[now]
    temp_cal = calculate(a1, a2, opop)
    if now+2>=len(operator):
        test2.append(temp_cal)
        #위의 예시에서 * 2 가 남아 있는데 현상황은, 더이상 괄호를 채울수 없는 경우이기에 처리를 해주는 부분이다 
        for l in range(now+1,len(operator)):
            test2.append(operator[l])
            test2.append(number[l+1])
        game(now + 2, number, operator, test2, n)
        
    else:
        #더 괄호를 채울수 있기에 괄호를 채우면서 진행하려는 부분이다
        test2.append(temp_cal)
        test2.append(operator[now+1])
        game(now + 2, number, operator,  test2, n)

test=[]
if n>2:
    game(0,number,operator,test,n)
    print(answer)
else:
    print(*number)