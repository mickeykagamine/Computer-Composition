#include<stdio.h>
main()
{
int N,a[10][10],sum[10][10]={0},i,m;
char op[10];
scanf("%d",&N);
op[0]='+'; 
while(1){	
for(i=0;i<N;i++)
	for(m=0;m<N;m++)scanf("%d",&a[i][m]);
if(op[0]=='+'){
	i=0;m=0;
	while(i<N){
	while(m<N){
	sum[i][m]=sum[i][m]+a[i][m];
		m++;	
	}m=0;
	i++;
	}}
	if(op[0]=='-'){
	i=0;m=0;
	while(i<N){
	while(m<N){
	sum[i][m]=sum[i][m]-a[i][m];
	m++;	
	}m=0;
	
	i++;
	}

}scanf("%s",&op);
if(op[0]=='#')break;
}

for(m=0;m<N;m++){

	for(i=0;i<N;i++)printf("%5d",sum[m][i]);
	printf("\n");}
}



