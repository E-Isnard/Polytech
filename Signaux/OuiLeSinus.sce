clc
x=[0:0.1:10]
title("Exercice 1.3")
xlabel("Axe des x")
ylabel("Axe des y")

function [x]=s(x)
    x=exp(-x).*cos(4*x-0.5)
endfunction

disp(s(100))
disp(max(s(x)))
plot(x,s(x))

