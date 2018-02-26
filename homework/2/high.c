#include <stdio.h>
#include<math.h>
int main()
{
   double n,m,s,h;
   scanf("%lf %lf",&n,&m);
   h=pow(0.25,m);
   s=(8*n/3)*(1-h)-n;
   printf("%.2f\n%.2f",s,h*n);
   
    
 } 


