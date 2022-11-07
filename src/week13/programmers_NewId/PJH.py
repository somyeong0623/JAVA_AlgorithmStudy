def solution(new_id):
    new_id = new_id.lower()
    tmp_id=""
    for i in new_id:
        if i.isalpha() or i.isdecimal() or i=='-' or i=='_' or i==".":
            tmp_id+=i
    new_id = tmp_id
    
    #print(new_id)
    
    new_id +="A"#마지막거 무시되어버리니 추가해서 버리기 역할
    new_id = [new_id[i] for i in range(len(new_id)-1) if not(new_id[i]=="." and new_id[i+1]==".")]
    new_id = "".join(new_id)
    #print(new_id)
    
    if len(new_id)!=0 and new_id[0]=='.':
        new_id = new_id[1:]
    
    if len(new_id)!=0 and new_id[len(new_id)-1]=='.':
        new_id = new_id[:len(new_id)-1]
    #print(new_id)
    
    if len(new_id)==0:
        new_id = "a"
    new_id = new_id[:15]
    #print(new_id)
    
    while list(new_id)[-1]==".":
        new_id = new_id[:len(new_id)-1]
        
    #print(new_id)
    while len(new_id)<=2:
        new_id+=new_id[len(new_id)-1]
    #print(new_id)
    return new_id
