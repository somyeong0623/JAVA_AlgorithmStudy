def solution(n, times):
    left = min(times)
    right = n*max(times)
    while left<=right:
        mid = (left+right)//2
        #tmp = sum([mid//i for i in times])
        tmp=0
        for i in times:
            tmp+=mid//i
            if tmp>n:
                break
        if tmp<n:
            left = mid +1
        elif tmp>=n:
            right = mid-1
        
        
    return (left+right+1)//2
