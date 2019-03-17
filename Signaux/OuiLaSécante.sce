clc;
clear;
xdel(winsid());

d = 1/48000;
t = 0:d:3;

e1 = 1/2*sin(2*%pi*400*t)+1/2*sin(2*%pi*660*t).*(t>=0) - ((1/2*sin(2*%pi*400*t)+1/2*sin(2*%pi*660*t)).*(t>=1));
e2 = sin(2*%pi*880*t).*(t>=2) - sin(2*%pi*880*t).*(t>=3);
e = e1 + e2;

loadmatfile('/home/enzo/Documents/eglise.mat');
s = conv(h_church,e);

sleep(100);
playsnd(e,48000)
sleep(200);
playsnd(s,48000)

