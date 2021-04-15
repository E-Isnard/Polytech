library(rpart)
library(rpart.plot)
et.seed(2001) # random seed

n <- 200
maxDigit <- 2
y <- rep(0:maxDigit, length = n) # labels
# patterns of the digits
pattern <- c(1,1,1,0,1,1,1, 
             0,0,1,0,0,1,0,
             1,0,1,1,1,0,1,
             1,0,1,1,0,1,1,
             0,1,1,1,0,1,0,
             1,1,0,1,0,1,1,
             0,1,0,1,1,1,1,
             1,0,1,0,0,1,0,
             1,1,1,1,1,1,1,
             1,1,1,1,0,1,0)
lights <- matrix(pattern, 10, 7, byrow = TRUE) # patterns as a matrix
lights <- lights[1:(maxDigit+1),] # select just some digits
noisy_lights <- matrix(rbinom(n*7, 1, 0.7), n, 7) # Noisy lights
noisy_lights <- ifelse(lights[y+1, ] == 1, noisy_lights, 1-noisy_lights)
random_lights <- matrix(rbinom(n*17, 1, 0.5), n, 17) # Random lights 
x <- cbind(noisy_lights, random_lights) # inputs
training_data <- as.data.frame(x) # training dataset: inputs
training_data$y <- y # training dataset: labels 

treeFitted <- rpart(y~., 
                    data = training_data, 
                    method='class', # output is a categorical variable by asking for method=’class’
                    control = rpart.control(
                      xval = 10, # number of cross-validations
                      minbucket = 2, # the minimum number of observations in any terminal (leaf) node
                      cp = 0.0)
)

rpart.plot(treeFitted,type=3)
printcp(treeFitted)
plotcp(treeFitted)
