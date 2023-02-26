import sys
signin=-sys.maxsize
sales=-sys.maxsize


def make(users,emoticons,now,visit,calculated):
    global signin,sales
    if visit==len(emoticons):
        #이제 여기서 계산을 해서 파악을 하면된다.
        temp_signin=0
        temp_sales=0
        #각 유저마다 계산을 해야함
        for a1,a2 in users:
            temp=0
            #비율이 넘는거 구매 하는거 계산
            for i in range(len(calculated)):
                tt=calculated[i]
                if tt>=a1:
                    temp+=emoticons[i]*(100-tt)*0.01
            
            #누적 구매값이 넘어서게 될경우에는
            if temp>=a2:
                temp_signin+=1
            else:
                temp_sales+=temp
        
        #이제 서비스 가입자가 최대로 늘어났다면 변화 시켜줘야함
        if temp_signin>signin:
            signin=temp_signin
            sales=temp_sales
        elif temp_signin==signin:
            if temp_sales>sales:
                sales=temp_sales

        return
    
    for i in now:
        calculated[visit]=i
        make(users,emoticons,now,visit+1,calculated)


def show(users,emoticons):
    global signin,sales
    #이것은 중복 순열로 풀어야 할듯? 하는 생각이 든다.....
    #우선 시작을 해보자구
    
    now=[10,20,30,40]#넣어야할 할인율을 나타낸다.
    visit=[0]*len(emoticons)
    calculated=[0]*len(emoticons)            
    make(users,emoticons,now,0,calculated)

def solution(users, emoticons):
    global signin,sales
    answer = []
    #이모티콘 가입자를 최대로 늘리는게 최우선 목표이다!

    show(users,emoticons)
    answer.append(signin)
    answer.append(sales)
    
    return answer