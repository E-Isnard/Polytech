//Exo 5 analyse numérique

function R=condL(A)
    R=diag(1./max(abs(A),"c"))
endfunction

function C=condC(A)
    C=diag(1./max(abs(A),"r"))
endfunction

function B=condLC(A)
    R=condL(A)
    B=condC(R*A)
endfunction

function S=condCL(A)
    C=condC(A)
    S=condL(C*A)
endfunction


A=[10 100;0.1 0.2]
R=condL(A)
C=condC(A)
B=condLC(A)
S=condCL(A)
disp(A)
disp("Conditionnement de A")
disp(cond(A))
disp("Conditionnement de RA")
disp(cond(R*A))
disp("Conditionnement de AC")
disp(cond(A*C))
disp("Conditionnement de RAB")
disp(cond(R*A*B))
disp("Conditionnement de SAC")
disp(cond(S*A*C))

A=[1 10^8; 2 0]

R=condL(A)
C=condC(A)
B=condLC(A)
S=condCL(A)
disp(A)
disp("Conditionnement de A")
disp(cond(A))
disp("Conditionnement de RA")
disp(cond(R*A))
disp("Conditionnement de AC")
disp(cond(A*C))
disp("Conditionnement de RAB")
disp(cond(R*A*B))
disp("Conditionnement de SAC")
disp(cond(S*A*C))

for i=1:20
    disp(":^)")
end



