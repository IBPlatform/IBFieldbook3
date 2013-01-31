GLOBAL <-
function(archivo){

library(lme4)
library(nlme)
alfa<-0.05

datos<-within(read.csv(archivo,header = TRUE,na.strings = "."),
{
LOC<-factor(LOC)
Rep<-factor(Rep)
Block<-factor(Block)
Entry<-factor(Entry)
}
)

datos<-datos[order(datos$LOC,datos$Entry,datos$Rep,datos$Block),]



iter<-1
while(iter<=dim(datos)[2])
{
if(all(is.na(datos[,iter])==TRUE)) datos=datos[,-iter] else datos=datos  
iter<-iter+1
}


a<-dim(datos)[2]


datoss<-datos

detach("package:nlme")

combinado<-function(iii){

iter<-5
acum1<-NULL
while(iter<=iii)
{

prues<-datoss[,c(1:4,iter)]
pruebas<-prues[order(prues$LOC,prues$Entry,prues$Rep,prues$Block),]
#Rm1MLp <- lmer(pruebas[,5] ~ Entry+(1 | LOC) +  (1 |LOC:Entry)+ (1 | (Rep:LOC):Block),pruebas, REML = TRUE, verbose = TRUE,na.action="na.omit")
Rm1MLp <- lmer(pruebas[,5] ~ Entry + (1 |(Rep):Block),pruebas, REML =  TRUE, verbose = TRUE,na.action="na.omit")
effijp<-matrix(fixef(Rm1MLp))


intercep_efp<-effijp[1,1]
correc_effijp<-effijp[-1]+intercep_efp
effij1p<-matrix(c(intercep_efp,correc_effijp))

#nombres_cols<-colnames(pruebas)
colnames(effij1p)<-colnames(pruebas[5])
acum1<-cbind(acum1,effij1p)
acum1

iter<-iter+1
}
return(acum1)
}


iii<-dim(datoss)[2]
ddd<-combinado(iii)

lmeans<-ddd[,order(colnames(ddd))]
if(a<=5) lmeans=matrix(lmeans,ncol=1) else lmeans=lmeans
rownames(lmeans)<-c(paste("Entry",levels(datos$Entry)))




library(nlme)
combinado2<-function(iii){
sali41<-NULL
iter<-5
acum1<-NULL
while(iter<=iii)
{
detach("package:nlme")
prues<-datoss[,c(1:4,iter)]
pruebas<-prues[order(prues$LOC,prues$Entry,prues$Rep,prues$Block),]
Rm1MLp <- lmer(pruebas[,5] ~ 1+(1 | LOC) +  (1 |LOC:Entry)+ (1 | (Rep:LOC):Block),pruebas, REML = TRUE, verbose = TRUE,na.action="na.omit")
varcorr<-VarCorr(Rm1MLp)
covs <- matrix(c(attr(varcorr,"sc")^2,varcorr$`LOC:Entry`,attr (varcorr,"sc")^2),ncol=1)
rownames(covs)<-c("Estimate","VEntry","Vres")
colnames(covs)<-colnames(pruebas[5])
original<-colnames(pruebas[5])
library(nlme)
colnames(pruebas)[5]<-c("todos")
fm2<-lme(todos ~ Entry,pruebas, random =~ 1 |Block,  method="REML",na.action = na.omit)
an<-anova(fm2)
denDF<-rbind(max(as.numeric(pruebas$Rep)),an$denDF[1])
rownames(denDF)<-c("nReps","denDF")
covs1<-rbind(covs,denDF)
sali41<-cbind(sali41,covs1)
iter<-iter+1
}
return(sali41)
}


iii<-dim(datoss)[2]
ddb<-combinado2(iii)

if(a<=5) ddb=ddb else ddb=ddb[,order(colnames(ddb))]
 
detach("package:nlme")

ddc<-rbind(ddb[2,]/(ddb[2,]+(ddb[3,]/ddb[4,])),SEM<-ddb[3,]/ddb[4,],t<-qt(1-alfa/2,ddb [5,]),SED<-sqrt(t*SEM),t*SED)
rownames(ddc)<-c("Heritability","SEM","t","SED","LSD")

Allvar<-rbind(lmeans,ddb,ddc)

Allvar_salida<-paste(archivo,"Allvar","csv",sep=".")

write.csv(Allvar,file=Allvar_salida)

}

