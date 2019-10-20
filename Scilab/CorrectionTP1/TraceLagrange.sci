function [val]=TraceLagrange(x,y,t)
    // Prend en entrée (N+1) points x_i distincts d'un intervalle [a,b], une 
    // liste de (N+1) valeurs y_i, une liste de M points t_k, et qui calcule
    // le polynôme d'interpolation de Lagrange Pn(x) qui passe par les points 
    // (x_i,y_i) par une fonction de différences divisées, et trouve les valeurs
    // Pn(t_k) par l'algorithme de Horner.
    // La sortie de cette fonction est le tracé de la fonction initiale, des
    // points d'interpolations et du polynôme Pn
    
    // Fonction DD
    a = DD(x,y);

    // Fonction Horner
    val = Horner(x,a,t);
    
    // Tracer des fonctions
    plot(t,val,'r.','markersize',2);
    
endfunction
