// Script TP séance 5: INTERPOLATION POLYNOMIALE

// Discrétisation des Points de l'axe x 
x = linspace(-1,1,500);

///////////////////////////////////////////////////////////////////////////////
// QUESTION 2.1
// 1. Fonction TraceLagrange pour f et g pour différentes valeurs de N
// Fonctions étudiées
f   = sin(x);
g   = exp(x);
// Nombre de points d'interpolation -1
N = 39   // essayez avec plus de points [1,19,29,39];
l = length(N);
for i = 1:l
    x_lag = linspace(-1,1,N(i)+1);
    f_lag = sin(x_lag);
    g_lag = exp(x_lag);
    figure(i)
    subplot(2,2,1)
    [pol_lag] = TraceLagrange(x_lag,f_lag,x);
    plot(x,f)
    title('sinus pour N+1='+string(N(i)+1)+'')
    // erreur
    subplot(2,2,3)
    err = abs(pol_lag-f);
    //plot2d("nl",x,err); // si vous voulez tracer en échelle semi-logarithmique
    plot(x,err);
    title('erreur sinus pour N+1='+string(N(i)+1)+'')
    subplot(2,2,2)
    [pol_lag] = TraceLagrange(x_lag,g_lag,x);
    plot(x,g)
    title('exponentielle pour N+1='+string(N(i)+1)+'')
    // erreur
    subplot(2,2,4)
    err = abs(pol_lag-g);
    plot(x,err);
    title('erreur exponentielle pour N+1='+string(N(i)+1)+'')
end

// 2. Extrapolation: Illustration avec sinus
x_extra = linspace(-1,2,500); // on va jusqu'à 2 alors que l'interpolation s'arrete à 1
f_extra = sin(x_extra);
figure()
subplot(2,1,1)
[pol_lag] = TraceLagrange(x_lag,g_lag,x_extra);
plot(x_extra,f_extra);
title('sinus pour N+1='+string(N($)+1)+'')
// erreur
subplot(2,1,2)
err = abs(pol_lag-f_extra);
plot(x_extra,err);
title('erreur sinus pour N+1='+string(N(i)+1)+'')
///////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////
// QUESTION 2.2
// Fonction TraceLagrange pour h pour différentes valeurs de p et N
// Valeurs de p:
p = 10;  // essayez avec d'autre valeurs de p [10,90];
N = 39; // essayez avec d'autres valeurs de N [9,19,39,79];
l = length(N);
L = length(p);

for k=1:L
    h_p = 1 ./ (1+p(k).*x.^2);
    for i = 1:l
        x_lag = linspace(-1,1,N(i)+1);
        h_lag = 1 ./ (1+p(k).*x_lag.^2);
        figure()
        subplot(2,1,1)
        [pol_lag] = TraceLagrange(x_lag,h_lag,x);
        plot(x,h_p)
        title('h_p pour N+1='+string(N(i)+1)+' et p='+string(p(k))+'')
        // erreur
        subplot(2,1,2)
        err = abs(pol_lag-h_p);
        plot(x,err);
        title('erreur h_p pour N+1='+string(N(i)+1)+' et p='+string(p(k))+'')
    end
end
///////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////
// QUESTION 2.3
// Points de Tchebychev: plus nombreux aux bords
// Valeurs de p:
p = 10;  // essayez avec d'autre valeurs de p [10,90];
N = 39; // essayez avec d'autres valeurs de N [9,19,39,79];
l = length(N);

for k=1:2
    h_p = 1 ./ (1+p(k).*x.^2);
    for i = 1:l
        vect  = (0:1:N(i)); 
        x_lag = cos(((2*vect + 1)*%pi) ./ ((N(i)+1)*2));
        h_lag = 1 ./ (1+p(k).*x_lag.^2);
        figure()
        subplot(2,1,1)
        [pol_lag] = TraceLagrange(x_lag,h_lag,x);
        plot(x,h_p)
        title('h_p pour N+1='+string(N(i)+1)+' et p='+string(p(k))+', points de Tchebychev')
        // erreur
        subplot(2,1,2)
        err = abs(pol_lag-h_p);
        plot(x,err);
        title('erreur h_p pour N+1='+string(N(i)+1)+' et p='+string(p(k))+', points de Tchebychev')
    end
end
///////////////////////////////////////////////////////////////////////////////

