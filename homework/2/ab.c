#include<stdio.h>
int main() 
{
	int N,A=1,B;
	scanf("%d",&N);
	while(A<9)
	{B=A+1;
		while(B<9){
	
		if((A*10+B)*(B*10+A)==N) {
		printf("%d%d",A,B);
		return 0;}
	B++;}
	A++;}
	

    
} 


