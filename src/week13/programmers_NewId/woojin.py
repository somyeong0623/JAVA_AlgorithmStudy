def solution(new_id):
    answer = ''
    # 유사하면서 규칙에 맞는 아이디를 추천해주는 프로그램 개발하고자 함
    # 마침표는 처음과 끝에 사용이 불가능함, 연속으로 사용도 불가능함


    #모든 대문자를 소문자로 치환
    new_id=new_id.lower()


    #알파벳 소문자 , 숫자, -, .를 제외한 문자제거

    for i in new_id:
        if i.isalnum()  or i in'-_.':
            answer+=i

    while '..' in answer:
        answer=answer.replace('..','.')
    if len(answer)>0 and answer[0]=='.':
        answer=answer[1:]
    if len(answer)>0 and answer[-1]=='.':
        answer=answer[:-1]

    if len(answer)==0:
        answer='a'

    if len(answer)>=16:
        answer=answer[:15]
    if answer[-1]=='.':
        answer=answer[:-1]

    if len(answer)<=2:
        while len(answer)<3:
            answer=answer+answer[-1]


    return answer
