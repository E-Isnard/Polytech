library(dplyr)
library(plotly)
library(quantmod)
library(tseries)
library(forecast)
library(astsa)
library(tsbox)

d <- list(line = list(color = '#d1242c'))
i <- list(line = list(color = '#28a315'))

btc<-read.csv("C:\\Users\\enzoi\\Documents\\Polytech\\ValorisationDesDonnes\\data\\coin_Bitcoin.csv")
btc$Date <- as.Date(btc$Date)
fig <- btc %>% plot_ly(x = ~btc$Date, type="candlestick",
                       open = ~btc$Open, close = ~btc$Close,
                       high = ~btc$High, low = ~btc$Low,
                       increasing = i, decreasing = d)
fig

usd<-read.csv("C:\\Users\\enzoi\\Documents\\Polytech\\ValorisationDesDonnes\\data\\coin_USDCoin.csv")
fig <- usd %>% plot_ly(x = ~usd$Date, type="candlestick",
                       open = ~usd$Open, close = ~usd$Close,
                       high = ~usd$High, low = ~usd$Low,
                       increasing = i, decreasing = d)
fig

usdt<-read.csv("C:\\Users\\enzoi\\Documents\\Polytech\\ValorisationDesDonnes\\data\\coin_Tether.csv")
usdt$Date <- as.Date(usdt$Date)
fig <- usdt %>% plot_ly(x = ~usdt$Date, type="candlestick",
                       open = ~usdt$Open, close = ~usdt$Close,
                       high = ~usdt$High, low = ~usdt$Low,
                       increasing = i, decreasing = d)
fig

eth<-read.csv("C:\\Users\\enzoi\\Documents\\Polytech\\ValorisationDesDonnes\\data\\coin_Ethereum.csv")
eth$Date <- as.Date(eth$Date)
fig <- eth %>% plot_ly(x = ~eth$Date, type="candlestick",
                       open = ~eth$Open, close = ~eth$Close,
                       high = ~eth$High, low = ~eth$Low,
                       increasing = i, decreasing = d)
fig

index = 1800
btc_ts = ts(btc$Close,frequency = 1)
btc_ts2 = ts(btc_ts[1:index])
autoplot(btc_ts2,type="l")
btc_diff2 = diff(btc_ts2)
plot(btc_diff2,type="l")

model = auto.arima(btc_ts2,approximation = FALSE,stepwise = FALSE,seasonal = TRUE)
summary(model)
checkresiduals(model)
h_pred=7
forecast1 = forecast(model,h=h_pred)
autoplot(forecast1,include = 100)


df = ts_data.frame(forecast1$mean)
df$true = btc_ts[index:(index+h_pred-1)]
df
fig <- plot_ly(df, x = ~time, y = ~value, name = 'prediction', type = 'scatter', mode = 'lines',color=I("red"))
fig <- fig %>% add_trace(y = ~value+se, name = 'max', mode = 'lines',color = I("black"))
fig <- fig %>% add_trace(y = ~value-se, name = 'min', mode = 'lines',fill="tonexty",color=I("black"))
fig <- fig %>% add_trace(y = ~value+2*se, name = 'max', mode = 'lines',color = I("grey"))
fig <- fig %>% add_trace(y = ~value-2*se, name = 'min', mode = 'lines',fill="tonexty",color=I("grey"))
fig <- fig %>% add_trace(y = ~true, name = 'true values', mode = 'lines',color=I("orange"))
fig



#===================================

usd_ts = ts(usd$Close)
plot.ts(usd_ts)
ndiffs(log(usd_ts))
usd_stationary <- diff(log(usd_ts), differences = 1)
plot(usd_stationary)

auto.arima(usd_stationary,stationary = TRUE)
(fit <- Arima(usd_stationary, order=c(2,0,2)))
autoplot(forecast(fit,h=1000))

usd_ts = ts(usd$Close)
plot.ts(usd_ts)

auto.arima(usd_ts)
(fit <- Arima(usd_stationary, order=c(5,2,0)))
autoplot(forecast(fit,h=50))

ts.plot(usd_ts)
auto.arima(usd_ts)
sarima(usd_ts,1,1,1)
forecast<-sarima.for(usd_ts,30,2,1,2)


usd_ts2<-usd_ts[0:873]
ts.plot(usd_ts2)
auto.arima(usd_ts2)
sarima(usd_ts2,1,1,2)
forecast<-sarima.for(usd_ts2,30,2,1,2)

#=====================================

usdt_ts = ts(usdt$Close)
plot.ts(usdt_ts)
ndiffs(log(usdt_ts))
usdt_stationary <- diff(log(usdt_ts), differences = 1)
plot(usdt_stationary)

auto.arima(usdt_stationary,stationary = TRUE)
(fit <- Arima(usdt_stationary, order=c(2,0,2)))
autoplot(forecast(fit,h=1000))

usdt_ts = ts(usdt$Close)
plot.ts(usdt_ts)

auto.arima(usdt_ts)
(fit <- Arima(usdt_stationary, order=c(5,2,0)))
autoplot(forecast(fit,h=50))


a<-log(1+usdt$Close)
usdt_ts<-xts(a,as.Date(1:873,origin="2018-10-09"))

ts.plot(usdt_ts)
auto.arima(usdt_ts)
sarima(usdt_ts,1,1,1)
forecast<-sarima.for(usdt_ts,30,2,1,2)


usdt_ts2<-usdt_ts[0:873]
ts.plot(usdt_ts2)
auto.arima(usdt_ts2)
sarima(usdt_ts2,1,1,2)
forecast<-sarima.for(usdt_ts2,30,2,1,2)

#================================================

eth_ts = ts(eth$Close)
plot.ts(eth_ts)
ndiffs(log(eth_ts))
eth_stationary <- diff(log(eth_ts), differences = 1)
plot(eth_stationary)

auto.arima(eth_stationary,stationary = TRUE)
(fit <- Arima(eth_stationary, order=c(2,0,2)))
autoplot(forecast(fit,h=1000))

eth_ts = ts(eth$Close)
plot.ts(eth_ts)

auto.arima(eth_ts)
(fit <- Arima(eth_stationary, order=c(5,2,0)))
autoplot(forecast(fit,h=50))


a<-log(1+eth$Close)
eth_ts<-xts(a,as.Date(1:873,origin="2018-10-09"))

ts.plot(eth_ts)
auto.arima(eth_ts)
sarima(eth_ts,1,1,1)
forecast<-sarima.for(eth_ts,30,2,1,2)


eth_ts2<-eth_ts[0:873]
ts.plot(eth_ts2)
auto.arima(eth_ts2)
sarima(eth_ts2,1,1,2)
forecast<-sarima.for(eth_ts2,30,2,1,2)

df = ts_data.frame(forecast$pred)
df$true = eth_ts[873:902]
df
fig <- plot_ly(df, x = ~time, y = ~value, name = 'prediction', type = 'scatter', mode = 'lines')
fig <- fig %>% add_trace(y = ~true, name = 'true values', mode = 'lines+markers') 
fig

