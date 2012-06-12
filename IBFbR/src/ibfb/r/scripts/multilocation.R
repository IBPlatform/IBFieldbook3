GLOBAL<-function(archivo){
library(lme4)

#ARREGLO DE ENTRADA DE DATOS#

datos<-within(read.csv(archivo,header = TRUE,na.strings = "."),
	{
		LOC<-factor(LOC)
		REP<-factor(REP)
		BLK<-factor(BLK)
		ENTRY<-factor(ENTRY)
	}
	)

ss<-datos$GY
sd<-matrix(ss)
se<-as.numeric(sd)
datos2<-cbind(datos,se)

aa<-c(levels(datos2$LOC))
ab<-as.numeric(aa)
a<-t(t(ab))

estandarizar<-function(a){
		iter<-1
		estandariza1<-NULL
 		while(iter<=length(a))
			{
				datos5<-datos2$LOC==a[iter]
				datos6<-datos2[datos5,]
				datos7<-datos6$se
				media<-mean(datos7,na.rm = TRUE)
				desv<-sqrt(var(datos7,na.rm = TRUE))
				estandariza<-(datos7-media)/desv
				estandariza1<-c(estandariza1,estandariza)
				iter<-iter+1
			}
			return(estandariza1)
		}
SGY<-estandarizar(a)
datos3<-cbind(datos2,SGY)

sl<-c(1,2,3,4,5,length(datos3)-1,length(datos3))
datos4<-datos3[,sl]

mix<-function(a){
		iter<-1
		sali41<-NULL
		while(iter<=length(a))
				{
				datos5<-datos4$LOC==a[iter]
				datos6<-datos4[datos5,]
				Rm1ML <- lmer(se ~ (1 | REP)+ (1 |REP:BLK)+(1 | ENTRY) ,datos6, REML = TRUE, verbose = TRUE)
				varcorr<-VarCorr(Rm1ML)	
				covs <- c(varcorr$ENTRY,varcorr$REP,varcorr$`REP:BLK`,attr(varcorr,"sc")^2)
 				covs1<-t(matrix(covs))
				gr<-((Rm1ML)@flist)
				rep_loc<-c(max(as.numeric(gr[,1])),max(as.numeric(gr[,2])),max(as.numeric(gr[,3])))
				sali4<-t(c(covs1,a[iter],rep_loc[3]))
				rownames(sali4)<-a[iter]
				sali41<-rbind(sali41,sali4)
				iter<-iter+1
				}
		return(sali41)
		}
resultado<-mix(a)

resultado_1<-cbind(resultado[,5],resultado[,3],resultado[,2],resultado[,1],resultado[,4],resultado[,6])

nombres_cols1<-c("Vent","Vrepxblk","Vrep","Vres","loc","nrep")
colnames(resultado)<-nombres_cols1

resultado_1<-cbind(resultado[,5],resultado[,3],resultado[,2],resultado[,1],resultado[,4],resultado[,6])
Heritability <-resultado_1[,4]/(resultado_1[,4]+(resultado_1[,5]/resultado_1[,6]))
colnames(resultado_1)<-nombres_cols1

Her_1<-cbind(resultado_1,Heritability)
Her<-Her_1[Her_1[,7]<.99999,]
Her<-Her[,-6]

Her<-t(Her)
Her<-Her[-1,]


mix_2<-function(n){
		iter<-1
		sali41<-NULL
		while(iter<=length(a))
			{
				datos5<-datos4$LOC==a[iter]
				datos6<-datos4[datos5,]
				Rm2ML <- lmer(datos6[,5] ~ ENTRY + (1 | REP)+ (1 |REP:BLK) ,datos6, REML = TRUE, verbose = TRUE)
				effij<-matrix(fixef(Rm2ML))
				intercep_ef<-effij[1,1]
				correc_effij<-effij[-1]+intercep_ef
				effij1<-matrix(c(intercep_ef,correc_effij))
				colnames(effij1)<-paste(colnames(datos6)[1], a[iter], sep="")
				rownames(effij1)<-names(fixef(Rm2ML))
				sali41<-cbind(sali41,effij1)
				iter<-iter+1
			}
			return(sali41)
			}

resultado2<-mix_2(a)


Meansgy<-rbind(resultado2,Her)
ln<-length(Meansgy[1,])
R1<-cor(resultado2)

Gencorrel<- rbind(t(a),Heritability)

corridas<-function(a){
	b<-a+1
	herab1<-NULL
	herab2<-NULL
	ident1<-NULL
	while(a<b && b<=ln)
		{
			while(b<=ln)
				{
					herab<-sqrt(Gencorrel[2,a]*Gencorrel[2,b])
					herab1<-rbind(herab1,herab)
					b<-b+1
				}
			sali11<-return(matrix(herab1))
			a<-a+1
			herab2<-rbind(herab2,herab1)
		}
	return(matrix(herab2))
	}

eva_corr<-function(a){
	sali<-NULL
	while(a<ln)
		{
			eve<-corridas(a)
			sali<-rbind(sali,eve)
			a<-a+1
		}
	return(sali)
	}
covariances1<-eva_corr(1)
covariances1

#construccion de L

corridas_L<-function(a){
	b<-a+1
	const1_L<-NULL
	const2_L<-NULL
	while(a<b && b<=ln)
		{
		while(b<=ln)
			{
				const_L<-a
				const1_L<-rbind(const1_L,const_L)
				b<-b+1
			}
		sali11<-return(matrix(const1_L))
		a<-a+1
		const2<-rbind(const2_L,const1_L)
		}	
	return(matrix(const2_L))
	}

eva_const_L<-function(a){
	sali_const_L<-NULL
	while(a<ln)
		{
			eve_const_L<-corridas_L(a)
			sali_const_L<-rbind(sali_const_L,eve_const_L)
			a<-a+1
		}
	return(sali_const_L)
	}
L_const<-eva_const_L(1)
L_const


#construccion de M

corridas_M<-function(a){
	b<-a+1
	const1<-NULL
	const2<-NULL
	while(a<b && b<=ln)
		{
		while(b<=ln)
			{
			const<-a+1
			a<-const
			const1<-rbind(const1,const)
			b<-b+1
			}
		sali11<-return(matrix(const1))
		a<-a+1
		const2<-rbind(const2,const1)
		}
	return(matrix(const2))
	}

eva_const<-function(a){
	sali_const<-NULL
	while(a<ln)
		{
		eve_const<-corridas_M(a)
		sali_const<-rbind(sali_const,eve_const)
		a<-a+1
		}
	return(sali_const)
	}

M_const<-eva_const(1)
M_const

#pegado de covarianzas1, P1 y T1
Lm1<-cbind(L_const,M_const,covariances1)
Lm2<-cbind(Lm1[,2],Lm1[,1],Lm1[,3])
colnames(Lm1)<-c("P1","T1","Covariances1")
colnames(Lm2)<-c("P1","T1","Covariances1")

corr_same<-cbind(rep(1:ln,1),rep(1:ln,1),rep(1,ln))
colnames(corr_same)<-c("P1","T1","Covariances1")
union<-rbind(Lm1,Lm2,corr_same)

x<-union[,1]
y<-union[,2]
z<-union[,3]

Hermat<-cbind(x,y,z)[ order(y, x, z),]
Hermat<-ifelse(Hermat==0,0.0001,Hermat)
H<-matrix(Hermat[,3],ncol=ln)
GR<-R1/H
GR<-as.numeric(GR)
GR<-ifelse(GR>1,1,GR)
GR<-ifelse(GR<(-1),-1,GR)
GR<-matrix(GR,ncol=ln)


colnames(GR)<-colnames(R1)
rownames(GR)<-rownames(R1)

Rout<-1-GR
Rout
a
Rout2<-cbind(a,Rout)
Rout2<-Rout2[,-1]
colnames(Rout2)<-colnames(R1)
rownames(Rout2)<-rownames(R1)
Rout2
matrix(Rout2,ncol=ln)

labels=colnames(Rout2)
salcorr<-hclust(dist(matrix(Rout2,ncol=ln),method="euclidean"),method = "ward", members=NULL)
plot(salcorr,labels=colnames(Rout2))


datoss<-(cbind(datos,SGY))

combinado<-function(iii){

	iter<-5
	acum1<-NULL
	while(iter<=iii)
		{
			prues<-datoss[,c(1:4,iter)]		
			pruebas<-prues[order(prues$LOC,prues$ENTRY,prues$REP,prues$BLK),]
			Rm1MLp <- lmer(pruebas[,5] ~ ENTRY+(1 | LOC) +  (1 |LOC:ENTRY)+ (1 |(REP:LOC):BLK),pruebas, REML = TRUE, verbose = TRUE,na.action="na.omit")
			effijp<-matrix(fixef(Rm1MLp))
			intercep_efp<-effijp[1,1]
			correc_effijp<-effijp[-1]+intercep_efp
			effij1p<-matrix(c(intercep_efp,correc_effijp))
			nombres_cols<-colnames(pruebas)
			colnames(effij1p)<-nombres_cols[5]
			acum1<-cbind(acum1,effij1p)
			acum1
			iter<-iter+1
		}
	return(acum1)
	}


iii<-dim(datoss)[2]
ddd<-combinado(iii)

lmeans<-ddd[,order(colnames(ddd))]
lmeans

nombres_filas<-paste("ENTRY",1:length(lmeans[,1]),sep=" ")
rownames(lmeans)<-nombres_filas


combinado_2<-function(iii){
	iter<-5
	acum1<-NULL
	while(iter<=iii)
		{
			prues<-datoss[,c(1:4,iter)]
			pruebas<-prues[order(prues$LOC,prues$ENTRY,prues$REP,prues$BLK),]
			Rm1MLp <- lmer(pruebas[,5] ~1+ (1|ENTRY)+(1 | LOC) +  (1 |LOC:ENTRY)+ (1 |(REP:LOC):BLK),pruebas, REML = TRUE, verbose = TRUE,na.action="na.omit")
			varcorr<-VarCorr(Rm1MLp)
			covs <- c(varcorr$`(REP:LOC):BLK`,varcorr$`ENTRY`,varcorr$`LOC`,varcorr$`LOC:ENTRY`,attr(varcorr,"sc")^2)
 			sali2.1<-t(matrix(covs))
			colnames(sali2.1)<-c("(REP:LOC):BLK","ENTRY","LOC","LOC:ENTRY","Residual")
			nombres_cols<-colnames(pruebas)
			rownames(sali2.1)<-nombres_cols[5]
			acum1<-rbind(acum1,sali2.1)
			iter<-iter+1
		}
	return(acum1)
	}

iii<-dim(datoss)[2]
covgy<-combinado_2(iii)

covgy



covgyl<-covgy[order(rownames(covgy)),]
covgyl


combinado_3<-function(iii){
	iter<-5
	acum1<-NULL
	while(iter<=iii)
		{
			prues<-datoss[,c(1:4,iter)]
			pruebas<-prues[order(prues$LOC,prues$ENTRY,prues$REP,prues$BLK),]
			indi<-which(pruebas[,5]!="NA",arr.ind = TRUE)
			pruebas1<-pruebas[indi,]
			pruebas11<-pruebas1$LOC
			pruebas11<-as.numeric(pruebas11)
			dimension<-dim(table(pruebas11))
			pruebas12<-pruebas1$REP
			pruebas12<-as.numeric(pruebas12)
			dimension2<-dim(table(pruebas12))
			if (dimension>1)  Rm1MLp <- lm(pruebas[,5] ~ REP  + ENTRY + LOC + LOC*ENTRY,pruebas,na.action="na.omit",contrasts = NULL) else Rm1MLp <- lm(pruebas[,5] ~ REP  + ENTRY + ENTRY,pruebas,na.action="na.omit",contrasts = NULL)
			sali<-Rm1MLp$df.residual
			sali<-c(dimension2,dimension,sali)
			sali<-matrix(sali,ncol=3)	
			nombres_cols<-colnames(pruebas)
			rownames(sali)<-nombres_cols[5]
			acum1<-rbind(acum1,sali)
			acum1
			iter<-iter+1
		}
		colnames(acum1)<-c("REP","LOC","Residual")
		return(acum1)
	}

iii<-dim(datoss)[2]
DDF<-combinado_3(iii)
DDF
DDF<-DDF[order(rownames(DDF)),]
nom_DDF<-colnames(DDF)

covgyl<-covgyl[order(rownames(covgyl)),]
covgyl<-covgyl[,-1]
nom_covgyl<-colnames(covgyl)


alfa<-0.05
covgy2<-cbind(covgyl,DDF)
contenedor<-NULL
Heritability<-covgy2[,1]/(covgy2[,1]+(covgy2[,3]/covgy2[,6])+covgy2[,4]/(covgy2[,5]*covgy2[,6]))
SEM<-covgy2[,3]/covgy2[,6]+covgy2[,4]/(covgy2[,5]*covgy2[,6])
t<-qt(1-alfa/2,covgy2[,7])
SED<-sqrt(t*SEM)
LSD<-t*SED

Her<-cbind(covgy2,Heritability,SEM,t,SED,LSD)
Heritability<-t(Her)
Allvar<-rbind(lmeans,Heritability)
Allvar

histograma<-paste(archivo,"histograma","pdf",sep = ".")

pdf(file=histograma)
plot(salcorr,labels=colnames(Rout2))
dev.off()


GR
Allvar
GR_salida<-paste(archivo,"GR","csv",sep=".")
Allvar_salida<-paste(archivo,"Allvar","csv",sep=".")
write.csv(GR,file=GR_salida)
write.csv(Allvar,file=Allvar_salida)
}

