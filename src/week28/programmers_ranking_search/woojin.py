

def make(start, store, totalz, value, total):

    if start == 4:
        temp = ""
        for l in store:
            temp += l
        if temp not in total:
            total[temp] = [(int)(value)]
        else:
            total[temp].append((int)(value))
        return

    # 한번 넣고
    store.append(totalz[start])
    make(start + 1, store, totalz, value, total)

    # 다시 빼고
    store.pop(-1)

    # 다시 넣고
    store.append("-")
    make(start + 1, store, totalz, value, total)

    store.pop(-1)

def check1(li,value):
    
    left=0
    right=len(li)-1
  
    while left<=right:
        mid=(left+right)//2
        if li[mid]>=value:
            right=mid-1
        else:
            left=mid+1

    return (len(li)-(left))


def solution(info, query):
    answer = []

    total = dict()
    for l in info:
        e = l.split(" ")
        make(0, [], e[:-1], e[-1], total)
    
    for k in total.keys():
        total[k].sort()
    
    
    for k in query:
        e = k.split(" ")
        while "and" in e:
            e.remove("and")
        now=""
        for l in range(len(e)-1):
            now+=e[l]
        if now not in total:
            answer.append(0)
        else:
            t=check1(total[now],(int)(e[-1]))
            answer.append(t)    
   
    return answer
