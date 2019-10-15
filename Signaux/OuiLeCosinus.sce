fe = 22050
t=0:1/fe:6


s=sin(2*%pi * 440*t).*(t<2)+sin(2*%pi*820*t).*(t>4)


sleep(100)
playsnd(s,fe)
