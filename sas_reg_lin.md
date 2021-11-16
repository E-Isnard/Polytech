# SAS: Régression linéaire

__But__ : Trouver un lien fonctionnel entre deux
var aléatoire X et Y.

$$Y = f(X)$$

Si Y est gaussienne, alors on a que :

$$\mathbb{E}[Y|X=x] = \beta_0+\beta_1x = f(x)$$

et f est la meilleure explication fonctionelle entre X et Y, au sens où:

$$f = \argmax_g \mathbb{E}[(Y-f(X))^2]$$

En pratique $\mathbb{E}[Y|X=x]$ est inaccessible et on dispose d'observations $(x_i,y_i)$.

On va modéliser par un modèle de régression linéaire:

$$y_i = \beta_0+\beta_1x_i+\varepsilon_i $$

__Hypothèses faibles__ :

- $\mathbb{E}[\epsilon_i] = 0$
- $\mathrm{cov}(\epsilon_i,\epsilon_j) = \sigma^2\delta_{ij}$

__Hypothèse forte__ :

Hypothèses faibles + $\epsilon_i \sim N(0,\sigma^2)$

__Remarque__ : On écrit plus souvent:

$Y = \mathbb{X}\beta+U$

__Q__ : Comment estimer $\beta$ ?

Deux méthodes:

- Moindres carrés (hypothèses faibles)
- Maximence de vraissemblance (hypothèses fortes)

Quand on a un bruit gaussien, les deux méthodes aboutissent à la même solution car dans les 2 cas on a à minimiser $D(\beta) = \|Y-\mathbb{X}\beta\|^2$

__Résultat__ :

La solution optimal du pb des moindres carrés avec deux vars est donné par:

$$ 
\hat{\beta}_1 = \frac{\sum (x_i-\bar{x}_n)(y_i-\bar{y}_n)}{\sum (x_i-\bar{x}_n)^2}
$$
$$
\hat{\beta}_0 = \bar{y}_n-\hat{\beta}_1\bar{x}_n
$$

__Propriété__:

- $\mathbb{E}[\hat{\beta_i}] = \beta_i$ (pas de biais)
- Si les $\varepsilon_i$ sont gaussiens, alors $\beta_i \sim N(\beta_i,\sigma^2_{\beta_i})$

$$
\begin{bmatrix}
\sigma^2_{\beta_0} & \sigma_{\beta_0\beta_1}
\\

\sigma_{\beta_1\beta_0} & \sigma^2_{\beta_1}
\end{bmatrix} = \sigma^2(\mathbb{X}\mathbb{X}^T)^{-1}
$$

Besoin de $\sigma^2$ pour calculer les $\sigma^2_{\beta_i}$

Estimateur de $\sigma^2$:

$$\hat{\sigma}^2_n = \frac{1}{n-2}\sum (y_i-\hat{y}_i)^2$$

__Interprétation géométrique de la régression linéaire__ :

$\hat{\beta}$ est la projection orthogonale de $Y$ sur $\mathrm{Vect}(\mathbb{X})$. On a $\epsilon_i\in \mathrm{Vect}(\mathbb{X})^\perp$ et $\dim(\mathrm{Vect}(\mathbb{X})^T) = n-2$ en régression linéaire simple.

Test sur $\beta_1$:

- $H_0$: $\beta_1 = 0$ (pas de dépendance linéaire entre X et Y)
- $H_1$: $\beta_1 \neq 0$ (dépendance linaire entre X et Y)

Dans le cadre gaussien, on a:

$$\frac{(\hat{\beta}_1-\beta_1)}{\sigma^2_{\beta_1}} \sim T(n-2) $$

Région de rejet de $H_0$

$$\{|\frac{\hat{\beta}_1}{\sigma^2_{\beta_1}}|>t_{1-\alpha/2 ; n-2}\}$$ 

Intervalle de confiance : $[\hat{\beta}_1\pm t_{1-\alpha/2;n-2}\sigma^2_{\beta_1}]$

/!\ Si $I_{\beta_i}$ sont les intervalles de conficance des $\beta_i$ alors on a pas que la région de conficance de $(\beta_0,\beta_1)$ est $I_{\beta_0}\times I_{\beta_1}$.
En fait cette région de confiance est une ellipse /!\

__Résulat__ : cadre Gaussien

Intervalle de confiance pour $\mathbb{E}[Y]$ lorsque $X=x_0$

$$[\hat{y}\pm \hat{\sigma}_nt_{1-\alpha/2;n-2}\sqrt{\frac{1}{n}+\frac{(x_0-\bar{x}_n)^2}{\sum (x_i-\bar{x}_n)^2}}] $$

