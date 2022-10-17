def solution(s):
    answer=len(s)
    for i in range(1,len(s)//2+1):
        tmp=s
        count=1
        tmp_str=""
        while len(tmp)>i:
            tmp_slice=tmp[:i]
            tmp=tmp[i:]
            
            if tmp_slice==tmp[:i]:
                count+=1
            else:
                if count==1:
                    tmp_str+=tmp_slice
                else:
                    tmp_str+=str(count)+tmp_slice
                    count=1
                    
        if count==1:
            tmp_str+=tmp
        else:
            tmp_str+=str(count)+tmp
        answer=min(answer,len(tmp_str))
    
    return answer