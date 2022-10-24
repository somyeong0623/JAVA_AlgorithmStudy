def solution(n, arr1, arr2):
    answer = []

    for i in range(n):
        a1=arr1[i]
        a2=arr2[i]
        temp1 = []
        temp2 = []
        u=[]
        while(a1>0):
            left=a1%2
            temp1.insert(0,left)
            a1=a1//2
        if(len(temp1)!=n):
            t=n-len(temp1)
            while t>0:
                temp1.insert(0,0)
                t-=1
        while (a2 > 0):
            left = a2 % 2
            temp2.insert(0, left)
            a2 = a2 // 2
        if (len(temp2) != n):
            t = n - len(temp2)
            while t > 0:
                temp2.insert(0, 0)
                t -= 1
        e=""
        for i1 in range(n):

            t1=temp1[i1]
            t2=temp2[i1]
            if(t1==0 and t2==0 ):
                #u.append(0)
                e+=" "
            else:
                #u.append(1)
                e+="#"
        answer.append(e)




    return answer