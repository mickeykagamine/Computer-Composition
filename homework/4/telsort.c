#include<stdio.h>
#include<string.h>
void sort(char array[100][100],int N){
	int i,m,mini;
	char min[100],tmp[100];
	for(i=1;i<=N;i++){
		strcpy(min,array[i]);
		mini=i;	
		for(m=i;m<=N;m++){
			if(strcmp(min,array[m])>0){
				strcpy(min,array[m]);
				mini=m;
			}
		}
		strcpy(tmp,array[mini]);
		strcpy(array[mini],array[i]);
		strcpy(array[i],tmp);		
	}
}
int main(){
	int N,i,m,n;
	char array[100][100],name[10],number[10];
	scanf("%d",&N);
	for(i=0;i<=N;i++){
		gets(array[i]);
	}
//	for(i=1;i<=N;i++){
//	puts(array[i]);
//}
	sort(array,N);
	for(i=1;i<=N;i++){
		for(m=0;m<10&&array[i][m]!=' ';m++){
			name[m]=array[i][m];
		}
		name[m]='\0';
		if(array[i][m]==' '){
			for(n=0;n<=10;n++,m++){
				number[n]=array[i][m];
			}
			number[n]='\0';
		}
		else{
			do{
				m++;
			}
			while(array[i][m]!=' ');
			for(n=0;n<=10;n++,m++){
				number[n]=array[i][m];
			}
			number[n]='\0';
		}
	
		printf("%12s%12s\n",name,number);
	}
	
}

