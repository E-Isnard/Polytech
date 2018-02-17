#!/usr/bin/python3
from peip3 import *


def main():
        f = open(argv[1],"r")
        i = 0
        for line in f:
            if i == 0:
                line = line.replace("\n","")
                print(line[::-1],end="")
            else:
                print(line[::-1],end="")
            i += 1
        print()
        close(f)

main()
if os.name=="nt":

    system("pause")
