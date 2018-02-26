#include<stdio.h>
main()
{
    int N,max,min,i,r,x2;
    scanf("%d",&N);
    scanf("%d",&max);
    min = max;
    for(i=1;i<N;i++)
    {     
    scanf("%d",&x2);
    if(max < x2)max = x2; 
    if(min > x2)min = x2;
    }
    printf("%d %d",max,min);
    scanf("%d",r);

}


