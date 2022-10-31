import sys
def solution(gems):
    answer = []
    # 가장 짧은 구간을 찾아서 가장 작은 구간을 찾아 내도록 하여 보자
    total=dict()
    total_len=0
    #전체 dict로 표현을 하고자 ㅎ마
    for i in gems:
        if i not in total:
            total[i]=1
            total_len+=1
        else:
            total[i]+=1

    max_len=sys.maxsize
    left=0
    right=0
    now_count=0
    temp=dict()

    for i in total.keys():
        temp[i]=0
    #print(temp)
    temp[gems[0]]=1
    now_count=1

    #오른쪽 까지 가는거는 지금까지 들어있는게 아직 다포함 되지 않았다면 진격
    #왼쪽이 증가하는 경우는 지금까지 들어 있는게 다 포함되었으면 진격
    while(1):
        #오른쪽 값을 하나 받아줌


        if now_count == total_len:  # 지금까지 들어있는게 모두 들어있다면
            mid=right-left
            if(mid<max_len):
                max_len=mid
                answer = []
                answer.append(left+1)
                answer.append(right+1)
            if(temp[gems[left]]==1):
                now_count-=1
            temp[gems[left]]-=1
            left+=1


        else:#오른쪽 진격 가능하면 하고 안된다면 끝내야함
            if right==len(gems)-1:
                break
            else:
                right+=1
                mid=gems[right]
                if (temp[mid]==0):
                    temp[mid]+=1
                    now_count+=1
                else:
                    temp[mid] += 1


    return answer