celui pour Y:
$$[\hat{y}\pm \hat{\sigma}_nt_{1-\alpha/2;n-2}\sqrt{1+\frac{1}{n}+\frac{(x_0-\bar{x}_n)^2}{\sum (x_i-\bar{x}_n)^2}}] $$

/!\ $x_0$ ne doit pas être dans le jeu de données. /!\

__Qualité du modèle__ : 

En régression, un quantificateur de la qualité du modèle est le coefficient de détermination $R^2$ : 

$$R^2 = \frac{\|\hat{y}-\bar{y}_n\|^2}{\|y-\bar{y}_n\|^2}$$


Propriétés: Dans le cadre de la régression linéaire simple, on a : 

$$R^2 = \mathrm{cov}((x_i),(y_i))^2$$


__Régression linéaire multiple__ : 

Cadre:

- $Y$ var réponse numérique
- $(X^{(i)})$ : $p$ variables explicatives numériques

Modélisation:

$$Y_i = \beta_0 + (\sum \beta_i X^{(i)})+\epsilon_i $$

$\implies$ $$Y = \mathbb{X}\beta+U$$

$\mathbb{X}\in \mathbb{R}^{n\times (p+1)}$

On peut avoir des problèmes de multicolinéarité : 

- soit des combinaisons exactes entre les colonnes de $\mathbb{X} \implies \mathbb{X}^T\mathbb{X}\notin \mathrm{GL}(n,\mathbb{R})$
- soit des dépendances linéaires fortes entre les colonnes de $\mathbb{X}$ (var propres de $\mathbb{X}^T\mathbb{X}$ proches de 0)

Pour autant même dans ces cas là on peut faire une régression linéaires.

Pour le moment on ne se place pas dans ces cas-là.

On peut alors estimer $\beta$ par $\hat{\beta}$ défini par :

$$\hat{\beta} = (\mathbb{X}^T\mathbb{X})^{-1}\mathbb{X}Y$$

On déinit alors $\hat{Y}$ par : $\hat{Y} = \mathbb{X}\hat{\beta} = P_{\mathbb{X}}Y$

où $P_{\mathbb{X}}$ est la proj orthogonale sur $\mathrm{Vect}(\mathbb{X})$.

__Propriétés__: 

$\hat{\beta}$ est un estimateur sans biais de $\beta$ de variance $\sigma^2(\mathbb{X}^T\mathbb{X})^{-1}$.

Sous les hypothèses fortes:

$$\hat{\beta}\sim \mathcal{N}(\beta,\sigma^2(\mathbb{X}^T\mathbb{X})^{-1})$$

__Proposition__ :

Un estimateur sans biais de $\sigma^2$ est $\hat{\sigma}^2_n$ défini par : 

$$\hat{\sigma}^2_n = \frac{1}{n-(p+1)}\sum (y_i-\hat{y}_i)^2 $$

/!\ p+1 car $\mathbb{X}^T\mathbb{X}$ est inversible. /!\

$\hat{\sigma}_n^2 \rightarrow_{ps} \sigma^2 $

Sous les hypothèses fortes:

$$\frac{\hat{\beta}_i-\beta_i}{\hat{\sigma}^2_{\hat{\beta}_i}}\sim T(n-(p+1))$$

__Tests__ : 

Test de significativité de $\beta_i$ : 

- $H_0 : \beta_i = 0$ (pas d'influence linéaire de $X^{(i)}$ sur $Y$
- $H_1 : \beta_i \neq 0$

Région de rejet de $H_0$ : 

$$\{\frac{|\hat{\beta}_i|}{\hat{\sigma}_{\hat{\beta}_i}} > t_{1-\alpha/2;n-(p+1)}\}$$ 

Test de significativité du modèle:

- $H_0 : \beta_i = 0,  \forall i> 0$
- $H_1 : \exists j>0 : \beta_j \neq 0$

Soit $\hat{Y}_0$ la projection de $\hat{Y}$ sur $\mathrm{Vect}(1,\cdots,1)$
$$F = \frac{\|\hat{Y}-\hat{Y}_0\|^2/p}{\|Y-\hat{Y}\|^2/(n-(p+1))}$$

$F$ suit une loi de Fisher de pramètres p et n-(p+1).

Région de rejet:

$$\{F>f_{1-\alpha;p;n-(p+1)}\}$$

où $f_{1-\alpha;p;n-(p+1)}$ est le $1-\alpha$ quantile de la loi de Fisher.

