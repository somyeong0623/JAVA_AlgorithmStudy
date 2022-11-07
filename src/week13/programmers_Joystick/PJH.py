def solution(name):
    init_A = list("A"*len(name))
    name=list(name)
    TF=[]
    for a,b in zip(name,init_A):
        TF.append(a==b)  
    count=move_count(TF,0,0)
    for alpa in name:
        tmp=ord(alpa)-ord("A")
        count+=min(ord(alpa)-ord("A"),ord("Z")-ord(alpa)+1)
    return count

def move_count(TF,point,count):
    if sum(TF)==len(TF):
        return count
    else:
        tmp_all=[]
        target_index=[]
        tmp_TF=TF.copy()
        target_index_count=0
        
        while len(target_index) <= 1:
            if not TF[(point+target_index_count)%len(TF)]:
                target_index.append((point+target_index_count)%len(TF))
            if not TF[(point-target_index_count)%len(TF)]:
                target_index.append((point-target_index_count)%len(TF))
            target_index_count+=1
        
        for i in set(target_index):
            if not TF[i]:
                tmp_TF=TF.copy()
                tmp_TF[i]=True
                tmp_all.append(move_count(tmp_TF,i,count+min(len(TF)-abs(point-i),abs(point-i))))
        return min(tmp_all)
