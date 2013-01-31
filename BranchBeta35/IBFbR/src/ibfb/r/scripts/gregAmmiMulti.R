rm(list=ls())
# Set the working directory
# Install the GEMI package (only the first time)
#install.packages("GEMI_1.0.zip",repos=NULL)
# Required R packages
require(lme4)
require(klaR)
require(Hmisc)
require(GEMI)
setwd("C:/IMIS-Installation/ScriptsR/testingscriptCCAH/GEMI/EXAMPLE2")
# Data 
#data(maiz)or from a *.csv file in the working directory
TRIAL1 <- read.csv("incomplete.csv")
ENV <- TRIAL1$LOC 
GEN <- TRIAL1$ENTRY
REP <- TRIAL1$REP
Y <- TRIAL1$GY
# Example 1: AMMI model with biplot and triplot graphs 
#mod1 <- GEMI(ENV, GEN, REP, Y)
# Example 2
 # AMMI model with biplot
# mod21 <- GEMI(ENV, GEN, REP, Y,graph="both",modty="ammi")
 # SREG model with biplot
mod22 <- GEMI(ENV, GEN, REP, Y,number=TRUE,graph="both",modty="sreg")
 # GREG model with biplot
#mod23 <- GEMI(ENV, GEN, REP, Y,number=FALSE,graph="biplot",modty="greg")
# Example 3: Missing data (%10)
mis <-sample(1:length(Y),floor(0.10*length(Y))) 
Y[mis] <- NA
 # AMMI model with triplot
mod31 <- GEMI(ENV, GEN, REP, Y,number=FALSE,graph="triplot",modty="ammi")
 # SREG model with triplot
mod32 <- GEMI(ENV, GEN, REP, Y,number=FALSE,graph="triplot",modty="sreg")
 # GREG model with triplot
mod33 <- GEMI(ENV, GEN, REP, Y,number=FALSE,graph="triplot",modty="greg")