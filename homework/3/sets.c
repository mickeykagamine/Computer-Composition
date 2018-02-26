#include<stdio.h>
int main()
{
	int array1[100]={0},array2[100]={0};
	int i=0,m=0, intersectant =0;
	while (i<100){
		scanf("%d",&array1[i]);
		if(array1[i]==-1)break;
		++i;
	}
	i=0;
	while(i<100){
		scanf("%d",&array2[i]);
		if(array2[i]==-1)break;
		++i;
	}
	i=0;
	
	while(i<100&&array1[i]!=-1){
		intersectant=0;
		m=0;
		while(m<100&&array2[m]!=-1){
			if(array1[i]==array2[m]){
				intersectant = 1;
				break;
			}
			++m;
		}
		if(!intersectant)
		printf("%d ",array1[i]);
		++i;
	}
	return 0;
 } 


