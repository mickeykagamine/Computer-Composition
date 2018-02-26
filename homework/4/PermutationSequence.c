#include<stdio.h>
void paixu(int n,int N,int array[100]){
	int i,min,a;
//n++;
	while(n<N-1){	
	min=n+1;
	i=n;
	while(i<N-1){	
		if(array[min]>array[i+1]){
		min=i+1;
		}
		i++;				
	}
	a=array[n+1];
	array[n+1]=array[min]; 
	array[min]=a;
	n++;
	}
	
}
int zuida(int a[100],int n,int N){
	int j=N-1;
	while(j>=n){
		if(a[n]<a[j]){
		break;}
		j--;
	}
	return j;
	
}

int main(){
	int N,M,n,array[100],i=0,j,k,aa,bb;
	//scanf("%d %d",&n,&N);
	scanf("%d",&N);
	array[0]=1;
	while(i<N-1){
		array[i+1]=1+array[i];
		i++;
	}
				i=0;
				while(i<N){
				printf("%d ",array[i]);	
				i++;
				
				}printf("\n");
	int col=1;
	while(col)
	{
	M=N;
	 col=0;
	while(M) {
		if(M>=2&&array[M-2]<array[M-1]){
			k=M-2;
			col=1;
			j=zuida(array,k,N);
		//	printf("%d %d\n",k,j);
			aa=array[k];
			array[k]=array[j];
			array[j]=aa;
			paixu(k,N,array);
			i=0;
			while(i<N){
				printf("%d ",array[i]);	
				i++;
				}	
			//	scanf("%d",bb);	       
			 printf("\n");
			break;
		}
		M--;
	}}
	


	
}


