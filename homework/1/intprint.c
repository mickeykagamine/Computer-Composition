#include<stdio.h>

int main()
{
    int i=0,x,y,z,w,a,b,c,d,e,x1,x2,x3,x4;
    scanf("%d",&x);
    x1=x/10000;
    while(x1!=0)
    { 
    y=x%10000;
    z=(x-y)/10000;
    w=y%1000;
    a=(y-w)/1000;
    b=w%100;
    c=(w-b)/100;
    d=b%10;
    e=(b-d)/10;
    printf("5\n");
    printf("%d%d%d%d%d\n",z,a,c,e,d);
    printf("%d%d%d%d%d\n",d,e,c,a,d);
    system("pause");
    return 0; 
    }     
    while(x1=0);
    {
                x2=x/1000; 
                while(x2!=0) 
                {
                             y=x%1000;
                             z=(x-y)/1000;
                             w=y%100;
                             a=(y-w)/100;
                             b=w%10;
                             c=(w-b)/10;
                             printf("4\n");
                             printf("%d%d%d%d\n",z,a,c,b);
                             printf("%d%d%d%d\n",b,c,a,z);
                             system("pause");
                             return 0;
                }
                while(x2=0);
                {
                           x3=x/100;
                           while(x3!=0)
                           {
                                       y=x%100;
                                       z=(x-y)/100;
                                       w=y%10;
                                       a=(y-w)/10;
                                       printf("3\n");
                                       printf("%d%d%d\n",z,a,w);
                                       printf("%d%d%d\n",w,a,z);
                                       system("pause");
                                       return 0;
                           }
                           while(x3=0);
                           {
                                       x4=x/10;
                                       while(x4!=0)
                                       {
                                                   y=x%10;
                                                   z=(x-y)/10;
                                                   printf("2\n");
                                                   printf("%d%d\n",z,y);
                                                   printf("%d%d\n",y,z);
                                                   system("pause");
                                                   return 0;
                                       }
                                       while(x4=0);
                                       {
                                                   printf("1\n");
                                                   printf("%d\n",x);
                                                   printf("%d\n",x);
                                                   system("pause");
                                                   return 0;
                                       }
                                       
                           }
                }
    }

    system("pause"); 
    return 0;
}


