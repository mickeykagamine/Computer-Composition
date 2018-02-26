#include<stdio.h>
main()
{
      char x,y,z;
      scanf("%c",&x);
      y=(int)(x)+1;
      z=(int)(x)-1;
      z=(char)(z);
      y=(char)(y);
      printf("%c %c %c\n%d %d %d\n",z,x,y,z,x,y);      
      system("pause");
      }


