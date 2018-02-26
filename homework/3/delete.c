#include<stdio.h>
void replace(int n,char array[811])
{ 
	int i=n;
//	printf("WoW!%d\n",n);
	while (array[i]!='\0'){
		while(i<811){ 
		array[i]=array[i+1];
	    i++;
		}
	}
}

int main()
{
	char array[811];	
	int i=0,n,a=0,N;
	scanf("%s",array);
	scanf("%d",&N);
	//puts(array);
	//printf("%d",N);
	//return 0;
	while (array[i+1]!='\0'&&a<N){
		while(a<N){	i=0;
		while(i<811){
			if((array[i]-'0')>(array[i+1]-'0')){
			replace(i,array);i++;	
			break;}
			//puts(array);
			++i;
		}
		//puts(array);
		a++;	
		}	
	}
	puts(array);
	return 0;
}




