# TD Linear Programming - Geometric Approach

## Exercise 6.1

1.

Let $x_1$ and $x_2$ be two points of $H$.

Let consider $x_t=t\,x_1 + (1-t)\,x_2$ for some
$t\in [0,1]$.

Let show that $x_t \in H$.

We have:

$$a^Tx_t = ta^Tx_1+(1-t)a^Tx_2\leq tb+(1-t)b = b $$

So $x_t \in H$

Se we have:

$∀(x_1,x_2)\in H^2,∀t\in[0,1],tx_1+(1-t)x_2\in H$

so by defenition, H is convex.

2.

Let $M\in \mathbb{R_+}$.

Let define $x(α):=\frac{-a}{ \|a\| }α$

To have $x(α)\in H$ we need

$a^Tx(α)≤b \implies -\|a\|α \leq b \implies α≥\frac{-b}{\|a||}$

Let choose $α=\max(-b/\|a\|,M+1)$

We have $\|x(α)\|=α≥M+1>M$

So for any $M\in \mathbb{R}_+$, there is an $x\in H$ such that $\|x\|≥M$, so by defenition $H$ is unbounded.

3.

Let $(x_1,x_2)\in (A\cap B)^2$.

$x_1,x_2 \in A\cap B$ so in particular, $x_1,x_2\in A$.

A is convex so $[x_1,x_2]\subset A$

We also have $[x_1,x_2] \subset B$

So $[x_1,x_2] \subset A\cap B$

So $\forall (x_1,x_2)\in (A\cap B)^2,[x_1,x_2]\subset A\cap B$.

So $A\cap B$ is convex.

## Exercise 6.2

1.
cf feuille

2.

$$
\begin{aligned}
    \max_{x\in \mathbb{R}^2}b^Tx\\
    Ax\geq c\\
    x\geq 0\\
\end{aligned}
$$
where $b=\begin{bmatrix}
    1 \\ 2
\end{bmatrix}$

 , $A = \begin{bmatrix}
    1 & 1 \\
    0 & 1
\end{bmatrix}$

and $c=\begin{bmatrix}
    6 \\ 3
\end{bmatrix}$

3.
$$
\begin{aligned}
    \max_{x_s\in \mathbb{R}^4}b_s^Tx_s \\
    A_sx_s = c \\
    x_s\geq 0
\end{aligned} 
$$

where

$b_s = \begin{bmatrix}
    1 \\ 2 \\ 0 \\ 0
\end{bmatrix}$
, $A=\begin{bmatrix}
    1 & 1 & 1 & 0 \\
    0 & 1 & 0 & 1
\end{bmatrix}$





