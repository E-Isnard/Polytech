rm(list = ls())

library(dplyr)
library(plotly)
library(quantmod)
library(tseries)
library(forecast)
library(astsa)
library(tsbox)

d <- list(line = list(color = '#d1242c'))
i <- list(line = list(color = '#28a315'))

btc <-
  read.csv(
    "C:\\Users\\enzoi\\Documents\\Polytech\\ValorisationDesDonnes\\data\\coin_Bitcoin.csv"
  )
btc$Date <- as.Date(btc$Date)
fig <- btc %>% plot_ly(
  x = ~ btc$Date,
  type = "candlestick",
  open = ~ btc$Open,
  close = ~ btc$Close,
  high = ~ btc$High,
  low = ~ btc$Low,
  increasing = i,
  decreasing = d
)

fig

date_to_vector <- function(date){
  y = format(as.Date(date, format="%Y-%m-%d"),"%Y")
  y_int = as.double(y)
  n_days = difftime(as.Date(date),as.Date(paste(y,"-01-01",sep = ""),format="%Y-%m-%d"),units = c("days"))
  n_days_int = as.integer(n_days)
  return(c(y_int,n_days_int))
}

forecast_arima <- function(data, index, h_pred) {
  #t1 = t2 = seq(as.Date(data$Date[1]),length=length(btc$Date),by="day")
  #t2 = seq(as.Date(data$Date[1]),length=index,by="day")
  s = date_to_vector(data$Date[1])
  data_ts = ts(data$Close,
               frequency = 365,
               start = s)
  data_ts2 = ts(data$Close[1:index],
                frequency = 360,
                start = s)
  model = auto.arima(
    data_ts2,
    method = "ML",
    approximation = TRUE,
    stepwise = TRUE,
    seasonal = TRUE
  )
  sim = simulate(model, nsim = h_pred)
  forecast1 = forecast(model, h = h_pred)
  df = ts_data.frame(forecast1$mean)
  df$true = data_ts[index:(index + h_pred - 1)]
  df$max.80 = forecast1$upper[, 1]
  df$max.95 = forecast1$upper[, 2]
  df$min.80 = forecast1$lower[, 1]
  df$min.95 = forecast1$lower[, 2]
  df$sim = sim
  names(df)[names(df) == "value"] <- "mean"
  
  fig <-
    plot_ly(
      df,
      x = ~ time,
      y = ~ mean,
      name = 'mean',
      type = 'scatter',
      mode = 'lines',
      color = I("red")
    )
  fig <-
    fig %>% add_trace(
      y = ~ max.80,
      mode = 'lines',
      color = I("black"),
      showlegend = FALSE
    )
  fig <-
    fig %>% add_trace(
      y = ~ min.80,
      name = 'min-max 80%',
      mode = 'lines',
      fill = "tonexty",
      color = I("black")
    )
  fig <-
    fig %>% add_trace(
      y = ~ max.95,
      mode = 'lines',
      color = I("grey"),
      showlegend = FALSE
    )
  fig <-
    fig %>% add_trace(
      y = ~ min.95,
      name = 'min-max 95%',
      mode = 'lines',
      fill = "tonexty",
      color = I("grey")
    )
  fig <-
    fig %>% add_trace(
      y = ~ true,
      name = 'true values',
      mode = 'lines',
      color = I("orange")
    )
  fig <-
    fig %>% add_trace(
      y = ~ sim,
      name = 'Simulation',
      mode = 'lines',
      color = I("green")
    )
  fig <-
    fig %>% layout(xaxis = list(type = "date", title = "time"),
                   yaxis = list(title = "Price"))
  return(list(fig, df, model))
  
}

x = forecast_arima(btc, 2400, 365)
fig = x[[1]]
df = x[[2]]
df$time = as.Date(df$time)
model = x[[3]]
f = forecast(model, h = 30)
df
fig

