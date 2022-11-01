if __name__ == "__main__":
    first = list(input())
    second = list(input())
    while (True):
        if (len(first) == len(second)):
            break
        if (second[-1] == 'A'):
            second.pop()
        elif (second[-1] == 'B'):
            second.pop()
            second = second[::-1]

    if(first != second):
        print(0)
    else:
        print(1)