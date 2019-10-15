def bizare(v):
   if v>=30:
      print("cas 1")
      return 30
   else :
      if v>=0:
         print("cas 2")
         return 0
      else :
         print("cas 3")

# programme principal
val = int(input("un entier "))
v = bizare(val)
print(v*3)

