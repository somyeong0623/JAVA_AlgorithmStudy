import sys
from collections import deque
input = sys.stdin.readline
go=[]#명령어 집합
numbers=[]#숫자집합

def game(go,number):
    q = deque()
    q.append(number)


    for l in go:
        if len(l)==2:#num 처리를 함
            q.append(l[1])
            if abs(l[1])>10**9:
                print("ERROR")
                return
        else:
            if l=="POP":
                if(len(q))==0:
                    print("ERROR")
                    return
                else:
                    q.pop()

            elif l=="INV":
                if (len(q)) == 0:
                    print("ERROR")
                    return
                else:
                    a1=q.pop()
                    q.append(-a1)
            elif l=="DUP":
                if (len(q)) == 0:
                    print("ERROR")
                    return
                else:
                    a1 = q.pop()
                    q.append(a1)
                    q.append(a1)
            elif l=="SWP":
                if (len(q)) <2:
                    print("ERROR")
                    return
                else:
                    a1 = q.pop()
                    a2=q.pop()
                    q.append(a1)
                    q.append(a2)
            elif l=="ADD":
                if (len(q)) < 2:
                    print("ERROR")
                    return
                else:
                    a1 = q.pop()
                    a2 = q.pop()
                    if abs(a1+a2) > 10 ** 9:
                        print("ERROR")
                        return
                    q.append(a1+a2)

            elif l=="SUB":
                if (len(q)) < 2:
                    print("ERROR")
                    return
                else:
                    a1 = q.pop()
                    a2 = q.pop()
                    if abs(a2-a1) > 10 ** 9:
                        print("ERROR")
                        return
                    q.append(a2 - a1)

            elif l=="MUL":
                if (len(q)) < 2:
                    print("ERROR")
                    return
                else:
                    a1 = q.pop()
                    a2 = q.pop()
                    if abs(a1*a2) > 10 ** 9:
                        print("ERROR")
                        return
                    q.append(a1 * a2)
            elif l=="DIV":
                if (len(q)) < 2:
                    print("ERROR")
                    return
                else:
                    a1 = q.pop()
                    a2 = q.pop()
                    if a1==0:
                        print("ERROR")
                        return
                    if abs(abs(a2)//abs(a1)) > 10 ** 9:
                        print("ERROR")
                        return
                    
                    count=0
                    if a1<0:
                        count+=1
                    if a2<0:
                        count+=1
                    now=abs(a2)//abs(a1)
                    if count==1:
                        q.append(-now)
                    else:
                        q.append(now)
            elif l=="MOD":
                if (len(q)) < 2:
                    print("ERROR")
                    return
                else:
                    a1 = q.pop()
                    a2 = q.pop()
                    if a1==0:
                        print("ERROR")
                        return
                    now=abs(a2)%abs(a1)
                    
                    if a2<0:
                        now*=-1
                    q.append(now)


    if len(q)!=1:
        print("ERROR")
    else:
        print(q.pop())
while True:
    now=list(map(str,input().split()))
    if now and now[0]=="END":
        n=int(input().rstrip())
        for _ in range(n):
            a1=int(input().rstrip())
            numbers.append(a1)
        #이제 여기서 보내서 결과를 확인을 하면은 됨

        for l in numbers:
            game(go,l)
        print()
        now=list(map(str,input().split()))
        go = []
        numbers = []

    else:

        if now[0]!="NUM":
            go.append(now[0])
        else:
            go.append((now[0],(int)(now[1])))
    if now and now[0]=="QUIT":
        break
