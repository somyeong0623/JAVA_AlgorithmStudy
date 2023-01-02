import sys
from collections import deque

sys.stdin = open('../input.txt')

def count_bin(num):
    value = bin(num)[2:]
    count = 0
    length_value = len(value)
    for i in range(length_value):
        if value[i] == '1':
            pow = length_value-1-i
            count += psum[pow]
            count += (num - 2 ** pow + 1)
            num = num - 2 ** pow
    return count

if __name__=="__main__":
    num1, num2 = map(int, sys.stdin.readline().split())
    psum = [0] * 60

    for i in range(1, 60):
        psum[i] = 2 * psum[i-1] + pow(2, i-1)
    print(count_bin(num2) - count_bin(num1-1))