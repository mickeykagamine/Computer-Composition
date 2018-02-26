
#include <stdio.h>
int main()
{
	double a,i,n,x,b,e,c;
	scanf("%lf",&e);	
	n=1;
		
	double jiecheng2(double n){
	a=i=1;
	while (i<2*n-1){i=i+2;
	a=a*i;}}
	double jiecheng1(double n){
	b=i=1;
	while (i<n-1){i=i+1;
	b=b*i;}}
	x=2;
	jiecheng1(n);
	jiecheng2(n);
	while(b*2/a>e){n++;jiecheng1(n);jiecheng2(n);
	x=x+b*2/a;
	}
	
	
	printf("%d %.7f",(int)n,x);
}


