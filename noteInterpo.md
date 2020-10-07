# Coordonnées barycentrique dans un triangle

1.
Les 3 droites séparent le plan en 7 régions.

Dans la région 7 toutes les coordonnées barycentriques des points sont positives.
Dans la 3 seul la coordonnée relative à A est négative.
Dans la région 6 les coodonnées realtives à B et C sont négatives.
Dans la 2 ce sont celles de A et C.
Dans la 1 c'est celle de C qui est négative.
Dans la 5 c'est celle de B.
Dans la 4 ce sont celles de A et B.

2.

$$f(p) = \sum_{i=1}^n b_i(p)f(v_i) $$

or $b_i(p) = \frac{A_i}{A}$

On a 

$A_1 = \det(v_2-v_1,p-v_1)/2 = \frac{1}{2}\begin{vmatrix}
    v_{2x}-v_{1x} & p_x-v_{1x} \\
    v_{2y}-v_{1y} & p_y-v_{1y}
\end{vmatrix} = [(v_{2x}-v_{1x})(p_y-v_{1y})-(p_x-v_{1x})(v_{2y}-v_{1y})]/2$ 

donc $\frac{\partial A_1}{∂p_x} = -v_{1x}(v_{2y}-v_{1y})$ et $\frac{\partial A_1}{∂p_y} = (v_{2x}-v_{1x})$
 donc $∇_pA_1$ est constant.

 On pourrait refaire ce résonnement pour chaque $A_i$.

 On en déduit que $∇_pf$ est constant , et donc $f$ est linéaire en $p$.
