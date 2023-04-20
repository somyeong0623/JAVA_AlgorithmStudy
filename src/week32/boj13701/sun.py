import os
import sys
from collections import deque
from heapq import heappush, heappop

sys.stdin = open('../input.txt')

def read_word():
    unstdin = os.fdopen(sys.stdin.fileno(), 'rb', buffering=1000000)
    ch = unstdin.read(1)
    while True:
        num = 0
        while not (ch == b'\n' or ch == b'' or (ch >= b'0' and ch <= b'9')):
            ch = unstdin.read(1)
        if ch == b'\n' or ch == b'':
            break
        while ch >= b'0' and ch <= b'9':
            num = num * 10 + int(ch)
            ch = unstdin.read(1)
        yield num

def set_bit(n):
    nn = n // 8
    nr = n % 8

    ret = (bits[nn] & (1 << nr)) == 0

    bits[nn] |= (1 << nr)
    print(nn, nr, bits[nn], "비츠")

    return ret

if __name__=="__main__":
    bits = bytearray(4194304)
    for num in read_word():
        if set_bit(num) == True:
            print(num)
            # print(num, end=" ")
    print()