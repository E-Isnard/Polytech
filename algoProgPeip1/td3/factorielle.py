def facto(n):
    if n < 2:
        return 1
    else:
        return n*facto(n-1)

print(facto(120323232))
