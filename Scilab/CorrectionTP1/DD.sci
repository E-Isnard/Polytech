function [a]=DD(x,y)
    // Calcule le tableau des différences divisées pour le polynome de Lagrange
    // pour les données (x_i,y_i), i=1,2...,N+1
    // Entrées: x liste de N+1 points distincts, y liste de N+1 points
    // Sorties: a vecteur avec les différences divisées
    
    // Initialisations
    N    = length(x);
    N    = N-1;
    a    = zeros(1,N+1);
    a(1) = y(1);
    
    vecteur = y;
    
    // Boucle
    for k = 1:N
        vecteur_courant = (vecteur(1,2:$)-vecteur(1,1:$-1))./(x(1,k+1:$)-x(1,1:$-k));
        a(k+1)  = vecteur_courant(1);
        vecteur = vecteur_courant;
    end

endfunction
