def solution(jobs):
    jobs.sort()
    time = 0
    len_jobs = len(jobs)
    target = [i for i in jobs if i[0]<=0]
    if len(target)==0:
        target = [i for i in jobs if i[0]<=jobs[0][0]]
    next_jobs = jobs[len(target):]
    target.sort(key=lambda x: x[1])
    
    answer = calc(target[1:]+next_jobs,target[0][0] + target[0][1],target[0][1])
    return answer//len_jobs


def calc(jobs,time,runtime):
    if len(jobs)==0:
        return runtime
    else:
        target = [i for i in jobs if i[0]<=time]
        if len(target)==0:
            jobs.sort()
            return calc(jobs,jobs[0][0],runtime)
        next_jobs = jobs[len(target):]
        target.sort(key=lambda x: x[1])
        return calc(target[1:]+next_jobs,time + target[0][1],(time + target[0][1])-target[0][0]+runtime)
    