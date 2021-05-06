library(matlib)
R <-
  matrix(
    data = c(-10, -10, 44, -4, -4, 44, -10, -10),
    ncol = 2,
    nrow = 4,
    byrow = TRUE
  )
Q <- array(data = 0, dim = c(4, 2, 4))

Q[1, 1, 2] = 0.9
Q[1, 1, 3] = 0.1

Q[1, 2, 2] = 0.1
Q[1, 2, 3] = 0.9

Q[2, 1, 1] = 0.1
Q[2, 1, 4] = 0.9

Q[2, 2, 1] = 0.9
Q[2, 2, 4] = 0.1

Q[3, 1, 1] = 0.9
Q[3, 1, 4] = 0.1

Q[3, 2, 1] = 0.1
Q[3, 2, 4] = 0.9

Q[4, 1, 2] = 0.1
Q[4, 1, 3] = 0.9

Q[4, 1, 2] = 0.9
Q[4, 1, 3] = 0.1

pi1 = matrix(
  data = c(1, 0, 1, 0, 1, 0, 1, 0),
  nrow = 4,
  ncol = 2,
  byrow = TRUE
)
pi1

pi2 = matrix(
  data = c(0, 1, 0, 1, 0, 1, 0, 1),
  nrow = 4,
  ncol = 2,
  byrow = TRUE
)
pi2

pi3 = matrix(
  data = c(0.4, 0.6, 1, 0, 0, 1, 0.5, 0.5),
  nrow = 4,
  ncol = 2,
  byrow = TRUE
)
pi3
gamma = 0.8
Rpi <- function(pi) {
  Rpi = matrix(
    data = rowSums(pi * R),
    ncol = 1,
    nrow = 4,
    byrow = TRUE
  )
  return(Rpi)
}
Ppi <- function(pi) {
  Ppi = matrix(data = 0,
               nrow = 4,
               ncol = 4)
  for (i in 1:4) {
    for (j in 1:4) {
      Ppi[i, j] = pi[i, ] %*% Q[i, , j]
    }
    
  }
  return(Ppi)
}

Rpi1 = Rpi(pi1)
Rpi1
Ppi1 = Ppi(pi1)
A1 = diag(4)-gamma*Ppi1
vpi1 = solve(a=A1,b=Rpi1)
vpi1
