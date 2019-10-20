function [val]=Horner(x,a,t)
    // calcule:
    // P = f[x_0] + f[x_0,x_1](x-x_0) + ... + f[x_0,...,x_n](x-x_0)...(x-x_(n-1))
    
    // Initialisation
    N = length(a);
    N = N-1;
    b = a(1,$);
    
    // Boucle
    for k=1:N
        c = b.*(t-x(1,$-k))+a(1,$-k);
        b = c;
    end
    
    val = b;
    
endfunction
