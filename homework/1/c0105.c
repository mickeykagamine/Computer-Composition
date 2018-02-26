#include<stdio.h>

int main()
{
    int x,y,z,a,b,w,c,s,d;
    scanf("%d",&x);
    y = x%10;
    a = (x - y)/10;
    z = y%5;
    b = (y - z)/5;
    w = z%2;
    c = (z - w)/2;
    s = w%1;
    d = (w - s)/1;    
    printf("%d %d %d %d\n",a,b,c,d);
    system("pause");    
    return 0;
}


