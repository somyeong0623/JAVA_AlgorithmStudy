import sys
input = sys.stdin.readline


i=int(input().rstrip())
de=list(map(int,input().split()))

de.sort()
left=0
right=i-1
uo=sys.maxsize

tl=left
tr=right

ch=0
while left<right:
    mid=de[left]+de[right]
    check=uo-mid
    if (abs(mid) <= uo):
        uo = abs(mid)
        tl = left
        tr = right
    if (mid == 0):
        print(str(de[left]) + " " + str(de[right]))
        ch=1
        break
    if mid<0:
        left+=1
    else:
        right-=1
    

if(ch==0):
    print(str(de[tl]) + " " + str(de[tr]))